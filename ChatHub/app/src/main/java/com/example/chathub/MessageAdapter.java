package com.example.chathub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/* This class is used to display a list of messages in a RecyclerView in the ChatActivity. */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    Context context;
    List<MessageModel> list;
    Activity activity;
    String userName;
    Boolean state;
    int send =0, received=1;

    public MessageAdapter(Context context, List<MessageModel> list, Activity activity, String userName) {
        this.context = context;
        this.list = list;
        this.activity = activity;
        this.userName = userName;
        state = false;
    }

    /* This method is responsible for creating a new instance if the ViewHolder class and inflating
    *  the appropriate layout for the item based on its viewType. */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        // Display a send message
        if(viewType == send){
            view= LayoutInflater.from(context).inflate(R.layout.send_layout, parent, false);
            return new ViewHolder(view);
        }
        // Display a received message
        else {
            view= LayoutInflater.from(context).inflate(R.layout.received_layout, parent, false);
            return new ViewHolder(view);
        }
    }

    /* This method is responsible for binding data to the views within a ViewHolder for a given position
     * in the data set.
     * In this case, it sets the text of a TextView within the ViewHolder to the text value from the
     * corresponding item in the list data set.*/
    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, final int position) {
        holder.textView.setText(list.get(position).getText().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    /* This class represents a single item view within the RecyclerView and is responsible for
     * holding references to the views within that item.*/
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            if(state == true){
                textView = itemView.findViewById(R.id.sendView);
            }
            else {
                textView = itemView.findViewById(R.id.receivedView);
            }
        }
    }

    /* This method is used to determine the view type of an item at a given position in the data set.
    It helps in differentiating between different types of item views within the RecyclerView. */
    @Override
    public int getItemViewType(int position) {
        if(list.get(position).getFrom().equals(userName)){
            state = true;
            return send;
        }
        else {
            state = false;
            return received;
        }
    }
}