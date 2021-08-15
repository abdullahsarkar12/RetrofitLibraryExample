package com.example.retrofitexample;

import com.google.gson.annotations.SerializedName;

public class Post {

    private int userid;

    private Integer id;

    private String title;

    @SerializedName("body")
    private  String text;

    public Post(int userid, String title, String text) {
        this.userid = userid;
        this.title = title;
        this.text = text;
    }

    public int getUserid() {
        return userid;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
