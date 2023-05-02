package com.example.simplecamera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TakePicture extends AppCompatActivity {

    public    int pic_id = 1;
    ImageView cameraFrame;
    ImageView cameraFrameOlder;
    Button saveLastPhoto,ExternalPhoto,SDPhoto;
    String currentDateAndTime;
    String currentDateAndTimeOlder;

    private static final String LOG_TAG = SettingP1.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);
        cameraFrame = findViewById(R.id.imageViewCameraLatest);
        cameraFrameOlder = findViewById(R.id.imageViewOlder);
        saveLastPhoto = findViewById(R.id.buttonSaveLastPhoto);
        ExternalPhoto = findViewById(R.id.buttonSaveExt);
        SDPhoto = findViewById(R.id.buttonSaveSD);
        File[] externalStorageVolumes = ContextCompat.getExternalFilesDirs(getApplicationContext(), Environment.DIRECTORY_PICTURES);
        File pathPrimaryExternalStorage = externalStorageVolumes[0];
        File pathSecondaryExternalStorage = externalStorageVolumes[1];

        String pathExt = pathPrimaryExternalStorage.getAbsolutePath();
        String pathSD = pathSecondaryExternalStorage.getAbsolutePath();

        Log.d(LOG_TAG,pathExt);
        Log.d(LOG_TAG,pathSD);


        long freeSpaceExternal = pathPrimaryExternalStorage.getFreeSpace() / (1024 * 1024);
        long freeSpaceExternalSD = pathSecondaryExternalStorage.getFreeSpace() / (1024 * 1024);



        cameraFrame.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                cameraFrame.setImageDrawable(null);

                if(cameraFrameOlder.getDrawable() == null)
                {
                    return false;
                }

                BitmapDrawable bp = (BitmapDrawable) cameraFrameOlder.getDrawable();
                Bitmap bmpOld = bp.getBitmap();

                cameraFrame.setImageBitmap(bmpOld);
                cameraFrameOlder.setImageDrawable(null);
                return false;
            }
        });

        cameraFrameOlder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                cameraFrameOlder.setImageDrawable(null);
                return false;
            }
        });

        cameraFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cameraFrame != null)
                {

                    Context context = getApplicationContext();


                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context,currentDateAndTime , duration);
                    toast.show();
                }
            }
        });

        cameraFrameOlder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cameraFrameOlder != null)
                {

                    Context context = getApplicationContext();


                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, currentDateAndTime, duration);
                    toast.show();
                }
            }
        });

        saveLastPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                File path = context.getFilesDir();
                BitmapDrawable bp = (BitmapDrawable) cameraFrame.getDrawable();
                Bitmap bpp = bp.getBitmap();


                File file = new File(path,"photo.jpg");

                try {
                    FileOutputStream stream = new FileOutputStream(file);
                    bpp.compress(Bitmap.CompressFormat.PNG,100,stream);
                    Log.d(LOG_TAG,"photo saved");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }


            }
        });

        ExternalPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(isExternalStorageWritable())
            {
                String text = "Free Space = " + Long.toString(freeSpaceExternal) + " MB";
                File file = new File(pathPrimaryExternalStorage,"demoPictureEXT.jpg");

                BitmapDrawable bp = (BitmapDrawable) cameraFrame.getDrawable();
                Bitmap bpp = bp.getBitmap();

                try {
                    FileOutputStream stream = new FileOutputStream(file);
                    bpp.compress(Bitmap.CompressFormat.PNG,100,stream);
                    Log.d(LOG_TAG,"photo saved in Secondary External Storage");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                Context context = getApplicationContext();


                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, "Ready", duration);
                toast.show();

                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                    }
                }, 2000);

                }
            }
        });

        SDPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isExternalSDStorageWritable())
                {
                    String text = "Free Space = " + Long.toString(freeSpaceExternalSD) + " MB";
                    File file = new File(pathSecondaryExternalStorage, "demoPictureSD.jpg");


                    BitmapDrawable bp = (BitmapDrawable) cameraFrame.getDrawable();
                    Bitmap bpp = bp.getBitmap();

                    try {
                        FileOutputStream stream = new FileOutputStream(file);
                        bpp.compress(Bitmap.CompressFormat.PNG,100,stream);
                        Log.d(LOG_TAG,"photo saved in SD External Storage");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                    Context context = getApplicationContext();


                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, "Ready", duration);
                    toast.show();

                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);

                }
            }
        });


    }

    public void click(View v)
    {


                cameraFrame = findViewById(R.id.imageViewCameraLatest);
                cameraFrameOlder = findViewById(R.id.imageViewOlder);
                Intent i= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, pic_id);
                pic_id = 2;


    }

    public boolean isExternalSDStorageWritable() {
        File[] externalStorageVolumes = ContextCompat.getExternalFilesDirs(getApplicationContext(), Environment.DIRECTORY_PICTURES);
        File pathSecondaryExternalStorage = externalStorageVolumes [1];
        String state = Environment.getExternalStorageState(pathSecondaryExternalStorage);
        if (Environment.MEDIA_MOUNTED.equals(state))
        {
            return true;
        }
        return false;
    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state))
        {
            return true;
    }
    return false;
}



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK && cameraFrame.getDrawable() == null) {
                    currentDateAndTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
                    Bundle extras = data.getExtras();
                    Bitmap bmp = (Bitmap) extras.get("data");

                    cameraFrame.setImageBitmap(bmp);

                }

                //Handle the case that if you delete an image when you have two of them and then you take another photo
                else  if (resultCode == RESULT_OK && cameraFrame.getDrawable() != null) {
                    currentDateAndTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
                    Bundle extras = data.getExtras();
                    Bitmap bmp = (Bitmap) extras.get("data");


                    BitmapDrawable bp = (BitmapDrawable) cameraFrame.getDrawable();
                    Bitmap bmpOld = bp.getBitmap();


                    cameraFrame.setImageBitmap(bmp);

                    cameraFrameOlder.setImageBitmap(bmpOld);
                    pic_id = 1;
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap bmp = (Bitmap) extras.get("data");


                    BitmapDrawable bp = (BitmapDrawable) cameraFrame.getDrawable();
                    Bitmap bmpOld = bp.getBitmap();


                    cameraFrame.setImageBitmap(bmp);

                    cameraFrameOlder.setImageBitmap(bmpOld);
                    pic_id = 1;
                }
                break;
        }
    }
}
