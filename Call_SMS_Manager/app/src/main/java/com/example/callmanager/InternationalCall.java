package com.example.callmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;


public class InternationalCall extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_international_call);
    }

    public void choosePrefix(View v)
    {
        RadioButton ita = (RadioButton) findViewById(R.id.radioButtonIta);
        RadioButton ger = (RadioButton) findViewById(R.id.radioButtonGer);
        RadioButton fra = (RadioButton) findViewById(R.id.radioButtonFr);
        Intent i = new Intent(this,CallManagerInternational4.class);
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

        i.putExtra("prefix",prefix);
        startActivity(i);







    }
}