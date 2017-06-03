package sathyabamanan.com.tiqriweather;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;

public class MainActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                MainActivity.this.startActivity(new Intent(MainActivity.this, WeatherList.class));
                MainActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
