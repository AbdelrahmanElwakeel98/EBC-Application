package com.example.abdelrahman.ebc_application;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BoardAdapterHorizontal extends RecyclerView.Adapter<BoardAdapterHorizontal.MyViewHolder> {
    ArrayList<HeadDataHolder> boardMembers;
    HeadDataHolder boardMember;
    Context context;

    public BoardAdapterHorizontal(ArrayList<HeadDataHolder> boardMembers, Context context){
        this.boardMembers=boardMembers;
        this.context=context;
    }

    @NonNull
    @Override
    public BoardAdapterHorizontal.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_committeeheads,viewGroup,false);
        MyViewHolder holder= new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapterHorizontal.MyViewHolder myViewHolder, int i) {
        boardMember=boardMembers.get(i);
        myViewHolder.textView.setText(boardMember.getName());
        // Log.d("dd", boardMember.getName());
        myViewHolder.textView1.setText(boardMember.getTitle());
        Glide.with(myViewHolder.imageView.getContext())
                .load(boardMember.getImgUrl())
                .into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return boardMembers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView textView1;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_grid);
            textView = itemView.findViewById(R.id.txt_grid);
            textView1 = itemView.findViewById(R.id.txt1_grid);
        }
    }
}
