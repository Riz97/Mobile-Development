package com.example.simplecamera;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingP1 extends AppCompatActivity {

    private static final String LOG_TAG = SettingP1.class.getSimpleName();
     Button listenButton;
     TextView text;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_p1);
        Log.d(LOG_TAG, "onCreate");
        listenButton = findViewById(R.id.buttonTestList);


        listenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                text = findViewById(R.id.textViewEvent);
                text.setText("Click Event");
            }

        });

        listenButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view)
            {
                text = findViewById(R.id.textViewEvent);
                text.setText("Long Click Event");
                return true;
            }
        });

    }


    public void returnHome(View v)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void goToSettings2(View v)
    {
        Intent i = new Intent(this,SettingP2.class);
        startActivity(i);
    }



























    @Override
    protected void onStart(){
        super.onStart();
        Log.d(LOG_TAG,"onStart");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(LOG_TAG,"onDestroy");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(LOG_TAG,"onStop");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(LOG_TAG,"onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(LOG_TAG,"onPause");
    }
}