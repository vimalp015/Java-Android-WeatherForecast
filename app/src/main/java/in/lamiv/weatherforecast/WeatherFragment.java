package in.lamiv.weatherforecast;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.lamiv.weatherforecast.dataobjects.ForecastData;
import in.lamiv.weatherforecast.utils.BaseFragment;
import in.lamiv.weatherforecast.utils.Helpers;

import static in.lamiv.weatherforecast.utils.GlobalVars.DECIMAL_FORMAT;
import static in.lamiv.weatherforecast.utils.GlobalVars.SHARED_PREFERENCE_FORECAST_DATA;


/**
 * Loaded when we have the required weather data to be displayed
 */
public class WeatherFragment extends BaseFragment {

    @BindView(R.id.fragment_weather_timezone)
    TextView textViewTimezone;
    @BindView(R.id.fragment_weather_temperature)
    TextView textViewTemperature;
    @BindView(R.id.fragment_weather_humidity)
    TextView textViewHumidity;
    @BindView(R.id.fragment_weather_windspeed)
    TextView textViewWindspeed;
    @BindView(R.id.fragment_weather_visibility)
    TextView textViewVisibility;
    @BindView(R.id.fragment_weather_summary)
    TextView textViewSummary;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this,view);

        if (getArguments() != null) {
            Bundle bundle = getArguments();
            ForecastData forecastData = bundle.getParcelable(SHARED_PREFERENCE_FORECAST_DATA);
            textViewTimezone.setText("Timezone: " + forecastData.getTimezone());
            textViewTemperature.setText("Temperature: " + forecastData.getCurrently().getTemperature() + (char) 0x00B0 + " F");
            textViewHumidity.setText("Humidity: " + String.valueOf(forecastData.getCurrently().getHumidity()));
            textViewWindspeed.setText("Wind speed: " + String.valueOf(forecastData.getCurrently().getWindSpeed()));
            textViewVisibility.setText("Visibility: " + String.valueOf(forecastData.getCurrently().getVisibility()));
            textViewSummary.setText("Summary: " + forecastData.getCurrently().getSummary());
        }
        return view;
    }

    public static WeatherFragment newInstance(ForecastData forecastData){
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putParcelable(SHARED_PREFERENCE_FORECAST_DATA, forecastData);
        fragment.setArguments(args);
        return fragment;
    }

}
