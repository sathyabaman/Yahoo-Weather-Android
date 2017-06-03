package sathyabamanan.com.tiqriweather;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import sathyabamanan.com.tiqriweather.Common.CustomUtility;
import sathyabamanan.com.tiqriweather.Common.SimpleDividerItemDecoration;
import sathyabamanan.com.tiqriweather.DataObjects.ForcastModel;
import sathyabamanan.com.tiqriweather.DataObjects.WeatherAdapter;
import zh.wang.android.yweathergetter4a.WeatherInfo;
import zh.wang.android.yweathergetter4a.YahooWeather;
import zh.wang.android.yweathergetter4a.YahooWeatherInfoListener;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static java.security.AccessController.getContext;

public class WeatherList extends AppCompatActivity implements YahooWeatherInfoListener {



    private RecyclerView recycleV_forcast;
    private RecyclerView.LayoutManager layoutManager;
    public static WeatherAdapter forcastAdapter;
    Context context;
    private YahooWeather mYahooWeather = YahooWeather.getInstance(5000, true);

    public static List<ForcastModel> forcastAarrayList = new ArrayList<ForcastModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);
        context = getApplicationContext();

        searchByPlaceName("colombo");
        new CustomUtility().savecurrentCity("colombo", context);
        SetupView();
    }


    private void SetupView(){
        recycleV_forcast = (RecyclerView) findViewById (R.id.recV_weatherlist);
        recycleV_forcast.addItemDecoration(new SimpleDividerItemDecoration(context));
        layoutManager = new LinearLayoutManager(context);
        recycleV_forcast.setLayoutManager(layoutManager);
        forcastAdapter = new WeatherAdapter(context, forcastAarrayList);
        recycleV_forcast.setAdapter(forcastAdapter);

    }


    @Override
    public void gotWeatherInfo(final WeatherInfo weatherInfo, YahooWeather.ErrorType errorType) {

        if (errorType == null) {
            forcastAarrayList.clear();
            int forcastSize = weatherInfo.getForecastInfoList().size();
            if (forcastSize > 0){
                for (int y=0; y< forcastSize; y++){
                    ForcastModel fmodel = new ForcastModel();
                    fmodel.ForecastCode             = weatherInfo.getForecastInfoList().get(y).getForecastCode();
                    fmodel.ForecastConditionIconURL = weatherInfo.getForecastInfoList().get(y).getForecastConditionIconURL();
                    fmodel.ForecastDate             = weatherInfo.getForecastInfoList().get(y).getForecastDate();
                    fmodel.ForecastDay              = weatherInfo.getForecastInfoList().get(y).getForecastDay();
                    fmodel.ForecastTempHigh         = weatherInfo.getForecastInfoList().get(y).getForecastTempHigh();
                    fmodel.ForecastTempLow          = weatherInfo.getForecastInfoList().get(y).getForecastTempLow();
                    fmodel.ForecastText             = weatherInfo.getForecastInfoList().get(y).getForecastText();
                    forcastAarrayList.add(fmodel);
                }
                forcastAdapter.notifyDataSetChanged();
            } else { showErrorMessage("No Forecast!", "No forecast available for the city"); }
        }
    }

    private void searchByPlaceName(String location) {
        if (isNetworkAvailable()) {
            try {
                forcastAarrayList.clear();
                forcastAdapter.notifyDataSetChanged();
            } catch (Exception e){e.printStackTrace();}

            mYahooWeather.setNeedDownloadIcons(true);
            mYahooWeather.setUnit(YahooWeather.UNIT.CELSIUS);
            mYahooWeather.setSearchMode(YahooWeather.SEARCH_MODE.PLACE_NAME);
            mYahooWeather.queryYahooWeatherByPlaceName(getApplicationContext(), location, WeatherList.this);
        } else {showErrorMessage("No connectivity!", "Please check your internet connection");}
    }





    public void showErrorMessage(String title, String data) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(data)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("ok clicked");
                    }
                })
                .setIcon(R.drawable.wrong_icon)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menu_id = item.getItemId();
        switch (menu_id){
            case R.id.menu_refresh:
                searchByPlaceName(new CustomUtility().getCurrentCity(context));
                break;
            case R.id.menu_maps:
                showCityList();
                break;
            case R.id.menu_settings:
                break;
            default:
                break;
        }
        return  true;
    }



    private void showCityList()  {
        final ArrayAdapter<String> citys = new ArrayAdapter<String>(WeatherList.this, android.R.layout.select_dialog_singlechoice);
        citys.add("colombo");
        citys.add("Matara");
        citys.add("Kandy");
        citys.add("Jaffna");
        citys.add("Kilinochchi");
        citys.add("Vavuniya");

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(WeatherList.this);
        builderSingle.setIcon(R.drawable.maps);
        builderSingle.setTitle("Select a city");

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(citys, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getSupportActionBar().setTitle(citys.getItem(which).toString());
                new CustomUtility().savecurrentCity(citys.getItem(which).toString(), context);
                searchByPlaceName(citys.getItem(which));
            }
        });
        builderSingle.show();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
