package in.lamiv.weatherforecast;


import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import in.lamiv.weatherforecast.dataobjects.ForecastData;
import in.lamiv.weatherforecast.interfaces.IRestClientResponseListener;
import in.lamiv.weatherforecast.interfaces.IWeatherObject;
import in.lamiv.weatherforecast.utils.BaseFragment;
import in.lamiv.weatherforecast.utils.GlobalVars;
import in.lamiv.weatherforecast.utils.ObjectFactory;

import static in.lamiv.weatherforecast.utils.GlobalVars.SHARED_PREFERENCE_LOCATION;


/**
 * This fragment will displayed to user while the weather data is being loaded
 */
public class LoadingFragment extends BaseFragment implements IRestClientResponseListener {

    OnActionListener mOnActionListener;
    Location location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loading, container, false);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            location = bundle.getParcelable(SHARED_PREFERENCE_LOCATION);
        }
        getForecastData();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if ( context instanceof LoadingFragment.OnActionListener) {
            mOnActionListener = (LoadingFragment.OnActionListener)context;
        }
    }

    @Override
    public void onDetach() {
        mOnActionListener = null;
        super.onDetach();
    }


    private void getForecastData(){
        IWeatherObject weatherObject = ObjectFactory.getWeatherObject();
        weatherObject.getForecastData(location, this, this.getContext());
    }

    //implementation of IRestClientResponseListener
    //{
    @Override
    public void onSuccessAction(int statusCode, ForecastData forecastData){
        if(mOnActionListener != null)
            mOnActionListener.onLoadingCompleted(GlobalVars.LoadingAction.SUCCESSFUL, forecastData);
    }

    @Override
    public void onFailureAction(int statusCode, String responseString, Throwable throwable){
        if(mOnActionListener != null)
            mOnActionListener.onLoadingCompleted(GlobalVars.LoadingAction.FAILED, null);
    }
    //}

    public static LoadingFragment newInstance(Location location){
        LoadingFragment fragment = new LoadingFragment();
        Bundle args = new Bundle();
        args.putParcelable(SHARED_PREFERENCE_LOCATION, location);
        fragment.setArguments(args);
        return fragment;
    }

    public interface OnActionListener {
        void onLoadingCompleted(GlobalVars.LoadingAction loadingAction, ForecastData forecastData);
    }

}
