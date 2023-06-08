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

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chat);
        String text;
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        Button newChatButton = (Button)findViewById(R.id.buttonNew);

        SearchView searchView = findViewById(R.id.searchView);
        ListView listView = findViewById(R.id.listview);


        List<String> usernames = new ArrayList<String>();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = database.getReference("Messages");


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
     searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
    @Override
    public boolean onQueryTextSubmit(String s) {
        if(usernames.contains(s)){
            adapter.getFilter().filter(s);
        }else{
            Toast.makeText(NewChatActivity.this, "No Match found",Toast.LENGTH_LONG).show();

        }

        return false;
    }

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

   listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String s = null;
             Object item  = adapter.getItem(i);
             s = item.toString();
             searchView.setQuery(s,true);
       }
   });

   newChatButton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           Intent i = new Intent(NewChatActivity.this, ChatListActivity.class);
           String s = searchView.getQuery().toString();
           i.putExtra("dest",s);
           i.putExtra("new",1);

           databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   String s = searchView.getQuery().toString();
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });



           startActivity(i);

       }
   });




    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



}