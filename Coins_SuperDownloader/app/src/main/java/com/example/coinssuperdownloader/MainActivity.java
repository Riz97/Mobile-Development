package com.example.coinssuperdownloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.coinssuperdownloader.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private AppBarConfiguration appBarConfiguration;
private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityMainBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

     ImageView coin1 = (ImageView)findViewById(R.id.imageViewCoin1);
     ImageView coin2 = (ImageView)findViewById(R.id.imageViewCoin2);
     ImageView coin3 = (ImageView)findViewById(R.id.imageViewCoin3);
     ImageView coin4 = (ImageView)findViewById(R.id.imageViewCoin4);
     ImageView coin5 = (ImageView)findViewById(R.id.imageViewCoin5);
     ImageView coin6 = (ImageView)findViewById(R.id.imageViewCoin6);
     ImageView coin7 = (ImageView)findViewById(R.id.imageViewCoin7);
     ImageView coin8 = (ImageView)findViewById(R.id.imageViewCoin8);
     ImageView coin9 = (ImageView)findViewById(R.id.imageViewCoin9);

        setSupportActionBar(binding.toolbar);

        new DownloadImageTask(coin1).execute("https://upload.wikimedia.org/wikipedia/commons/b/b9/1974S_Eisenhower_Obverse.jpg");
        new DownloadImageTask(coin2).execute("https://upload.wikimedia.org/wikipedia/commons/f/f0/1976S_Type1_Eisenhower_Reverse.jpg");
        new DownloadImageTask(coin3).execute("https://upload.wikimedia.org/wikipedia/commons/d/d7/2009NativeAmericanRev.jpg");
        new DownloadImageTask(coin4).execute("https://upload.wikimedia.org/wikipedia/commons/0/0a/George_Washington_Presidential_%241_Coin_obverse.png");
        new DownloadImageTask(coin5).execute("https://upload.wikimedia.org/wikipedia/commons/7/75/Anthony_dollar_coin.jpg");
        new DownloadImageTask(coin6).execute("https://upload.wikimedia.org/wikipedia/commons/1/1a/2006_AESilver_Proof_Obv.png");
        new DownloadImageTask(coin7).execute("https://upload.wikimedia.org/wikipedia/commons/5/54/2003_Sacagawea_Rev.png");
        new DownloadImageTask(coin8).execute("https://upload.wikimedia.org/wikipedia/commons/f/f8/1804_dollar_obverse.PNG");
        new DownloadImageTask(coin9).execute("https://upload.wikimedia.org/wikipedia/commons/f/fa/Presidential_dollar_coin_reverse.png");


        //new DownloadImageTask();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            String error = "https://upload.wikimedia.org/wikipedia/commons/9/95/No_not.png";
            Bitmap mIcon = null;
            Bitmap rotatedBitmap = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
                Matrix matrix = new Matrix();

                for(int i = 0 ; i < 400 ; i++)
                {
                    matrix.postRotate(90);

                    //scaledBitmap = Bitmap.createScaledBitmap(mIcon, 100, 100, true);

                    rotatedBitmap = Bitmap.createBitmap(mIcon, 0, 0, mIcon.getWidth(), mIcon.getHeight(), matrix, true);
                }




            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();

                try {
                    InputStream in = new java.net.URL(error).openStream();
                    rotatedBitmap = BitmapFactory.decodeStream(in);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
            return rotatedBitmap;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }





















































@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        ImageView coin1 = (ImageView)findViewById(R.id.imageViewCoin1);
        ImageView coin2 = (ImageView)findViewById(R.id.imageViewCoin2);
        ImageView coin3 = (ImageView)findViewById(R.id.imageViewCoin3);
        ImageView coin4 = (ImageView)findViewById(R.id.imageViewCoin4);
        ImageView coin5 = (ImageView)findViewById(R.id.imageViewCoin5);
        ImageView coin6 = (ImageView)findViewById(R.id.imageViewCoin6);
        ImageView coin7 = (ImageView)findViewById(R.id.imageViewCoin7);
        ImageView coin8 = (ImageView)findViewById(R.id.imageViewCoin8);
        ImageView coin9 = (ImageView)findViewById(R.id.imageViewCoin9);


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {



            coin1.setImageBitmap(null);
            coin2.setImageBitmap(null);
            coin3.setImageBitmap(null);
            coin4.setImageBitmap(null);
            coin5.setImageBitmap(null);
            coin6.setImageBitmap(null);
            coin7.setImageBitmap(null);
            coin8.setImageBitmap(null);
            coin9.setImageBitmap(null);

            return true;
        }

        if(id == R.id.action_serial)
        {
            new DownloadImageTask(coin1).execute("https://upload.wikimedia.org/wikipedia/commons/b/b9/1974S_Eisenhower_Obverse.jpg");
            new DownloadImageTask(coin2).execute("https://upload.wikimedia.org/wikipedia/commons/f/f0/1976S_Type1_Eisenhower_Reverse.jpg");
            new DownloadImageTask(coin3).execute("https://upload.wikimedia.org/wikipedia/commons/d/d7/2009NativeAmericanRev.jpg");
            new DownloadImageTask(coin4).execute("https://upload.wikimedia.org/wikipedia/commons/0/0a/George_Washington_Presidential_%241_Coin_obverse.png");
            new DownloadImageTask(coin5).execute("https://upload.wikimedia.org/wikipedia/commons/7/75/Anthony_dollar_coin.jpg");
            new DownloadImageTask(coin6).execute("https://upload.wikimedia.org/wikipedia/commons/1/1a/2006_AESilver_Proof_Obv.png");
            new DownloadImageTask(coin7).execute("https://upload.wikimedia.org/wikipedia/commons/5/54/2003_Sacagawea_Rev.png");
            new DownloadImageTask(coin8).execute("https://upload.wikimedia.org/wikipedia/commons/f/f8/1804_dollar_obverse.PNG");
            new DownloadImageTask(coin9).execute("https://upload.wikimedia.org/wikipedia/commons/f/fa/Presidential_dollar_coin_reverse.png");
        }

        if(id == R.id.action_parallel)
        {
            new DownloadImageTask(coin1).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"https://upload.wikimedia.org/wikipedia/commons/b/b9/1974S_Eisenhower_Obverse.jpg");
            new DownloadImageTask(coin2).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"https://upload.wikimedia.org/wikipedia/commons/f/f0/1976S_Type1_Eisenhower_Reverse.jpg");
            new DownloadImageTask(coin3).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"https://upload.wikimedia.org/wikipedia/commons/d/d7/2009NativeAmericanRev.jpg");
            new DownloadImageTask(coin4).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"https://upload.wikimedia.org/wikipedia/commons/0/0a/George_Washington_Presidential_%241_Coin_obverse.png");
            new DownloadImageTask(coin5).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"https://upload.wikimedia.org/wikipedia/commons/7/75/Anthony_dollar_coin.jpg");
            new DownloadImageTask(coin6).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"https://upload.wikimedia.org/wikipedia/commons/1/1a/2006_AESilver_Proof_Obv.png");
            new DownloadImageTask(coin7).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"https://upload.wikimedia.org/wikipedia/commons/5/54/2003_Sacagawea_Rev.png");
            new DownloadImageTask(coin8).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"https://upload.wikimedia.org/wikipedia/commons/f/f8/1804_dollar_obverse.PNG");
            new DownloadImageTask(coin9).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"https://upload.wikimedia.org/wikipedia/commons/f/fa/Presidential_dollar_coin_reverse.png");

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}