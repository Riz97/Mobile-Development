package com.example.chathub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity implements RecyclerViewInterface {
    private static final String LOG_TAG = ChatListActivity.class.getSimpleName();

    private SharedPreferences mPreferences;

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> userList;
    Adapter adapter;
    String userlogged = "";
    String newChatUser = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        userlogged = intent.getStringExtra("username");
        newChatUser = intent.getStringExtra("dest");
        Log.d("USER_LOGGED", "User logged:" + userlogged);


        String foo = intent.getStringExtra("dest");
        int foo1 = intent.getIntExtra("new",0);

        if(foo1 == 1)
        {
            Log.d("test",foo);
        }


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
                Intent newChatIntent = new Intent(ChatListActivity.this, NewChatActivity.class);
                newChatIntent.putExtra("userlogged", userlogged);
                startActivity(newChatIntent);
            }
        });

        // Click Listener for the "Logout" button
        Button logoutButton = (Button) findViewById(R.id.buttonLogout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logout

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                     snapshot.getRef().child(userlogged).child("online").setValue(false);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


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

        initData();
        initRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chatlist,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

        case R.id.refresh:
            initData();
            initRecyclerView();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {

        userList = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = database.getReference("Messages");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Intent intent = getIntent();
                String userlogged = intent.getStringExtra("username");


                for(DataSnapshot ds : snapshot.getChildren()) {
                    String usernameFromDB = ds.child("username").getValue(String.class);
                    boolean usernameStatusFromDB = ds.child("online").getValue(Boolean.class);

                    if(!usernameFromDB.equals(userlogged))
                    {
                        if(usernameStatusFromDB == true) {
                            userList.add(new ModelClass(usernameFromDB,"online" ));
                        } else {
                            userList.add(new ModelClass(usernameFromDB,"offline" ));
                        }
                        Log.d("Usernames",usernameFromDB.toString());
                    }



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
        adapter = new Adapter(userList, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

//    @Override
//    protected void onStop(){
//        super.onStop();
//        Intent intent = getIntent();
//        String userlogged = intent.getStringExtra("username");
//        FirebaseDatabase database = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/");
//        databaseReference = database.getReference("Users");
//
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                snapshot.getRef().child(userlogged).child("online").setValue(false);
//                Log.d("onstop",userlogged.toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//
//    }


//    @Override
//    protected void onPause(){
//        super.onPause();
//        Intent intent = getIntent();
//        String userlogged = intent.getStringExtra("username");
//        FirebaseDatabase database = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/");
//        databaseReference = database.getReference("Users");
//
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                snapshot.getRef().child(userlogged).child("online").setValue(false);
//                Log.d("onstop",userlogged.toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }


//    @Override
//    protected void onResume(){
//        super.onResume();
//        Intent intent = getIntent();
//        String userlogged = intent.getStringExtra("username");
//        FirebaseDatabase database = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/");
//        databaseReference = database.getReference("Users");
//
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                snapshot.getRef().child(userlogged).child("online").setValue(true);
//                Log.d("onstop",userlogged.toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }



    @Override
    public void onItemClick(int position) {
        Intent intent = getIntent();
        String userlogged = intent.getStringExtra("username");

        Intent chatIntent = new Intent(ChatListActivity.this, ChatActivity.class);
        String other = ((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.textViewUsername)).getText().toString();

        chatIntent.putExtra("dest", other);
        chatIntent.putExtra("username", userlogged);

        startActivity(chatIntent);
    }




}