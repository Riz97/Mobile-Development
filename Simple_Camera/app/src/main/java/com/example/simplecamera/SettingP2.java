package com.example.simplecamera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SettingP2 extends AppCompatActivity {


    private static final String LOG_TAG = SettingP2.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_p2);
        Log.d(LOG_TAG,"onCreate");
    }

    public void returnSettings1(View v)
    {
        Intent i = new Intent(this,SettingP1.class);
        startActivity(i);
    }

    public void goToSettings3(View v)
    {
        Intent i = new Intent(this,SettingP3.class);
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