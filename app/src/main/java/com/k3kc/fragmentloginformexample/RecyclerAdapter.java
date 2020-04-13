package com.k3kc.fragmentloginformexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder> {

    private List<LoginModel> logins;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView Name, Email;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name);
            Email = itemView.findViewById(R.id.email);
        }
    }

    public RecyclerAdapter(List<LoginModel> logins) {
        this.logins = logins;
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.Name.setText(logins.get(position).Name);
        holder.Email.setText(logins.get(position).Email);
    }

    @Override
    public int getItemCount() {
        return logins.size();
    }
}
