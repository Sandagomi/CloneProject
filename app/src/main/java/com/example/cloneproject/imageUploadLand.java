package com.example.cloneproject;

public class imageUploadLand {


    private String mName;
    private String mimageUrl;


    public imageUploadLand(){

            // this needed
    }

    public imageUploadLand(String name, String imageUrl) {

        if(name.trim().equals("")){
            name = "No Name";
        }

        this.mName = name;
        this.mimageUrl = imageUrl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getMimageUrl() {
        return mimageUrl;
    }

    public void setMimageUrl(String mimageUrl) {
        this.mimageUrl = mimageUrl;
    }
}
