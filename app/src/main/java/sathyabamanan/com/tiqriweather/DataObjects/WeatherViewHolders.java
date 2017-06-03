package sathyabamanan.com.tiqriweather.DataObjects;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

import sathyabamanan.com.tiqriweather.R;
import sathyabamanan.com.tiqriweather.WeatherDetail;
import sathyabamanan.com.tiqriweather.WeatherList;

import static sathyabamanan.com.tiqriweather.WeatherList.forcastAarrayList;

/**
 * Created by baman on 6/3/17.
 */

public class WeatherViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView temperature;
    public ImageView weatherImage;
    public TextView message;
    public TextView cus_date;
    public TextView forcastId;
    public Context context;


    public WeatherViewHolders(View itemView, Context context) {
        super(itemView);
        this.context = context;
        itemView.setOnClickListener(this);

        temperature = (TextView) itemView.findViewById(R.id.cus_temperature);
        weatherImage = (ImageView) itemView.findViewById(R.id.cus_image);
        message = (TextView) itemView.findViewById(R.id.cus_message);
        cus_date = (TextView) itemView.findViewById(R.id.cus_date);
        forcastId = (TextView) itemView.findViewById(R.id.forcast_id);
    }

    @Override
    public void onClick(View view) {
        String forcastcode = forcastId.getText().toString();
        ForcastModel fmodel = new ForcastModel();

            for (int k=0; k< WeatherList.forcastAarrayList.size(); k++){
                if (String.valueOf(WeatherList.forcastAarrayList.get(k).ForecastCode).equals(forcastcode)) fmodel = WeatherList.forcastAarrayList.get(k);
             }

        Intent intent = new Intent(view.getContext(), WeatherDetail.class);
        intent.putExtra("code", String.valueOf(fmodel.ForecastCode));
        intent.putExtra("imageUrl", String.valueOf(fmodel.ForecastConditionIconURL));
        intent.putExtra("Date", String.valueOf(fmodel.ForecastDate));
        intent.putExtra("day", String.valueOf(fmodel.ForecastDay));
        intent.putExtra("high", String.valueOf(fmodel.ForecastTempHigh));
        intent.putExtra("low", String.valueOf(fmodel.ForecastTempLow));
        intent.putExtra("text", String.valueOf(fmodel.ForecastText));
        view.getContext().startActivity(intent);


    }
}
