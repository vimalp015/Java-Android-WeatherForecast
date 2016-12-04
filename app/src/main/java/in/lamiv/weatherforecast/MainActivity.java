package in.lamiv.weatherforecast;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import in.lamiv.weatherforecast.dataobjects.ForecastData;
import in.lamiv.weatherforecast.utils.BaseActivity;
import in.lamiv.weatherforecast.utils.BaseFragment;
import in.lamiv.weatherforecast.utils.GlobalVars;

public class MainActivity extends BaseActivity implements TryAgainFragment.OnActionListener,
        LoadingFragment.OnActionListener {

    private BaseFragment mCurrentFragment;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserLocation();
    }

    //Methods from base activity
    //{
    @Override
    protected void loadDataLoadingFragment(Location location){
        unSubscribe();
        transitionToFragment(LoadingFragment.newInstance(location));
    }

    @Override
    protected void loadTryAgainFragment(){
        transitionToFragment(TryAgainFragment.newInstance());
    }
    //}

    //Try again button click listener
    //{
    @Override
    public void onTryAgainClicked() {
        getUserLocation();
    }
    //}

    //On loading completed action listener
    //{
    @Override
    public void onLoadingCompleted(GlobalVars.LoadingAction loadingAction, ForecastData forecastData) {
        if(loadingAction == GlobalVars.LoadingAction.SUCCESSFUL) {
            transitionToFragment(WeatherFragment.newInstance(forecastData.getTimezone(), forecastData.getCurrently().getTemperature()
                    , forecastData.getCurrently().getHumidity(), forecastData.getCurrently().getWindSpeed()
                    , forecastData.getCurrently().getVisibility(), forecastData.getCurrently().getSummary()));
        }
        else if(loadingAction == GlobalVars.LoadingAction.FAILED) {
            transitionToFragment(TryAgainFragment.newInstance());
        }
        else {
            Toast.makeText(this, "Unknown state", Toast.LENGTH_LONG).show();
            Log.wtf(TAG, "Unknown state returned by loading fragment");
        }
    }
    //}

    private void transitionToFragment(BaseFragment fragment) {
        mCurrentFragment = fragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_layout,
                fragment).commitAllowingStateLoss();
    }

}
