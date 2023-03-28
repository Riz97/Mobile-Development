package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private Button B1,B2,B3,B4,B5,B6,B7,B8,B9,B0,Bdot,Bplus,Bminus,Bslash,Bstar,Bclear,Beq;
    private TextView text;

    private int count = 0;
    private static final String TAG = MainActivity.class.getSimpleName();

    private float v1 , v2;

    private boolean addition,subtraction,division,multiplication;

    private String operator;
    private float op1;

    private int len;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        B1 = (Button) findViewById(R.id.B1);
        B2 = (Button) findViewById(R.id.B2);
        B3 = (Button) findViewById(R.id.B3);
        B4 = (Button) findViewById(R.id.B4);
        B5 = (Button) findViewById(R.id.B5);
        B6 = (Button) findViewById(R.id.B6);
        B7 = (Button) findViewById(R.id.B7);
        B8 = (Button) findViewById(R.id.B8);
        B9 = (Button) findViewById(R.id.B9);
        B0 = (Button) findViewById(R.id.B0);
        Bdot = (Button) findViewById(R.id.Bdot);
        Bplus = (Button) findViewById(R.id.Bplus);
        Bminus = (Button) findViewById(R.id.Bminus);
        Bslash = (Button) findViewById(R.id.Bslash);
        Bstar = (Button) findViewById(R.id.Bstar);
        Bclear = (Button) findViewById(R.id.Bclear);
        Beq = (Button) findViewById(R.id.Beq);
        text = (TextView) findViewById((R.id.textView));


        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText() + "1");
            }
        });

        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText() + "2");
            }
        });

        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText() + "3");
            }
        });

        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText() + "4");
            }
        });
        B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText() + "5");
            }
        });

        B6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText() + "6");
            }
        });

        B7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText() + "7");
            }
        });

        B8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText() + "8");
            }
        });

        B9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText() + "9");
            }
        });

        B0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(text.getText() + "0");
            }
        });

        Bdot.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                //Multiple decimal dots handling
                count++;
                if(count <= 1)
                {
                    text.setText(text.getText() + ".");

                }
            }
        });

        Bclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("");
            }
        });

        //If the user leaves empty the first number we insert a 0 by default

        Bplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if(text.getText() == "")
            {
                v1 = 0;
            }

            else
            {
                v1 = Float.parseFloat(text.getText()+"");
            }
            addition = true;
            text.setText(null);

            }
        });

        Bminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(text.getText() == "")
                {
                    v1 = 0;
                }

                else
                {
                    v1 = Float.parseFloat(text.getText()+"");
                }
                subtraction = true;
                text.setText(null);
            }
        });

        Bslash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(text.getText() == "")
                {
                    v1 = 0;
                }

                else
                {
                    v1 = Float.parseFloat(text.getText()+"");
                }
                division = true;
                text.setText(null);
            }
        });

        Bstar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text.getText() == "")
                {
                    v1 = 0;
                }

                else
                {
                    v1 = Float.parseFloat(text.getText()+"");
                }
                multiplication = true;
                text.setText(null);
            }
        });

        Beq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v2 = Float.parseFloat(text.getText()+"");

                if(addition == true)
                {
                    text.setText(v1 + v2 + "");
                    addition = false;
                }

                if(subtraction == true)
                {
                    text.setText(v1 - v2 + "");
                    subtraction = false;
                }

                if(division == true)
                {
                    text.setText(v1 / v2 + "");
                    division = false;
                }

                if(multiplication == true)
                {
                    text.setText(v1 * v2 + "");
                    multiplication = false;
                }
            }
        });




    }
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("text",String.valueOf(text.getText()));
        outState.putBoolean("division",division);
        outState.putBoolean("addition",addition);
        outState.putBoolean("multiplication",multiplication);
        outState.putBoolean("subtraction",subtraction);
        Log.d("DEBUG_TV",String.valueOf(text.getText()));

        outState.putFloat("v1",v1);



    }

    @Override
    protected void onRestoreInstanceState(Bundle mySavedState)
    {
        super.onRestoreInstanceState(mySavedState);
        if(mySavedState != null)
        {
            String text_tv = mySavedState.getString("text");

            v1 = mySavedState.getFloat("v1");
            division = mySavedState.getBoolean("division");
            addition = mySavedState.getBoolean("addition");
            multiplication = mySavedState.getBoolean("multiplication");
            subtraction = mySavedState.getBoolean("subtraction");




        }

    }
}
