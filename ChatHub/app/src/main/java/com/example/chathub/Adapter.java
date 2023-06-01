package com.example.chathub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private List<ModelClass> userList;

    public Adapter (List<ModelClass>userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String name = userList.get(position).getTextViewUsername();
        String status = userList.get(position).getTextViewStatus();

        holder.setData(name, status);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewUsername;
        private TextView textViewStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);
        }

        public void setData(String name, String status) {
            textViewUsername.setText(name);
            textViewStatus.setText(status);
        }
    }
}
