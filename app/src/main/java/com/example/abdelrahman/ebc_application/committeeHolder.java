package com.example.abdelrahman.ebc_application;

public class committeeHolder {

    private String committee;
    private int numOfCommittee;

    public committeeHolder(String committee, int numOfCommittee){
        this.committee = committee;
        this.numOfCommittee = numOfCommittee;
    }

    public String getCommittee() {
        return committee;
    }

    public int getNumOfCommittee() {
        return numOfCommittee;
    }
}
