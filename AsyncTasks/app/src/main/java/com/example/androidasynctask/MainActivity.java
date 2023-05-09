package com.example.androidasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.*;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    String randomString = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startLocalTask(View view)
    {

        TextView localTaskText = (TextView) findViewById(R.id.textViewStatusLocal);

        for(int progress = 0 ; progress <101 ; progress++)
        {

            try {
                Thread.sleep(40);
                localTaskText.setText("Task Completed");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    }

    public void startTask(View view)
    {
        new startAsync().execute();
    }

    public void generateRandomString(View view) {
        TextView randomStringText = (TextView) findViewById(R.id.textViewRandom);
        int len = 10;
        char[] ch = new char[len];
        Random rd = new Random();
        for (int i = 0; i < len; i++) {
            ch[i] = (char) (rd.nextInt(9) + 97);
            randomString = new String(ch);
            randomStringText.setText(randomString);
        }
    }

        private class startAsync extends AsyncTask<Void, Integer, Void> {

            @Override
            protected Void  doInBackground(Void... voids) {
                try {
                    for(int progress = 0 ; progress <101 ; progress++)
                    {




                    Thread.sleep(40);
                    publishProgress(progress);


                } } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Log.d(LOG_TAG,"Exiting from doInBackground");
                return null;
            }

            protected void onProgressUpdate(Integer... progress) {

                TextView asyncTaskText = (TextView) findViewById(R.id.textViewStatusAsync);

               Integer percentage = progress[0];

                asyncTaskText.setText(Integer.toString(percentage) + "%");

            }

        }
    }