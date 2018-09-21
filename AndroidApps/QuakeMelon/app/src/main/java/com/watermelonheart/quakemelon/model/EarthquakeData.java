package com.watermelonheart.quakemelon.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EarthquakeData {

    @SerializedName("features")
    @Expose
    private ArrayList<Feature> features = null;


    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<Feature> features) {
        this.features = features;
    }

}
