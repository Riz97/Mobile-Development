package com.example.callmanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SMSManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsmanager);
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