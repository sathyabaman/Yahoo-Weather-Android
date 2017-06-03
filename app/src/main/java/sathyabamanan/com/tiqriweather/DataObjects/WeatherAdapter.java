package sathyabamanan.com.tiqriweather.DataObjects;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import sathyabamanan.com.tiqriweather.R;

/**
 * Created by baman on 6/3/17.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolders> {
    private List<ForcastModel> forcasts;
    private Context context;

    public WeatherAdapter(Context contet, List<ForcastModel> forcastsArrayList) {
        this.forcasts = forcastsArrayList;
        this.context = contet;
    }

    @Override
    public WeatherViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_single_forcast, null);
        WeatherViewHolders rcv = new WeatherViewHolders(layoutView, context);
        return rcv;
    }

    @Override
    public void onBindViewHolder(WeatherViewHolders holder, int position) {
        holder.temperature.setText(String.valueOf(forcasts.get(position).ForecastTempHigh)+"°");
        Picasso.with(context).load(forcasts.get(position).ForecastConditionIconURL).resize(30, 30).into(holder.weatherImage);
        holder.message.setText(forcasts.get(position).ForecastText);
        holder.cus_date.setText(forcasts.get(position).ForecastDate);
    }


    @Override
    public int getItemCount() {
        return forcasts.size();
    }



}