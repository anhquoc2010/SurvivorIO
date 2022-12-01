package com.example.cuoiki_android_lythuyet.models;

import java.io.Serializable;

public class Pet implements Serializable {
    private String name;
    private String species;
    private boolean gender;
    private double age;
    private double weight;
    private double height;
    private String color;
    private String description;
    private int imageResourceID;

    public Pet() {
    }

    public Pet(String name, String species, boolean gender, double age, double weight, double height, String color, String description, int imageResourceID) {
        this.name = name;
        this.species = species;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.color = color;
        this.description = description;
        this.imageResourceID = imageResourceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResourceID() {
        return imageResourceID;
    }

    public void setImageResourceID(int imageResourceID) {
        this.imageResourceID = imageResourceID;
    }
}

