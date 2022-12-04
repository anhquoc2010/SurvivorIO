package com.example.cuoiki_android_lythuyet.models;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class Keeper implements Serializable {

    private String name, distance, preview;
    private int imageKeeper, star;
    private double price;

    public Keeper(int imageKeeper, String name, String distance, int star, String preview, double price){
        this.imageKeeper = imageKeeper;
        this.name = name;
        this.distance = distance;
        this.star = star;
        this.preview = preview;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public int getImageKeeper() {
        return imageKeeper;
    }

    public void setImageKeeper(int imageKeeper) {
        this.imageKeeper = imageKeeper;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
