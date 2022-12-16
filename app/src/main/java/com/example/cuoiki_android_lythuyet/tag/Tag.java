package com.example.cuoiki_android_lythuyet.tag;

public abstract class Tag {
    private static String tag = "info";
    private static String tagBooking = "";
    private static String tagOnline = "";

    public static String getTag() {
        return tag;
    }

    public static void setTag(String tag) {
        Tag.tag = tag;
    }

    public static String getTagBooking() {
        return tagBooking;
    }

    public static void setTagBooking(String tagBooking) {
        Tag.tagBooking = tagBooking;
    }

    public static String getTagOnline() {
        return tagOnline;
    }

    public static void setTagOnline(String tagOnline) {
        Tag.tagOnline = tagOnline;
    }
}
