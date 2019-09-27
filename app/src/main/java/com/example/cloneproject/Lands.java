package com.example.cloneproject;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Lands {

    private String landId;
    private String landPlace;
    private String landArea;
    private double landPrice;

    public Lands() {

    }

    public Lands(String landId, String landPlace, String landArea,double landPrice) {
        this.landId = landId;
        this.landPlace = landPlace;
        this.landArea = landArea;
        this.landPrice = landPrice;
    }

    public double getLandPrice() {
        return landPrice;
    }

    public void setLandPrice(double landPrice) {
        this.landPrice = landPrice;
    }

    public String getLandId() {

        return landId;
    }

    public void setLandId(String landId) {

        this.landId = landId;
    }

    public String getLandPlace() {
        return landPlace;
    }

    public void setLandPlace(String landPlace) {
        this.landPlace = landPlace;
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea;
    }
}