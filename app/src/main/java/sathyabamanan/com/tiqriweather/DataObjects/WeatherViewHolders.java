package sathyabamanan.com.tiqriweather.DataObjects;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import sathyabamanan.com.tiqriweather.R;

/**
 * Created by baman on 6/3/17.
 */

public class WeatherViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView temperature;
    public ImageView weatherImage;
    public TextView message;
    public TextView cus_date;
    public Context context;

    public WeatherViewHolders(View itemView, Context context) {
        super(itemView);
        this.context = context;
        itemView.setOnClickListener(this);

        temperature = (TextView) itemView.findViewById(R.id.cus_temperature);
        weatherImage = (ImageView) itemView.findViewById(R.id.cus_image);
        message = (TextView) itemView.findViewById(R.id.cus_message);
        cus_date = (TextView) itemView.findViewById(R.id.cus_date);
    }

    @Override
    public void onClick(View view) {

    }
}
