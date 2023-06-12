package com.example.chathub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


// Login Activity:
// Here we implemented the first Activity you will see when you open the app.
// In the Login Activity you have two EditText elements in which you can insert your username and password.
// If you don't have an account you tap on the "SignIn" button and you will be redirected to the SignInActivity.
public class MainActivity extends AppCompatActivity {


    private SharedPreferences mPreferences;
    DatabaseReference databaseMessagesReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference to the Firebase Realtime Database:
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseMessagesReference = database.getReference("Messages");


        /*--------------------- Shared Preferences for the Dialog Box -------------------------------------*/
        // When you login you can decide whether to stay logged in or not.
        String sharedPrefFile = "com.example.chathub";
        mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        int logged = mPreferences.getInt("logged",0);
        if(logged == 1)
        {
            Intent signinIntent = new Intent(MainActivity.this, ChatListActivity.class);
            startActivity(signinIntent);
        }


        /*--------------------- SignIn Button -------------------------------- */

        // Click Listener for the Sign In button:
        Button signinButton = (Button)findViewById(R.id.buttonSignin);
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If you don't have an account you can click on this button and create one in the SignInActivity.
                Intent signinIntent = new Intent(MainActivity.this, SigninActivity.class);
                startActivity(signinIntent);
            }
        });



        /*--------------------- Login Button -------------------------------- */

        //It searches in the database user and password inserted in the EdiTexts.
        EditText editUsername = (EditText) (findViewById(R.id.editTextUsername));
        EditText editPassword = (EditText) (findViewById(R.id.editTextPassword));

        // Click Listener for the Sign In button:
        Button loginButton = (Button)findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the username and paswword inserted in the EdiTexts:
                final String userEnteredUsername = editUsername.getText().toString().trim();
                final String userEnteredPassword = editPassword.getText().toString().trim();

                // Reference to the Firebase Realtime Database:
                DatabaseReference reference = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Messages");
                Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            // Take the password from the Database:
                            String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                            // Check if the entered password is correct:
                            if (passwordFromDB.equals(userEnteredPassword)) {
                                dataSnapshot.getRef().child(userEnteredUsername).child("online").setValue(true);

                                Intent signinIntent = new Intent(MainActivity.this, ChatListActivity.class);
                                signinIntent.putExtra("username",userEnteredUsername);

                                startActivity(signinIntent);

                            } else {
                                editPassword.setError("Wrong Password");
                                editPassword.requestFocus();
                            }

                            //Checks if the user exists
                        } else {
                            editUsername.setError("No such User exist");
                            editUsername.requestFocus();
                       }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}