package in.lamiv.weatherforecast.utils;

/**
 * Created by vimal on 12/4/2016.
 */

public class GlobalVars {
    public static final String SHARED_PREFERENCE = "SHARED_PREFERENCE";
    public static final String DARK_SKY_API_KEY = "a08a08393f369a67408129304af1eae0";
    public static final String DARK_SKY_BASE_URL = "https://api.darksky.net/forecast/{key}/{latitude},{longitude}";

    public enum LoadingAction {
        SUCCESSFUL, FAILED
    }
}
