package com.example.abdelrahman.ebc_application;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ResourcesAdapter extends RecyclerView.Adapter<ResourcesAdapter.MyViewHolder> {

    private Context context;
    ArrayList<ResourcesHolder> res;

    public ResourcesAdapter (ArrayList<ResourcesHolder> res, Context context){
        this.res=res;
        this.context=context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_row,viewGroup,false);
        MyViewHolder holder=new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.textView.setText(res.get(i).getTitle());
        myViewHolder.textView1.setText(res.get(i).getLink());

    }

    @Override
    public int getItemCount() {
        return res.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView1;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.txt_res);
            textView1 = itemView.findViewById(R.id.txt1_res);
        }
    }
}
