package com.example.callmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;


public class InternationalCall extends AppCompatActivity {

    private static final String LOG_TAG = InternationalCall.class.getSimpleName();
    public final static String EXTRA_REPLY = "com.example.Lab5.RETURN_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_international_call);
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

    public void linkWiki(View v)
    {
        Uri uri = Uri.parse("https://en.wikipedia.org/wiki/List_of_international_call_prefixes");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }

    public void choosePrefix(View v)
    {
        RadioButton ita = (RadioButton) findViewById(R.id.radioButtonIta);
        RadioButton ger = (RadioButton) findViewById(R.id.radioButtonGer);
        RadioButton fra = (RadioButton) findViewById(R.id.radioButtonFr);
        Intent replyIntent = new Intent();
        String prefix = null;

    if(ita.isChecked())
    {
        prefix = "+39";

    }
        if(ger.isChecked())
        {
            prefix = "+49";

        }
        if(fra.isChecked())
        {
            prefix = "+33";

        }

        replyIntent.putExtra(EXTRA_REPLY,prefix);
        setResult(RESULT_OK,replyIntent);
        finish();







    }
}