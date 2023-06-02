package com.example.chathub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity {
    private static final String LOG_TAG = ChatListActivity.class.getSimpleName();

    private SharedPreferences mPreferences;

    DatabaseReference databaseReference;
    ArrayList<User> contacts;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> userList;

    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = database.getReference("Users");


        String sharedPrefFile = "com.example.chathub";
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);



        // Click Listener for the "New Chat" button
        Button newChatButton = (Button) findViewById(R.id.buttonNewChat);
        newChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // New Chat
                Intent logoutIntent = new Intent(ChatListActivity.this, NewChatActivity.class);
                startActivity(logoutIntent);
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

        List<String> usernames = new ArrayList<String>();
        databaseReference = database.getReference("Users");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(LOG_TAG,"sono qua");

                for(DataSnapshot ds : snapshot.getChildren()) {
                    String usernameFromDB = ds.child("username").getValue(String.class);
                    usernames.add(usernameFromDB);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*
        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);


        // Initialize contacts
        contacts = User.createContactsList(20);

        // Create adapter passing in the sample user data
        ContactsAdapter adapter = new ContactsAdapter(contacts);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
        */

        initData();
        initRecyclerView();




    }

    private void initData() {
//  userList = new ArrayList<>();
//      userList.add(new ModelClass("Giulia", "Online"));
//       userList.add(new ModelClass("Riccardo", "Offline"));
//       userList.add(new ModelClass("Belin", "Online"));
//        Log.d("Usernames",userList.toString());
//
//        userList.add(new ModelClass("Leotta", "Offline"));
//       userList.add(new ModelClass("Fra", "Online"));
//       userList.add(new ModelClass("Ric", "Offline"));

        userList = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = database.getReference("Users");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds : snapshot.getChildren()) {
                    String usernameFromDB = ds.child("username").getValue(String.class);
                    Log.d("Usernames",usernameFromDB.toString());
                    userList.add(new ModelClass(usernameFromDB, "Offline"));

                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


   }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(userList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}