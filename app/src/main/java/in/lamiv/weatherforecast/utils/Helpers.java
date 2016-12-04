package in.lamiv.weatherforecast.utils;


import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static in.lamiv.weatherforecast.utils.GlobalVars.DATETIME_FORMAT;

/**
 * Created by vimal on 12/4/2016.
 */

public class Helpers {

    private String TAG = "Helpers";

    public Date parseDate(String dateString) {
        DateFormat df = new SimpleDateFormat(DATETIME_FORMAT);
        Date startDate;
        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

}
