package com.example.cuoiki_android_lythuyet.models;

public class Booking {

    String nameBooking;
    int imgBooking;
    Double distanceBooking;
    Double priceBooking;
    Double starBooking;
    Double previewBooking;

    public Booking(String nameBooking, int imgBooking, Double distanceBooking, Double priceBooking, Double starBooking, Double previewBooking) {
        this.nameBooking = nameBooking;
        this.imgBooking = imgBooking;
        this.distanceBooking = distanceBooking;
        this.priceBooking = priceBooking;
        this.starBooking = starBooking;
        this.previewBooking = previewBooking;
    }

    public String getNameBooking() {
        return nameBooking;
    }

    public void setNameBooking(String nameBooking) {
        this.nameBooking = nameBooking;
    }

    public int getImgBooking() {
        return imgBooking;
    }

    public void setImgBooking(int imgBooking) {
        this.imgBooking = imgBooking;
    }

    public Double getDistanceBooking() {
        return distanceBooking;
    }

    public void setDistanceBooking(Double distanceBooking) {
        this.distanceBooking = distanceBooking;
    }

    public Double getPriceBooking() {
        return priceBooking;
    }

    public void setPriceBooking(Double priceBooking) {
        this.priceBooking = priceBooking;
    }

    public Double getStarBooking() {
        return starBooking;
    }

    public void setStarBooking(Double starBooking) {
        this.starBooking = starBooking;
    }

    public Double getPreviewBooking() {
        return previewBooking;
    }

    public void setPreviewBooking(Double previewBooking) {
        this.previewBooking = previewBooking;
    }
}
