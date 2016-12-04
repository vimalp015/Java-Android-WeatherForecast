package in.lamiv.weatherforecast.coreservices.networking;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by vimal on 12/4/2016.
 */

public class RestClient {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void GetForecastData(String baseURL, RequestParams params,
                                               AsyncHttpResponseHandler responseHandler) {
        client.get(baseURL, params, responseHandler);
    }
}
