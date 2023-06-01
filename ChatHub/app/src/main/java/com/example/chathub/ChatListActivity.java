package com.example.chathub;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChatListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);


        // Click Listener for the "New Chat" button
        Button newChatButton = (Button)findViewById(R.id.buttonNewChat);
        newChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // New Chat
                Log.d("New Chat", "ok");
            }
        });

        // Click Listener for the "Logout" button
        Button logoutButton = (Button)findViewById(R.id.buttonLogout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logout
                Log.d("Logout", "ok");
            }
        });


        // Dialog box: https://code2care.org/2015/android-alertdialog-with-3-buttons-example
        AlertDialog.Builder builder = new AlertDialog.Builder(ChatListActivity.this);
        builder.setTitle("AlertDialog Example");
        builder.setMessage("This is an Example of Android AlertDialog with 3 Buttons!!");
        
        //Button One : Yes
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ChatListActivity.this, "Yes button Clicked!", Toast.LENGTH_LONG).show();
            }
        });


        //Button Two : No
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ChatListActivity.this, "No button Clicked!", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        AlertDialog diag = builder.create();
        diag.show();
    }

}