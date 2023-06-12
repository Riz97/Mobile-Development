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
    DatabaseReference databaseReference;
    DatabaseReference dbr;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> userList;
    Adapter adapter;
    String userlogged = "";
    String newChatUser = "";
    int i = 0;
    List myList = new ArrayList<String>();
    List removedList = new ArrayList<String>();
    ArrayList list_aux = new  ArrayList<String>() ;

    int rem = 0; // Variable used for understand from which list we are taking the chats

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        userlogged = intent.getStringExtra("username");
        newChatUser = intent.getStringExtra("dest");


        /* ---------------- MAIN ------------------ */
        initData();
        initRecyclerView();


        /* ----------------------- New Chat Button behavior --------------------- */
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


        /* ------------------ LogOut Button Behaviour -------------------- */
        /* When clicked it sets the status of the user to offline and it redirects him to the Login */

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

                Intent logoutIntent = new Intent(ChatListActivity.this, MainActivity.class);
                startActivity(logoutIntent);
            }
        });



    }

    /* ------------------- Three Dots behavior in the Toolbar ------------------*/
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

    /* -------------------------- INIT DATA ------------------------------- */
    /* This is the most important function in the application. Its job is to initialize the data taken from the database.
     * When a user log in tha application , it takes from the DB all the users with which a chat exists and create the chat inside
     * the recyclerView using the Model class.
     */

    private void initData() {

        userList = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://chathub-caprile-benvenuto-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = database.getReference("Messages");

        dbr = databaseReference.child(userlogged);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Intent intent = getIntent();
                String userlogged = intent.getStringExtra("username");

                //----------------------- NO deletion ---------------------------------
                if(rem == 0)
                {
                    for(DataSnapshot ds : snapshot.getChildren()) {
                        String usernameFromDB = ds.child("username").getValue(String.class);
                        boolean usernameStatusFromDB = ds.child("online").getValue(Boolean.class);
                        Log.d("status username", Boolean.toString(usernameStatusFromDB));
                        if (!usernameFromDB.equals(userlogged) && myList.contains(usernameFromDB) ) {

                            //Avoid repetition of chats
                            for (i = 0; i < myList.size(); i++) {
                                Object element = myList.get(i);

                                boolean isNewElement = true;
                                for (ModelClass model : userList) {
                                    if (model.getTextViewUsername().equals(element)) {
                                        isNewElement = false;
                                        break;
                                    }
                                 }

                                if (isNewElement) {
                                    if (usernameStatusFromDB == true && usernameFromDB.equals(element)) {
                                        userList.add(new ModelClass((String) element, "online"));
                                    } else if(usernameStatusFromDB == false && usernameFromDB.equals(element)) {
                                        userList.add(new ModelClass((String) element, "offline"));
                                    }
                                }
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                }

                // ---------------------- YES deletion ---------------------------------
                else if (rem == 1) {
                    for (DataSnapshot ds : snapshot.getChildren()) {

                        String usernameFromDB = ds.child("username").getValue(String.class);
                        boolean usernameStatusFromDB = ds.child("online").getValue(Boolean.class);

                        if (!usernameFromDB.equals(userlogged) && myList.contains(usernameFromDB)) {

                            for (i = 0; i < removedList.size(); i++) {
                                Object element = removedList.get(i);

                                boolean isNewElement = true;
                                for (ModelClass model : userList) {
                                    if (model.getTextViewUsername().equals(element)) {
                                        isNewElement = false;
                                        break;
                                    }
                                }

                                if (isNewElement) {
                                    Log.d("status username", Boolean.toString(usernameStatusFromDB));

                                    if (usernameStatusFromDB == true && usernameFromDB.equals(element)) {
                                        userList.add(new ModelClass((String) element, "online"));
                                    } else if(usernameStatusFromDB == false && usernameFromDB.equals(element)) {
                                        userList.add(new ModelClass((String) element, "offline"));
                                    }
                                }
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        /*Here we created the list of user with which there is an existed chat */

        dbr.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds : snapshot.getChildren()) {

                    String parent = ds.getKey();

                    if(!parent.equals("dest")){
                        list_aux.add(parent);

                        //Get rid of useless keys
                        list_aux.remove("online");
                        list_aux.remove("password");
                        list_aux.remove("username");
                        list_aux.remove("dest");

                        myList = removeDuplicates(list_aux);

                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    /* ----------------- Graphical Inizialization ---------------------------------- */
    /* Function that initializes the RecyclerView that contains all the chats of the user logged */

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(userList, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }



    /* ------------------- OnItemClick -------------------------------- */
    /* When you click on a chat you will be redirected to ChatActivity */

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

    /* ----------------------------------- Long Item Click --------------------------------- */
    /*   If you long click on the chat you would like to delete , the chat will disappear
    *  if you delete a chat rem is set to 1, in this way initData will use removedList as a
    * ist that contains all the previous chats*/


    @Override
    public void onItemLongClick(int position) {

        String other = ((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.textViewUsername)).getText().toString();

        dbr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds : snapshot.getChildren()) {
                    String parent = ds.getKey();

                    if(parent.equals(other)) {
                        snapshot.child(other).getRef().setValue(null);
                        myList.remove(other);

                        removedList = myList;
                        rem = 1;
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        userList.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyDataSetChanged();
    }


    /* --------------------------- Auxiliary Function ------------------ */
    public static <String> ArrayList<String> removeDuplicates(ArrayList<String> list)
    {

        // Create a new ArrayList
        ArrayList<String> newList = new ArrayList<String>();

        // Traverse through the first list
        for (String element : list) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        // return the new list
        return newList;
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
                initData();
                initRecyclerView();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



    }

}