package com.example.cloneproject;

import com.google.firebase.database.Exclude;

public class UploadComputer {

    private String mComputerName;
    private String mComputerDes;
    private String mComputerCNo;
    private String mImageUrl;
    private String mKey;
    public UploadComputer() {

    }

    public UploadComputer (String mComputerName,String mImageUrl){

        if(mComputerName.trim().equals("")){
            mComputerName = "No Name";
        }
        this.mImageUrl = mImageUrl;
        this.mComputerName = mComputerName;

    }

    public String getmComputerName() {
        return mComputerName;
    }

    public void setmComputerName(String mComputerName) {
        this.mComputerName = mComputerName;

    }

    public String getmComputerDes() {
        return mComputerDes;
    }

    public void setmComputerDes(String mComputerDes) {
        this.mComputerDes = mComputerDes;
    }

    public String getmComputerCNo() {
        return mComputerCNo;
    }

    public void setmComputerCNo(String mComputerCNo) {
        this.mComputerCNo = mComputerCNo;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
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
