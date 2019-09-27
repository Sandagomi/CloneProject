package com.example.cloneproject;

public class imageUploadApartment {


    private String aName;
    private String aImageUrl;


    public imageUploadApartment() {

    }


    public imageUploadApartment(String aName, String aImageUrl) {
        this.aName = aName;
        this.aImageUrl = aImageUrl;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getaImageUrl() {
        return aImageUrl;
    }

    public void setaImageUrl(String aImageUrl) {
        this.aImageUrl = aImageUrl;
    }
}
