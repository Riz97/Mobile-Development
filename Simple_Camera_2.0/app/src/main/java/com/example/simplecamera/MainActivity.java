package com.example.simplecamera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG,"onCreate");
        File[] externalStorageVolumes = ContextCompat.getExternalFilesDirs(getApplicationContext(), Environment.DIRECTORY_PICTURES);
        File pathPrimaryExternalStorage = externalStorageVolumes[0];
        File pathSecondaryExternalStorage = externalStorageVolumes[1];


        Context context = getApplicationContext();
        File path = context.getFilesDir();




        try {
            File file = new File(path,"photo.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(file));
            ImageView img = (ImageView) findViewById(R.id.imageViewMainLatest);
            img.setImageBitmap(b);

            File fileExt = new File(pathPrimaryExternalStorage,"demoPictureEXT.jpg");
            Bitmap bExt = BitmapFactory.decodeStream(new FileInputStream(fileExt));
            ImageView imgExt = (ImageView) findViewById(R.id.imageViewExt);
            imgExt.setImageBitmap(bExt);

            File fileSD = new File(pathSecondaryExternalStorage,"demoPictureSD.jpg");
            Bitmap bSD = BitmapFactory.decodeStream(new FileInputStream(fileSD));
            ImageView imgSD = (ImageView) findViewById(R.id.imageViewSD);
            imgSD.setImageBitmap(bSD);




        } catch (FileNotFoundException e) {
            //throw new RuntimeException(e);
        }


    }

    public void cameraSettings(View v)
    {
        Intent i = new Intent(this,SettingP1.class);
        startActivity(i);
    }

    public void takePicture(View v)
    {
        Intent i = new Intent(this, TakePicture.class);
        startActivity(i);
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

}