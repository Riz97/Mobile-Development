package com.example.chathub;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    String userName,otherName;
    TextView chatUserName;
    ImageView backImage , sendImage;
    EditText chatEditText;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    RecyclerView chatRecyclerView;
    MessageAdapter messageAdapter;
    List<MessageModel> list;
    String userlogged;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ActionBar actionBar = getSupportActionBar();

        Intent intent = getIntent();
        userlogged = intent.getStringExtra("userlogged");

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        userName = getIntent().getExtras().getString("username");
        otherName = getIntent().getExtras().getString("dest");
        sendImage = (ImageView) findViewById(R.id.sendImage);
        chatUserName = (TextView)findViewById(R.id.chatUserName);
        chatEditText = (EditText)findViewById(R.id.chatEditText);
        chatUserName.setText(otherName);
        list = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = firebaseDatabase.getReference();


        /*------------------------------ Send Message Button ----------------------------------------*/
        sendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = chatEditText.getText().toString();
                chatEditText.setText("");
                sendMessage(message);
            }
        });

        /* Creation of the RecyclerView and load all the messages if there are any */
        loadMessage();
        chatRecyclerView = (RecyclerView)findViewById(R.id.chatRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ChatActivity.this, 1);
        chatRecyclerView.setLayoutManager(layoutManager);
        messageAdapter = new MessageAdapter(ChatActivity.this, list, ChatActivity.this,userName);
        chatRecyclerView.setAdapter(messageAdapter);

    }


    /* --------------------------------- Send Message Function ---------------------------------- */
    /* Send  the message and upload it  in the RecyclerView and update  the database of the user logged
    *  creating a child which is the dest and other two children which contains the text of the message and the sender name. */

    public void sendMessage(String text){
        final String key = reference.child("Messages").child(userName).child(otherName).push().getKey();
        final Map messageMap = new HashMap();
        messageMap.put("text" , text);
        messageMap.put("from" , userName);
        //reference.child("Messages").child(userName).child("dest").setValue(otherName);
        reference.child("Messages").child(userName).child(otherName).child(key).setValue(messageMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    reference.child("Messages").child(otherName).child(userName).child(key).setValue(messageMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }
            }
        });

    }

    /* ---------------------------------- Load Message Function --------------------------------- */
    /* Load all the messages in the RecyclerView taken from the database of the user logged */


    public void loadMessage(){
            //reference.child("Messages").child(userName).child(otherName).setValue(otherName);
            reference.child("Messages").child(userName).child(otherName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {
                MessageModel messageModel = snapshot.getValue(MessageModel.class);
                list.add(messageModel);
                messageAdapter.notifyDataSetChanged();
                chatRecyclerView.scrollToPosition(list.size()-1);
            }

            @Override
            public void onChildChanged(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull  DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }

    /* ------------------------------- Back Arrow Functions ------------------------------ */
    /* They handle the behavior of the back arrow when it is pressed and it which activity you will be redirected */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ChatActivity.this, ChatListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("username", userName);
        startActivity(intent);
    }


    /* ------------------------------------- OnPause and OnResume ----------------------------------*/
    /* Set the status of the User , online if the user is inside the activity and offline if the activity is paused */
    @Override
    protected void onPause(){
        super.onPause();
        Intent intent = getIntent();
        String userlogged = intent.getStringExtra("username");
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = database.getReference("Messages");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getRef().child(userlogged).child("online").setValue(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    @Override
    protected void onResume(){
        super.onResume();
        Intent intent = getIntent();
        String userlogged = intent.getStringExtra("username");
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = database.getReference("Messages");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getRef().child(userlogged).child("online").setValue(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}