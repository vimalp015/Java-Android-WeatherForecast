package in.lamiv.weatherforecast;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.lamiv.weatherforecast.dataobjects.ForecastData;
import in.lamiv.weatherforecast.utils.BaseFragment;
import in.lamiv.weatherforecast.utils.GlobalVars;


/**
 * This fragment will displayed to user while the weather data is being loaded
 */
public class LoadingFragment extends BaseFragment {

    OnActionListener mOnActionListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loading, container, false);
        getForecastData();
        return view;
    }

    private void getForecastData(){
        ForecastData forecastData = new ForecastData();

    }

    public static LoadingFragment newInstance(){
        LoadingFragment fragment = new LoadingFragment();
        return fragment;
    }

    public interface OnActionListener {
        void onLoadingCompleted(GlobalVars.LoadingAction loadingAction);
    }

}
