package com.bryanpoh.c347_p05_ps_songlist;

import java.io.Serializable;

public class Song implements Serializable{
    private int _id;
    private String title;
    private String singers;
    private String year;
    private int stars;

    public Song(String title, String singers, String year, int stars) {
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }

    public String getYear() {
        return year;
    }

    public int getStars() {
        return stars;
    }
}
