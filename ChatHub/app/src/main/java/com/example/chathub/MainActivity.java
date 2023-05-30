package com.example.chathub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;





// Login window
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Click Listener Sign In button:
        Button signinButton = (Button)findViewById(R.id.buttonSignin);
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signinIntent = new Intent(MainActivity.this, SigninActivity.class);
                startActivity(signinIntent);
            }
        });


        // Click Listener Sign In button:
        Button loginButton = (Button)findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/");
                DatabaseReference myRef = database.getReference("message");

                myRef.setValue("Hello, World!");
            }
        });
    }


}