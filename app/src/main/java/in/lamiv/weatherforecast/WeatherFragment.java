package in.lamiv.weatherforecast;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.lamiv.weatherforecast.utils.BaseFragment;


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
            textViewTimezone.setText("TimeZone: " + bundle.getString("timezone"));
            textViewTemperature.setText("Temperature: " + bundle.getDouble("temp") + (char) 0x00B0 + " F");
            textViewHumidity.setText("Humidity: " + bundle.getDouble("humidity"));
            textViewWindspeed.setText("Windspeed: " + bundle.getDouble("windspeed"));
            textViewVisibility.setText("Visibility: " + bundle.getDouble("visibility"));
            textViewSummary.setText("Summary: " + bundle.getString("summary"));
        }
        return view;
    }

    public static WeatherFragment newInstance(String timezone, double temp, double humidity,
                                              double windspeed, double visibility, String summary){
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putString("timezone", timezone);
        args.putDouble("temp", temp);
        args.putDouble("humidity", humidity);
        args.putDouble("windspeed", windspeed);
        args.putDouble("visibility", visibility);
        args.putString("summary", summary);
        fragment.setArguments(args);
        return fragment;
    }

}
