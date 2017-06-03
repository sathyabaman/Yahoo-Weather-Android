package sathyabamanan.com.tiqriweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class WeatherList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);
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
