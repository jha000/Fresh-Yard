package com.example.milkaggregatorapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<User> list;



    public MyAdapter(Context context, ArrayList<User> list) {


        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.Product.setText(user.getProduct());
        holder.name1.setText(user.getName());
        holder.mobile1.setText(user.getMobile());
        holder.address1.setText(user.getAddress());


        holder.amount.setText(user.getAmount());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Product,amount,name1,mobile1,address1;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Product = itemView.findViewById(R.id.tvfirstName);

            amount = itemView.findViewById(R.id.tvage);

            name1 = itemView.findViewById(R.id.nameid);
//
//            mobile1 = itemView.findViewById(R.id.mobileid);
//
//            address1 = itemView.findViewById(R.id.addressid);

        }
    }

}
