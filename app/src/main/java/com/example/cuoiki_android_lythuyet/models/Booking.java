package com.example.cuoiki_android_lythuyet.models;

import com.google.type.DateTime;

import java.io.Serializable;

public class Booking implements Serializable {

    String name;
    int imgBooking;
    Double price;
    int responceBooking;
    String calendar;
    String status;
    public Booking(String name, int imgBooking, Double price, int responceBooking, String calendar, String status) {
        this.name = name;
        this.imgBooking = imgBooking;
        this.price = price;
        this.responceBooking = responceBooking;
        this.calendar = calendar;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgBooking() {
        return imgBooking;
    }

    public void setImgBooking(int imgBooking) {
        this.imgBooking = imgBooking;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getResponceBooking() {
        return responceBooking;
    }

    public void setResponceBooking(int responceBooking) {
        this.responceBooking = responceBooking;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }
}
