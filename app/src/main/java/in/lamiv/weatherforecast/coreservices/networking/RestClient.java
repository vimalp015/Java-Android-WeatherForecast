package in.lamiv.weatherforecast.coreservices.networking;

import in.lamiv.weatherforecast.dataobjects.ForecastData;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vimal on 12/4/2016.
 */

public class RestClient {
    public static ForecastData GetForecastData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ForecastData.class);
    }
}
