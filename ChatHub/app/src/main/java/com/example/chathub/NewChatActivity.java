package com.example.chathub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewChatActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    String userlogged;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chat);

        ActionBar actionBar = getSupportActionBar();

        Intent intent = getIntent();
        userlogged = intent.getStringExtra("userlogged");

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        Button newChatButton = (Button)findViewById(R.id.buttonNew);

        SearchView searchView = findViewById(R.id.searchView);
        ListView listView = findViewById(R.id.listview);


        List<String> usernames = new ArrayList<String>();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = database.getReference("Messages");


        /* Filling up a list that contains all the users in the database */
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds : snapshot.getChildren()) {
                    String usernameFromDB = ds.child("username").getValue(String.class);
                    usernames.add(usernameFromDB);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

     ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,usernames);
     listView.setAdapter(adapter);

     /* --------------------SearchView Behavior ------------------------- */

     searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

         /* If the name does not exists it will raise a toast   */
    @Override
    public boolean onQueryTextSubmit(String s) {
        if(usernames.contains(s)){
            adapter.getFilter().filter(s);
        }else{
            Toast.makeText(NewChatActivity.this, "No Match found",Toast.LENGTH_LONG).show();

        }
        return false;
    }

    /* If the user exists ,  make it visible */
    @Override
    public boolean onQueryTextChange(String s) {
        String text = s;
        adapter.getFilter().filter(text);
        if(TextUtils.isEmpty(text)){
            listView.setVisibility(View.GONE);
        }
        else {
            listView.setVisibility(View.VISIBLE);

        }
        return true;
    }

     });

     /* If you click on the user suggestion , it will be automatically written inside the searchView */

   listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String s = null;
             Object item  = adapter.getItem(i);
             s = item.toString();
             searchView.setQuery(s,true);
       }
   });

   /* -------------------------- New Chat Button Behavior ----------------------------------- */

   newChatButton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           Intent i = new Intent(NewChatActivity.this, ChatActivity.class);
           String s = searchView.getQuery().toString();
           i.putExtra("username", userlogged);
           i.putExtra("dest", s);
           i.putExtra("new", 1);
           //databaseReference.child(userlogged).child("dest").setValue(s);

           if (usernames.contains(s)) {
               adapter.getFilter().filter(s);
               databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       String s = searchView.getQuery().toString();
                       i.putExtra("dest", s);

                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });


               startActivity(i);

           } else {
               Toast.makeText(NewChatActivity.this, "No Match found", Toast.LENGTH_LONG).show();

           }


       }});




    }

    /* ------------------Back Arrow Button -------------------------*/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /* ------------------------------------- OnPause and OnResume ----------------------------------*/
    /* Set the status of the User , online if the user is inside the activity and offline if the activity is paused */
    @Override
    protected void onPause(){
        super.onPause();
        //Intent intent = getIntent();
        //String userlogged = intent.getStringExtra("username");
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
        //Intent intent = getIntent();
        //String userlogged = intent.getStringExtra("username");
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