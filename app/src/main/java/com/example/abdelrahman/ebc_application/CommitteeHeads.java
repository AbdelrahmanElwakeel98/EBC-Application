package com.example.abdelrahman.ebc_application;

import java.util.ArrayList;

public class CommitteeHeads {

    ArrayList<HeadDataHolder> boardMembers;
    private String committee;
    private int num;

    public CommitteeHeads(ArrayList<HeadDataHolder> boardMembers, String committee, int num){
        this.boardMembers = boardMembers;
        this.committee = committee;
        this.num = num;
    }

    public ArrayList<HeadDataHolder> getBoardList() {
        return boardMembers;
    }

    public String getCommittee() {
        return committee;
    }

    public int getNum() {
        return num;
    }
}
