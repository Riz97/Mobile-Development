package com.example.counterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private double count = 0;
    private TextView ShowCount;
    private EditText Stepsize;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShowCount = (TextView) findViewById(R.id.textCount);
        Stepsize = (EditText) findViewById(R.id.editTextNumberDecimal);
    }

    public void CountUp(View view){
        count = count + Double.parseDouble(Stepsize.getText().toString());
        ShowCount.setText("Value = " + Double.toString(count));
        Log.i(TAG,"Count addition pressed");
    }
    public void CountDown(View view){

        count = count - Double.parseDouble(Stepsize.getText().toString());
        ShowCount.setText("Value = " + Double.toString(count));
        Log.i(TAG,"Count substitution pressed");
    }
}


