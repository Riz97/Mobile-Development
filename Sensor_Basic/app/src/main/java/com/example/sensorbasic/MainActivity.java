package com.example.sensorbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);



        int countSensors = deviceSensors.size();

        Log.d(LOG_TAG,Integer.toString(countSensors));
        //ScrollView scroll = (ScrollView) findViewById(R.id.scrollVir)
        TextView numberOfSensors = (TextView) findViewById(R.id.textViewNumberSensors);
        TextView scrollSensors = (TextView) findViewById(R.id.textViewScroll);
        numberOfSensors.setText( numberOfSensors.getText() + Integer.toString(countSensors));


        for(Sensor sensor : deviceSensors)
        {
           String name = sensor.getName();
            scrollSensors.append(name+ "\n \n");
            Log.d(LOG_TAG, name);
        }








    }


    public void lightSensorValuesActivity(View view)
    {
        Intent i = new Intent(this,LightSensor.class);
        startActivity(i);
    }

    public void chartAcccelorometer(View view)
    {
        Intent i = new Intent(this,AccelerometerSensor.class);
        startActivity(i);
    }
}