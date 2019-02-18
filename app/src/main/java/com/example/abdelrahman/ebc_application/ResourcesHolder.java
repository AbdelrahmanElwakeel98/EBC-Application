package com.example.abdelrahman.ebc_application;

public class ResourcesHolder {
    private String title;
    private String link;

    public ResourcesHolder (String title, String link){
        this.title = title;
        this.link = link;
    }

    public String getTitle(){
        return this.title;
    }

    public String getLink(){
        return this.link;
    }
}
