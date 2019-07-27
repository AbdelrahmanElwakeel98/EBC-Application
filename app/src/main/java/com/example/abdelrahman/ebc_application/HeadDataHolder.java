package com.example.abdelrahman.ebc_application;

public class HeadDataHolder {
    private String imgUrl;
    private String name;
    private String title;

    public HeadDataHolder(String imgUrl, String name, String title){
        this.imgUrl = imgUrl;
        this.name = name;
        this.title = title;
    }

    public String getImgUrl(){
        return imgUrl;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

}
