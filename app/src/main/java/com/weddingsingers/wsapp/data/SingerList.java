package com.weddingsingers.wsapp.data;

import java.io.Serializable;

/**
 * Created by Tacademy on 2016-08-29.
 */

//ReservationListFragment를 위한 Data
public class SingerList implements Serializable{
    private String singerImage;
    private String location;
    private String date;
    private String songs;
    private String singerName;

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerImage() {
        return singerImage;
    }

    public void setSingerImage(String singerImage) {
        this.singerImage = singerImage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSongs() {
        return songs;
    }

    public void setSongs(String songs) {
        this.songs = songs;
    }
}
