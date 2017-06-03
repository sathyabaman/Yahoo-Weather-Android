package sathyabamanan.com.tiqriweather;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import sathyabamanan.com.tiqriweather.Common.CustomUtility;
import sathyabamanan.com.tiqriweather.DataObjects.ForcastModel;

public class WeatherDetail extends AppCompatActivity {

    private String code;
    private String imageUrl;
    private String Date;
    private String day;
    private String high;
    private String low;
    private String text;
    public Context context;

    private TextView title;
    private TextView message;
    private TextView detail_high;
    private TextView temperature_type;
    private TextView today_high;
    private TextView today_low;
    private ImageView weather_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        context = getApplicationContext();

        Intent intent = getIntent();
        code = intent.getStringExtra("code");
        imageUrl = intent.getStringExtra("imageUrl");
        Date = intent.getStringExtra("Date");
        day = intent.getStringExtra("day");
        high = intent.getStringExtra("high");
        low = intent.getStringExtra("low");
        text = intent.getStringExtra("text");


        title = (TextView) findViewById(R.id.detail_title);
        message = (TextView) findViewById(R.id.detail_message);
        detail_high = (TextView) findViewById(R.id.detail_high_temp);
        temperature_type = (TextView) findViewById(R.id.detail_temp_type);
        today_high = (TextView) findViewById(R.id.details_tdy_high);
        today_low = (TextView) findViewById(R.id.details_tdy_low);
        weather_image = (ImageView) findViewById(R.id.detail_image);


        String metrisystem = new CustomUtility().getCurrentMetricsytem(context);

        if (metrisystem.equals("Fahrenheit")) {
            detail_high.setText(fahrenheitConversion(Integer.parseInt(high)) + "°");
            today_high.setText("Today's highest : " + fahrenheitConversion(Integer.parseInt(high))+ "°");
            today_low.setText("Today's Lowest : " + fahrenheitConversion(Integer.parseInt(low))+ "°");
        } else {
            detail_high.setText(high + "°");
            today_high.setText("Today's highest : " + high+ "°");
            today_low.setText("Today's Lowest : " + low+ "°");
        }

        title.setText(Date + "@ " + new CustomUtility().getCurrentCity(context));
        message.setText(text);
        Picasso.with(context).load(imageUrl).into(weather_image);
        temperature_type.setText(new CustomUtility().getCurrentMetricsytem(context));


    }


    private String fahrenheitConversion(int value){
        return String.valueOf(32 + (value * 9 / 5));
    }
}

