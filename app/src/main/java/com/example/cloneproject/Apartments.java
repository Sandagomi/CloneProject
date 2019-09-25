package com.example.cloneproject;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Apartments {

    private String apartmentId;
    private String apartmentName;
    private String apartmentPlace;
    private String aImageName;
    private String mImageUrl;

    public Apartments(String apartmentId, String aName, String tSpinner){


    }


    public Apartments(String apartmentId, String apartmentName, String apartmentPlace, String aImageName, String mImageUrl) {

        if(apartmentName.trim().equals("")){
            apartmentName = "No Name";
        }



        this.apartmentId = apartmentId;
        this.apartmentName = apartmentName;
        this.apartmentPlace = apartmentPlace;
        this.aImageName = aImageName;
        this.mImageUrl = mImageUrl;
    }

    public String getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getApartmentPlace() {
        return apartmentPlace;
    }

    public void setApartmentPlace(String apartmentPlace) {
        this.apartmentPlace = apartmentPlace;
    }

    public String getaImageName() {
        return aImageName;
    }

    public void setaImageName(String aImageName) {
        this.aImageName = aImageName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
