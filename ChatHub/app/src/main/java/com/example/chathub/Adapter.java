package com.example.chathub;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


/* This class is used in conjunction with the RecyclerView to provide the data and create the views
*  for individual items in the layout, it allows us to dynamically display the data (the chat list)
*  in the ChatListActivity. */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private List<ModelClass> userList;
    private final RecyclerViewInterface recyclerViewInterface;

    public Adapter (List<ModelClass>userList, RecyclerViewInterface recyclerViewInterface) {
        this.userList = userList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    /* This method is responsible for creating a new instance of the ViewHolder class. */
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    /* This method is responsible for binding data to the views within a ViewHolder for a given
    *  position in the data set. */
    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String name = userList.get(position).getTextViewUsername();
        String status = userList.get(position).getTextViewStatus();
        holder.setData(name, status);
    }

    /* This method return the number of users. */
    @Override
    public int getItemCount() {
        return userList.size();
    }

    /* This class represents a single item view within the RecyclerView and is responsible for
    *  holding references to the views within that item. */
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewUsername;
        private TextView textViewStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);

            // Set a Click Listener on each item of the RecyclerView
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

            // Set a Long Click Listener on each item of the RecyclerView
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemLongClick(pos);
                        }
                    }
                    return true;
                }
            });
        }

        // Method that sets the data in the TextViews if each item in the RecyclerView
        public void setData(String name, String status) {
            textViewUsername.setText(name);
            textViewStatus.setText(status);

            if(status == "online") {
                textViewStatus.setTextColor(Color.parseColor("#2cba00"));
            } else {
                textViewStatus.setTextColor(Color.parseColor("#ff0000"));
            }
        }
    }

}
