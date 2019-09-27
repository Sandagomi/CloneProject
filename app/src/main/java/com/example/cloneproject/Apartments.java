package com.example.cloneproject;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Apartments {

    private String apartmentId;
    private String apartmentName;
    private String apartmentPlace;
    private double apartmentPrice;


    public Apartments(String apartmentId, String apartmentName, String apartmentPlace, double apartmentPrice) {
        this.apartmentId = apartmentId;
        this.apartmentName = apartmentName;
        this.apartmentPlace = apartmentPlace;
        this.apartmentPrice = apartmentPrice;
    }

    public double getApartmentPrice() {
        return apartmentPrice;
    }

    public void setApartmentPrice(double apartmentPrice) {
        this.apartmentPrice = apartmentPrice;
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
}