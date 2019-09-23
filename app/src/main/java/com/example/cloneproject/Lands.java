package com.example.cloneproject;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Lands {

    private String landId;
    private String landPlace;
    private String landPrice;

    public Lands() {

    }

    public Lands(String landId, String landPlace, String landPrice) {
        this.landId = landId;
        this.landPlace = landPlace;
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

    public String getLandPrice() {
        return landPrice;
    }

    public void setLandPrice(String landPrice) {
        this.landPrice = landPrice;
    }
}