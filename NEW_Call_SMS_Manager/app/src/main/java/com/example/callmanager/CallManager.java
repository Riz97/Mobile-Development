package com.example.callmanager;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class CallManager extends AppCompatActivity {

    private static final String LOG_TAG = CallManager.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_manager);
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

    public void compose(View v)
    {
        EditText edn = findViewById(R.id.editTextPhone3);
        Intent intentImplicit = new Intent(Intent.ACTION_DIAL);
        String uri = "tel:" + edn.getText().toString();
        intentImplicit.setData(Uri.parse(uri));
        startActivity(intentImplicit);

    }

    public void makeCall(View v)
    {
        EditText edn = findViewById(R.id.editTextPhone3);
        Intent intentImplicit = new Intent(Intent.ACTION_CALL);
        String uri = "tel:" + edn.getText().toString();
        intentImplicit.setData(Uri.parse(uri));
        try {
            startActivity(intentImplicit);
        } catch(SecurityException e){
            ActivityCompat.requestPermissions(CallManager.this ,
                    new String[]{Manifest.permission.CALL_PHONE},1);
        }
    }}