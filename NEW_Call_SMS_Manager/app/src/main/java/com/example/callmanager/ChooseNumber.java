package com.example.callmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class ChooseNumber extends AppCompatActivity {

    public final static String EXTRA_REPLY = "com.example.Lab5.RETURN_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_number);
    }

    public void chooseNumber(View v)
    {
        RadioButton Emergency = (RadioButton) findViewById(R.id.radioButtonEmergency);
        RadioButton Fire = (RadioButton) findViewById(R.id.radioButtonFire);
        RadioButton Police = (RadioButton) findViewById(R.id.radioButtonPolice);
        Intent replyIntent = new Intent();
        String number = null;

        if(Emergency.isChecked())
        {
            number = "111111";

        }
        if(Fire.isChecked())
        {
            number = "222222";

        }
        if(Police.isChecked())
        {
            number = "333333";

        }

        replyIntent.putExtra(EXTRA_REPLY,number);
        setResult(RESULT_OK,replyIntent);
        finish();







    }
}