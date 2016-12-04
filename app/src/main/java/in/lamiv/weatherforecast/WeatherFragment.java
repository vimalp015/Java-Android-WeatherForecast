package in.lamiv.weatherforecast;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.lamiv.weatherforecast.utils.BaseFragment;


/**
 * Loaded when we have the required weather data to be displayed
 */
public class WeatherFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    public static WeatherFragment newInstance(){
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }

}
