package com.example.cuoiki_android_lythuyet.tag;

public abstract class Tag {
    private static String tag = "info";

    public static String getTag() {
        return tag;
    }

    public static void setTag(String tag) {
        Tag.tag = tag;
    }
}
