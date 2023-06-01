package com.example.chathub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
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

        SearchView searchView = findViewById(R.id.searchView);
        ListView listView = findViewById(R.id.listview);


        List<String> usernames = new ArrayList<String>();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = database.getReference("Users");


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
            Log.d("ciaooo","sono qua");
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



    }




}