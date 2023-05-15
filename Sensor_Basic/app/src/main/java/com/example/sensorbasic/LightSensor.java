package com.example.sensorbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class LightSensor extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor mLight;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

    }

    @Override
    public final void onSensorChanged(SensorEvent event)
    {
        float lux = event.values[0];

        Log.d(LOG_TAG,Float.toString(lux));

        TextView text = findViewById(R.id.textViewLightValue);
        text.setText(Float.toString(lux));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
     // Do something here if sensor accuracy changes.
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        // Put this line in OnCreate if you do not want to stop and resume every time the sensor
        sensorManager.registerListener(this,mLight,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}