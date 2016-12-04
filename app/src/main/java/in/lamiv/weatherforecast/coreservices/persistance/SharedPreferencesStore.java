package in.lamiv.weatherforecast.coreservices.persistance;

import android.content.Context;
import android.content.SharedPreferences;

import static in.lamiv.weatherforecast.utils.GlobalVars.SHARED_PREFERENCE;

/**
 * Created by vimal on 12/4/2016.
 * Save values in SharedPreferences as String, and return null if item not in store
 *
 */

public class SharedPreferencesStore {

    public static void PutItem(String key, String value, Context context){
        SharedPreferences settings = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String GetItem(String key, Context context){
        SharedPreferences settings = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return settings.getString(key, null);
    }

}
