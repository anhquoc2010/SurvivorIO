package com.example.cuoiki_android_lythuyet.models;

public class Message {

    private String avatar;
    private String name;
    private String email;
    private String lastMessage;
    private int unreadMessage;

    public Message() {
    }

    public Message(String avatar, String name, String email, String lastMessage, int unreadMessage) {
        this.avatar = avatar;
        this.name = name;
        this.email = email;
        this.lastMessage = lastMessage;
        this.unreadMessage = unreadMessage;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getUnreadMessage() {
        return unreadMessage;
    }

    public void setUnreadMessage(int unreadMessage) {
        this.unreadMessage = unreadMessage;
    }
}
