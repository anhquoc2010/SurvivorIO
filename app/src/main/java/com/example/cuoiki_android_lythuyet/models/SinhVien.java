package com.example.cuoiki_android_lythuyet.models;

public class SinhVien implements java.io.Serializable {
    private String hoTen;
    private String email;
    private String password;

    public SinhVien(String hoTen, String email, String password) {
        this.hoTen = hoTen;
        this.email = email;
        this.password = password;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
