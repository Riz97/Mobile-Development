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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //Lists that contain all the users
        List<String> usernames = new ArrayList<String>();
        List<String> usernamesMessages = new ArrayList<String>();

        /*-------------------------- Database Initializations ----------------------- */

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = database.getReference("Users");
        databaseMessagesReference = database.getReference("Messages");


        /*------------------------ Buttons and Editext Initializations -----------------------------*/
        
        EditText editUsername = (EditText) (findViewById(R.id.editTextUsername));
        EditText editPassword = (EditText) (findViewById(R.id.editTextPassword));
        EditText editConfirm = (EditText) (findViewById(R.id.editTextConfirmPassword));
        Button signIn = (Button) findViewById(R.id.buttonSignin);

        final String userEnteredUsername = editUsername.getText().toString().trim();

        // In this part we populate the lists. In this way users cannot create profiles with the same username

        databaseMessagesReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

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

        /*--------------------------- SignIn Button ------------------------*/
        /*It takes the password ,username and the confirmation of the password and save them in three variables
        then it checks that the user is not already in the Database, and then if password and confirmpassword mathc
        it will be created in the database a new user with that characteristics and you will be redirected to
        the MainActivity , where you can login*/

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

                    databaseMessagesReference.child(username).child("password").setValue(password);
                    databaseMessagesReference.child(username).child("username").setValue(username);
                    databaseMessagesReference.child(username).child("online").setValue(status);

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