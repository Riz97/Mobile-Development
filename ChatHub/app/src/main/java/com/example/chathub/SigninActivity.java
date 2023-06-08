package com.example.chathub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SigninActivity extends AppCompatActivity {
    private static final String LOG_TAG = SigninActivity.class.getSimpleName();

    DatabaseReference databaseReference;
    DatabaseReference databaseMessagesReference;

    User newUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        List<String> usernames = new ArrayList<String>();
        List<String> usernamesMessages = new ArrayList<String>();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = database.getReference("Users");
        databaseMessagesReference = database.getReference("Messages");


        EditText editUsername = (EditText) (findViewById(R.id.editTextUsername));
        EditText editPassword = (EditText) (findViewById(R.id.editTextPassword));
        EditText editConfirm = (EditText) (findViewById(R.id.editTextConfirmPassword));
        Button signIn = (Button) findViewById(R.id.buttonSignin);
        final String userEnteredUsername = editUsername.getText().toString().trim();


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(LOG_TAG,"sono qua");


                for(DataSnapshot ds : snapshot.getChildren()) {
                    String usernameFromDB = ds.child(userEnteredUsername).child("username").getValue(String.class);
                    usernames.add(usernameFromDB);
                    usernamesMessages.add(usernameFromDB);

                    Log.d(LOG_TAG,usernames.toString());
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String password = editPassword.getText().toString();
                String username = editUsername.getText().toString();
                String confirmPsw = editConfirm.getText().toString();
                boolean status = false;


                if(usernames.contains(username)) {
                    editUsername.setError("User already Exists");
                    editUsername.requestFocus();
                }


               else if (password.equals(confirmPsw) && !usernames.contains(username)) {
                    newUser = new User(username, password,status);
                    databaseReference.child(username).child("password").setValue(password);
                    databaseReference.child(username).child("username").setValue(username);
                    databaseReference.child(username).child("online").setValue(status);

                    databaseMessagesReference.child(username).child("password").setValue(password);
                    databaseMessagesReference.child(username).child("username").setValue(username);
                    databaseMessagesReference.child(username).child("online").setValue(status);
                    databaseMessagesReference.child(username).child("dest").setValue("");

                    Intent signinIntent = new Intent(SigninActivity.this, MainActivity.class);
                    startActivity(signinIntent);
                    Toast.makeText(SigninActivity.this, "Profile Created Successfully", Toast.LENGTH_LONG).show();
               } else {
                    Toast.makeText(SigninActivity.this, " Error : Inserted Password do not correspond", Toast.LENGTH_LONG).show();
               }
            }
        });
    }
}