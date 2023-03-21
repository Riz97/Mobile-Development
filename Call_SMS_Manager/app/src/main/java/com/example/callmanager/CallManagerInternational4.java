package com.example.callmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CallManagerInternational4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_manager_international4);
        Intent intent = getIntent();
        String prefix = intent.getStringExtra("prefix");
        EditText prefixText = findViewById(R.id.editTextPhone);
        prefixText.setText(prefix);

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