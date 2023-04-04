package com.example.simplecamera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.icu.util.Calendar;
import android.media.ExifInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakePicture extends AppCompatActivity {

    public    int pic_id = 1;
    ImageView cameraFrame;
    ImageView cameraFrameOlder;
    String currentDateAndTime;
    String currentDateAndTimeOlder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);
        cameraFrame = findViewById(R.id.imageViewCameraLatest);
        cameraFrameOlder = findViewById(R.id.imageViewOlder);

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
    }

    public void click(View v)
    {


                cameraFrame = findViewById(R.id.imageViewCameraLatest);
                cameraFrameOlder = findViewById(R.id.imageViewOlder);
                Intent i= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, pic_id);
                pic_id = 2;


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
