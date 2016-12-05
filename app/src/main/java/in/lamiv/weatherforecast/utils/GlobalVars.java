package in.lamiv.weatherforecast.utils;

import java.text.DecimalFormat;

/**
 * Created by vimal on 12/4/2016.
 */

public class GlobalVars {
    public static final String SHARED_PREFERENCE = "SHARED_PREFERENCE";
    public static final String DARK_SKY_API_KEY = "a08a08393f369a67408129304af1eae0";
    public static final String DARK_SKY_BASE_URL = "https://api.darksky.net/forecast/{key}/{latitude},{longitude}/";
    public static final String SHARED_PREFERENCE_LOCATION = "SHARED_PREFERENCE_LOCATION";
    public static final String SHARED_PREFERENCE_WEATHER_SERVER_RESPONSE_STRING ="SHARED_PREFERENCE_WEATHER_SERVER_RESPONSE_STRING";
    public static final String SHARED_PREFERENCE_WEATHER_SERVER_RESPONSE_DATETIME ="SHARED_PREFERENCE_WEATHER_SERVER_RESPONSE_DATETIME";
    public static final String SHARED_PREFERENCE_FORECAST_DATA = "SHARED_PREFERENCE_FORECAST_DATA";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat(".##");

    public enum LoadingAction {
        SUCCESSFUL, FAILED
    }

    public enum BusinessObject {
        WEATHEROBJECT
    }
}
