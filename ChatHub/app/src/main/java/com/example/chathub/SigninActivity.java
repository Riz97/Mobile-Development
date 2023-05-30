package com.example.chathub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

import java.util.UUID;

public class SigninActivity extends AppCompatActivity {
    private static final String LOG_TAG = SigninActivity.class.getSimpleName();

    DatabaseReference databaseReference;

    User newUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = database.getReference("Users");


        EditText editUsername = (EditText) (findViewById(R.id.editTextUsername));
        EditText editPassword = (EditText) (findViewById(R.id.editTextPassword));
        EditText editConfirm = (EditText) (findViewById(R.id.editTextConfirmPassword));
        Button signIn = (Button) findViewById(R.id.buttonSignin);




        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String password = editPassword.getText().toString();
                String username = editUsername.getText().toString();
                String confirmPsw = editConfirm.getText().toString();




                if(password.equals(confirmPsw))
                {
                    newUser = new User(username,password);
                    databaseReference.child(username).child("password").setValue(password);
                    databaseReference.child(username).child("username").setValue(username);
                    Toast.makeText(SigninActivity.this,"Profile Created Successfully",Toast.LENGTH_LONG).show();
                }

                else {


                    Toast.makeText(SigninActivity.this," Error : Inserted Password do not correspond",Toast.LENGTH_LONG).show();


                }



            }
        });
    }




}