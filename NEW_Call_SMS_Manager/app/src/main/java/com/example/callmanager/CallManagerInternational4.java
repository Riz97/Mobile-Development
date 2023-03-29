package com.example.callmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class CallManagerInternational4 extends AppCompatActivity {

    private static final String LOG_TAG = CallManagerInternational4.class.getSimpleName();
    public static final int CHOOSE_PREFIX = 1;

    public static final int CHOOSE_SPECIAL_NUMBER = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_manager_international4);
        Intent intent = getIntent();
        String prefix = intent.getStringExtra("prefix");
        EditText prefixText = findViewById(R.id.editTextPhone);
        prefixText.setText(prefix);
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

        public void prefix(View v)
        {


            Intent intent = new Intent(this,InternationalCall.class);
            startActivityForResult(intent,CHOOSE_PREFIX);
        }

        public void telNumberInternational(View v)
        {
            Intent intent = new Intent(this,ChooseNumber.class);
            startActivityForResult(intent,CHOOSE_SPECIAL_NUMBER);
        }

        @Override
        public void onActivityResult(int requestCode,int resultCode,Intent data)
        {
            super.onActivityResult(requestCode,resultCode,data);
            EditText prefixText = findViewById(R.id.editTextPhone);
            EditText numberText = findViewById(R.id.editTextPhone2);

            if(requestCode == CHOOSE_PREFIX)
            {
                if(resultCode == RESULT_OK)
                {
                    String reply = data.getStringExtra(InternationalCall.EXTRA_REPLY);
                    prefixText.setText(reply);
                }
            }

            if(requestCode == CHOOSE_SPECIAL_NUMBER)
            {
                if(resultCode == RESULT_OK)
                {
                    String reply = data.getStringExtra(ChooseNumber.EXTRA_REPLY);
                    numberText.setText(reply);
                }
            }
        }

        public void compose(View v)
        {
            EditText prefixText = findViewById(R.id.editTextPhone);
            String prefix = prefixText.getText().toString();
            EditText edn = findViewById(R.id.editTextPhone2);
            Intent intentImplicit = new Intent(Intent.ACTION_DIAL);
            String uri = "tel:"+ prefix + edn.getText().toString();
            intentImplicit.setData(Uri.parse(uri));
            startActivity(intentImplicit);
        }

        public void makeCall(View v)
        {
            EditText prefixText = findViewById(R.id.editTextPhone);
            String prefix = prefixText.getText().toString();
            EditText edn = findViewById(R.id.editTextPhone2);
            Intent intentImplicit = new Intent(Intent.ACTION_CALL);
            String uri = "tel:"+ prefix + edn.getText().toString();
            intentImplicit.setData(Uri.parse(uri));
            try
            {
                startActivity(intentImplicit);
            } catch(SecurityException e)
            {
                ActivityCompat.requestPermissions(CallManagerInternational4.this ,
                        new String[]{Manifest.permission.CALL_PHONE},1);
            }
        }
}