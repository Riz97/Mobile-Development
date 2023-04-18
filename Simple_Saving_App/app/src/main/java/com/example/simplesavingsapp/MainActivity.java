package com.example.simplesavingsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;

    int checked ;

    String textUsername , textLocation;
    EditText editUsername;
    EditText editLocation;

    Switch graySwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editUsername = findViewById(R.id.editTextTextUsername);
        editLocation = findViewById(R.id.editTextTextPersonLocation);
        graySwitch = findViewById(R.id.switchColor);

        String sharedPrefFile = "com.example.simplesavingapp";
        mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        String username = mPreferences.getString("username","");
        String location = mPreferences.getString("location","");
        int number = mPreferences.getInt("color",0);

        if(number == 1)
        {
            graySwitch.setChecked(true);
            getWindow().getDecorView().setBackgroundColor(Color.GRAY);

        }

        else if(number == 0){
            graySwitch.setChecked(false);
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        }

        editUsername.setText(username);
        editLocation.setText(location);



    }

    public void saveApp(View view)
    {


        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        textUsername = editUsername.getText().toString();
        textLocation = editLocation.getText().toString();

        preferencesEditor.putString("username",textUsername);
        preferencesEditor.putString("location",textLocation);
        preferencesEditor.putInt("color",checked);

        preferencesEditor.apply();
    }

    public void clearApp(View view)
    {


        SharedPreferences.Editor preferencesEditor = mPreferences.edit();

        editUsername.setText("");
        editLocation.setText("");
        graySwitch.setChecked(false);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);


        preferencesEditor.clear();
        preferencesEditor.apply();

    }

    public void changeBackground(View view)
    {
        if(graySwitch.isChecked() == true)
        {
            getWindow().getDecorView().setBackgroundColor(Color.GRAY);
            checked = 1;
        }

        else
        {
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            checked = 0;
        }
    }

    public void closeApp(View view)
    {
        finishAndRemoveTask();
    }
}