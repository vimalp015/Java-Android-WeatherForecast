package in.lamiv.weatherforecast.interfaces;

import android.content.Context;
import android.location.Location;

/**
 * Created by vimal on 12/5/2016.
 */

public interface IWeatherObject {

    //Gets forecast data from Shared Preferences if available and if data is not older than an hour
    //else fetches the data from server
    void getForecastData(Location location, IRestClientResponseListener restClientResponseListener, Context context);

}
