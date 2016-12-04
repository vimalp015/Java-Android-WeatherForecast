package in.lamiv.weatherforecast.utils;

import in.lamiv.weatherforecast.businesslogic.WeatherObject;
import in.lamiv.weatherforecast.interfaces.IWeatherObject;

/**
 * Created by vimal on 12/4/2016.
 */

public class ObjectFactory {

    public static IWeatherObject getWeatherObject() {
        return new WeatherObject();
    }
}
