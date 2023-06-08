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


// Login window
public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    DatabaseReference databaseReference;
    private SharedPreferences mPreferences;

    DatabaseReference databaseMessagesReference;
    User newUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = database.getReference("Users");

        String sharedPrefFile = "com.example.chathub";
        mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);

        databaseMessagesReference = database.getReference("Messages");


        int logged = mPreferences.getInt("logged",0);



        if(logged == 1)
        {
            Intent signinIntent = new Intent(MainActivity.this, ChatListActivity.class);
            startActivity(signinIntent);
        }




        EditText editUsername = (EditText) (findViewById(R.id.editTextUsername));
        EditText editPassword = (EditText) (findViewById(R.id.editTextPassword));





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


                final String userEnteredUsername = editUsername.getText().toString().trim();
                final String userEnteredPassword = editPassword.getText().toString().trim();
                DatabaseReference reference = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Messages");
                Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);
                            if (passwordFromDB.equals(userEnteredPassword)) {
                                dataSnapshot.getRef().child(userEnteredUsername).child("online").setValue(true);


                                Intent signinIntent = new Intent(MainActivity.this, ChatListActivity.class);
                                signinIntent.putExtra("username",userEnteredUsername);

                                startActivity(signinIntent);
                            } else {
                                editPassword.setError("Wrong Password");
                                editPassword.requestFocus();
                            }
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