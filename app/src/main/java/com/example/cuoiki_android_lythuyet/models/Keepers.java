package com.example.cuoiki_android_lythuyet.models;

import java.io.Serializable;

public class Keepers implements Serializable {

    private String name, distance, star, price, review;

    public Keepers() {
    }

    public Keepers(String name, String distance, String star, String price, String review) {
        this.name = name;
        this.distance = distance;
        this.star = star;
        this.price = price;
        this.review = review;
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

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
