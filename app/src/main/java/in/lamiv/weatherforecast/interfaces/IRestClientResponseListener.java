package in.lamiv.weatherforecast.interfaces;

import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;
import in.lamiv.weatherforecast.dataobjects.ForecastData;

/**
 * Created by vimal on 12/5/2016.
 */

public interface IRestClientResponseListener {

    void onSuccessAction(int statusCode, ForecastData response);
    void onFailureAction(int statusCode, String responseString, Throwable throwable);

}
