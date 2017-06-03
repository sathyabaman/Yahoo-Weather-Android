package sathyabamanan.com.tiqriweather;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import sathyabamanan.com.tiqriweather.Common.CustomUtility;

public class Settings extends AppCompatActivity {
    Spinner metricSpinner;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        context = getApplicationContext();

        metricSpinner = (Spinner) findViewById(R.id.settingsSpinner);

        if (new CustomUtility().getCurrentMetricsytem(context).equals("no_matric")){
            metricSpinner.setSelection(1);
        }

        metricSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                 String newValue = (String) metricSpinner.getItemAtPosition(position);
                 new CustomUtility().saveMetricsytem(newValue, context);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

    }
}
