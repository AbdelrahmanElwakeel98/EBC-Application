package com.example.abdelrahman.ebc_application;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.MyViewHolder> {

    ArrayList<CommitteeHeads> boardMembers;
    CommitteeHeads boardMember;
    Context context;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public BoardAdapter(ArrayList<CommitteeHeads> boardMembers, Context context){
        this.boardMembers=boardMembers;
        this.context=context;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @NonNull
    @Override
    public BoardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_committeesheads,viewGroup,false);
        MyViewHolder holder=new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.MyViewHolder myViewHolder, int i) {
        boardMember=boardMembers.get(i);
        myViewHolder.textView.setText(boardMember.getCommittee());
        //myViewHolder.recyclerView.setLayoutManager(new GridLayoutManager(context,boardMember.getNum()));
        BoardAdapterHorizontal boardAdapterHorizontal = new BoardAdapterHorizontal(boardMember.getBoardList(), context);
        myViewHolder.recyclerView.setAdapter(boardAdapterHorizontal);
        myViewHolder.recyclerView.setRecycledViewPool(recycledViewPool);
    }

    @Override
    public int getItemCount() {
        return boardMembers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;

        private LinearLayoutManager horizontalManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt_linear);
            recyclerView = itemView.findViewById(R.id.recyclerGrid);
            recyclerView.setHasFixedSize(true);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setLayoutManager(horizontalManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }
    }
}
