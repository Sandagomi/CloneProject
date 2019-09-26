package com.example.cloneproject;

import com.google.firebase.database.Exclude;

public class Upload {
    private String mImageUri;
    private String mItemID;
    private String mTitle;
    private String mDescription;
    private String mPrice;
    private int mContactNum;
    private String mEmail;

    private String mKey;


    public Upload() {
    }
    public  Upload(String itemID,String imageUri){

        if(itemID.trim().equals("")){
            itemID = "No Name";
        }
        mImageUri = imageUri;
        mItemID = itemID;

    }

    public String getmImageUri() {
        return mImageUri;
    }

    public void setmImageUri(String mImageUri) {
        this.mImageUri = mImageUri;
    }


    public String getmItemID() {
        return mItemID;
    }

    public void setmItemID(String mItemID) {
        this.mItemID = mItemID;
    }


    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public int getmContactNum() {
        return mContactNum;
    }

    public void setmContactNum(int mContactNum) {
        this.mContactNum = mContactNum;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    @Exclude
    public String getmKey() {
        return mKey;
    }
    @Exclude
    public void setmKey(String mKey) {
        this.mKey = mKey;
    }
}
