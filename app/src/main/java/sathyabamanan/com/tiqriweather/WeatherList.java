package sathyabamanan.com.tiqriweather;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import sathyabamanan.com.tiqriweather.Common.CustomUtility;
import sathyabamanan.com.tiqriweather.DataObjects.ForcastModel;
import zh.wang.android.yweathergetter4a.WeatherInfo;
import zh.wang.android.yweathergetter4a.YahooWeather;
import zh.wang.android.yweathergetter4a.YahooWeatherInfoListener;


import java.util.ArrayList;
import java.util.List;

public class WeatherList extends AppCompatActivity implements YahooWeatherInfoListener {


    Context context;
    private YahooWeather mYahooWeather = YahooWeather.getInstance(5000, true);

    public static List<ForcastModel> forcastAarrayList = new ArrayList<ForcastModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);
        context = getApplicationContext();

        searchByPlaceName("colombo");
    }

    @Override
    public void gotWeatherInfo(final WeatherInfo weatherInfo, YahooWeather.ErrorType errorType) {
        if (errorType == null) {
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
            } else { showErrorMessage("No Forecast!", "No forecast available for the city"); }
        }
    }

    private void searchByPlaceName(String location) {
        mYahooWeather.setNeedDownloadIcons(true);
        mYahooWeather.setUnit(YahooWeather.UNIT.CELSIUS);
        mYahooWeather.setSearchMode(YahooWeather.SEARCH_MODE.PLACE_NAME);
        mYahooWeather.queryYahooWeatherByPlaceName(getApplicationContext(), location, WeatherList.this);
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
                break;
            case R.id.menu_maps:
                break;
            case R.id.menu_settings:
                break;
            default:
                break;
        }
        return  true;
    }


}
