package sathyabamanan.com.tiqriweather.Common;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;

import sathyabamanan.com.tiqriweather.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by baman on 6/3/17.
 */

public class CustomUtility {



    public String getCurrentCity(Context context){
        SharedPreferences prefs = context.getSharedPreferences("tiqriweatherpreferences", MODE_PRIVATE);
        return prefs.getString("cityName", "no_city");
    }

    public void savecurrentCity(String cityName, Context context){
        SharedPreferences.Editor editor =  context.getSharedPreferences("tiqriweatherpreferences", MODE_PRIVATE).edit();
        editor.putString("cityName", cityName);
        editor.commit();
    }

    public void saveMetricsytem(String systemName, Context context){
        SharedPreferences.Editor editor =  context.getSharedPreferences("tiqriweatherpreferences", MODE_PRIVATE).edit();
        editor.putString("metricSystem", systemName);
        editor.commit();
    }

    public String getCurrentMetricsytem(Context context){
        SharedPreferences prefs = context.getSharedPreferences("tiqriweatherpreferences", MODE_PRIVATE);
        return prefs.getString("metricSystem", "no_matric");
    }
}
