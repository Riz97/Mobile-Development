package com.example.chathub;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChatListActivity extends AppCompatActivity {
    private static final String LOG_TAG = ChatListActivity.class.getSimpleName();

    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);


        String sharedPrefFile = "com.example.chathub";
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        // Click Listener for the "New Chat" button
        Button newChatButton = (Button) findViewById(R.id.buttonNewChat);
        newChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // New Chat
                Log.d("New Chat", "ok");
            }
        });

        // Click Listener for the "Logout" button
        Button logoutButton = (Button) findViewById(R.id.buttonLogout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logout
                Log.d("Logout", "ok");
                Intent logoutIntent = new Intent(ChatListActivity.this, MainActivity.class);
                startActivity(logoutIntent);
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.clear();
                preferencesEditor.apply();
            }
        });


        int logged = mPreferences.getInt("logged", 0);
        if (logged == 0) {


            AlertDialog.Builder builder = new AlertDialog.Builder(ChatListActivity.this);
            builder.setTitle("Logout Preference");
            builder.setMessage("Would like to stay logged  with the same account when you reopen the app?");

            //Button One : Yes
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                    preferencesEditor.putInt("logged", 1);
                    preferencesEditor.apply();


                }
            });


            //Button Two : No
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog diag = builder.create();
            diag.show();
        }
    }
}