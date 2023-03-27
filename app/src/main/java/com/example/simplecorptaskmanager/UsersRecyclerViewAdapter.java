package com.example.simplecorptaskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UsersRecyclerViewAdapter extends RecyclerView.Adapter<UsersRecyclerViewAdapter.UsersHolder> {
    Context context;
    ArrayList<User> dataSource;

    public UsersRecyclerViewAdapter(Context context, ArrayList<User> dataSource) {
        this.context = context;
        this.dataSource = dataSource;
    }
    public static class UsersHolder extends RecyclerView.ViewHolder {
        TextView textViewUserName;
        TextView textViewJobTitle;
        public UsersHolder(@NonNull View itemView) {
            super(itemView);

            textViewUserName = itemView.findViewById(R.id.user_name_one_user);
            textViewJobTitle = itemView.findViewById(R.id.job_title_one_user);
        }
    }

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.one_user, parent, false);
        UsersHolder usersHolder = new UsersHolder(v);
        return usersHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsersHolder holder, int position) {
        User user = dataSource.get(position);
        holder.textViewUserName.setText(user.getName());
        holder.textViewJobTitle.setText(user.getJobTitle());
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }
}
