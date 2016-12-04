package in.lamiv.weatherforecast.businesslogic;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONObject;
import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import cz.msebera.android.httpclient.Header;
import in.lamiv.weatherforecast.MainActivity;
import in.lamiv.weatherforecast.coreservices.networking.RestClient;
import in.lamiv.weatherforecast.coreservices.persistance.SharedPreferencesStore;
import in.lamiv.weatherforecast.dataobjects.ForecastData;
import in.lamiv.weatherforecast.interfaces.IRestClientResponseListener;
import in.lamiv.weatherforecast.interfaces.IWeatherObject;
import in.lamiv.weatherforecast.utils.Helpers;

import static in.lamiv.weatherforecast.utils.GlobalVars.DARK_SKY_API_KEY;
import static in.lamiv.weatherforecast.utils.GlobalVars.DARK_SKY_BASE_URL;
import static in.lamiv.weatherforecast.utils.GlobalVars.DATETIME_FORMAT;
import static in.lamiv.weatherforecast.utils.GlobalVars.SHARED_PREFERENCE_WEATHER_SERVER_RESPONSE_DATETIME;
import static in.lamiv.weatherforecast.utils.GlobalVars.SHARED_PREFERENCE_WEATHER_SERVER_RESPONSE_STRING;

/**
 * Created by vimal on 12/5/2016.
 */

public class WeatherObject implements IWeatherObject {

    WeakReference<IRestClientResponseListener> listenerWeakReference;
    WeakReference<Context> contextWeakReference;

    @Override
    public void getForecastData(Location location, IRestClientResponseListener restClientResponseListener, Context context) {

        listenerWeakReference = new WeakReference<>(restClientResponseListener);
        contextWeakReference = new WeakReference<>(context);

        if(SharedPreferencesStore.GetItem(SHARED_PREFERENCE_WEATHER_SERVER_RESPONSE_DATETIME, context) != null) {
            Date lastDataDateTime = new Helpers().parseDate((SharedPreferencesStore.GetItem(SHARED_PREFERENCE_WEATHER_SERVER_RESPONSE_DATETIME, context)));
            Date today = Calendar.getInstance().getTime();
            long diff = today.getTime() - lastDataDateTime.getTime();
            long hours = TimeUnit.HOURS.toHours(diff);
            //consider saved data if it is not older than 1 hour
            if(hours <= 1){
                IRestClientResponseListener listener = listenerWeakReference.get();
                if (listener != null) {
                    Gson gson = new GsonBuilder().create();
                    ForecastData forecastData = gson.fromJson(SharedPreferencesStore.GetItem(SHARED_PREFERENCE_WEATHER_SERVER_RESPONSE_STRING, context), ForecastData.class);
                    listener.onSuccessAction(0, forecastData);
                }
            }
            else {
                getDataFromServer(location);
            }
        }
        else {
            getDataFromServer(location);
        }
    }

    private void getDataFromServer(Location location){
        String baseURL = DARK_SKY_BASE_URL
                .replace("{key}", DARK_SKY_API_KEY)
                .replace("{latitude}", String.valueOf(location.getLatitude()))
                .replace("{longitude}", String.valueOf(location.getLongitude()));

        RestClient.GetForecastData(baseURL, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                IRestClientResponseListener listener = listenerWeakReference != null ? listenerWeakReference.get() : null;
                Context context = contextWeakReference != null ? contextWeakReference.get() : null;

                //save the response to shared preferences for future use
                //{
                if(context != null) {
                    DateFormat df = new SimpleDateFormat(DATETIME_FORMAT);
                    Date today = Calendar.getInstance().getTime();
                    SharedPreferencesStore.PutItem(SHARED_PREFERENCE_WEATHER_SERVER_RESPONSE_DATETIME,
                            df.format(today), context);
                    SharedPreferencesStore.PutItem(SHARED_PREFERENCE_WEATHER_SERVER_RESPONSE_STRING,
                            response.toString(), context);
                }
                //}

                if (listener != null) {
                    Gson gson = new GsonBuilder().create();
                    ForecastData forecastData = gson.fromJson(response.toString(), ForecastData.class);
                    listener.onSuccessAction(statusCode, forecastData);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                IRestClientResponseListener listener = listenerWeakReference.get();
                if (listener != null) {
                    listener.onFailureAction(statusCode, responseString, throwable);
                }
            }
        });
    }
}
