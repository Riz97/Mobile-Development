package com.example.callmanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

public class SMSManager extends AppCompatActivity {

    private static final String LOG_TAG = SMSManager.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsmanager);
        Log.d(LOG_TAG,"onCreate");
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


    public void sendSMS(View v) {
        EditText eds = (EditText) findViewById(R.id.editTextSMS);
        EditText edn = (EditText) findViewById(R.id.editTextNumberSMS);

        String uri = "smsto:" + edn.getText().toString();
        String text = eds.getText().toString();

        Intent it = new Intent(Intent.ACTION_SENDTO,Uri.parse(uri));
        it.putExtra("sms_body",text);


        startActivity(it);
    }

}