package in.lamiv.weatherforecast.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class BaseActivity extends AppCompatActivity {

    protected Subscription subscription;
    private String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        unSubscribe();
        super.onDestroy();
    }

    protected void getUserLocation() {

        if ( ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION )
                == PackageManager.PERMISSION_GRANTED ) {

            final ReactiveLocationProvider locationProvider = new ReactiveLocationProvider(this);
            subscription = locationProvider.getLastKnownLocation()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(locationObserver);
        }
        else {
            loadTryAgainFragment();
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
    }

    Observer<Location> locationObserver = new Observer<Location>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, e.getMessage());
        }

        @Override
        public void onNext(Location location) {
            loadDataLoadingFragment(location);
        }
    };

    protected void loadDataLoadingFragment(Location location){}

    protected void loadTryAgainFragment(){}

    protected void unSubscribe(){
        if(subscription != null)
            subscription.unsubscribe();
    }

}
