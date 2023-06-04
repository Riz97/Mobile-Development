package com.example.chathub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        String dest = getIntent().getStringExtra("USERNAME");

        TextView contact = (TextView) findViewById(R.id.textViewContact);
        contact.append(dest);

    }
}