package com.example.callmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startCall(View v)
    {
        Intent i = new Intent(this,CallManager.class);
        startActivity(i);
    }

    public  void startSMS(View v)
    {
        Intent i = new Intent(this,SMSManager.class);
        startActivity(i);
    }

    public void startIntCall(View v)
    {
        Intent i = new Intent(this,InternationalCall.class);
        startActivity(i);
    }
}