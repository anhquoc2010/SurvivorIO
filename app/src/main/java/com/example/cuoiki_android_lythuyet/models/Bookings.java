package com.example.cuoiki_android_lythuyet.models;

import java.io.Serializable;

public class Bookings implements Serializable {
    String name, status, calendar, pet, price, userSend, userReceive;

    public Bookings() {
    }

    public Bookings(String name, String status, String calendar, String pet, String price, String userSend, String userReceive) {
        this.name = name;
        this.status = status;
        this.calendar = calendar;
        this.pet = pet;
        this.price = price;
        this.userSend = userSend;
        this.userReceive = userReceive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUserSend() {
        return userSend;
    }

    public void setUserSend(String userSend) {
        this.userSend = userSend;
    }

    public String getUserReceive() {
        return userReceive;
    }

    public void setUserReceive(String userReceive) {
        this.userReceive = userReceive;
    }
}
