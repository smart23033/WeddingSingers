package com.weddingsingers.wsapp.data;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VideoList implements Serializable {

    @SerializedName("id")
    private int videoId;

    @SerializedName("url")
    private String thumbnail;

    private String title;

    @SerializedName("singer_name")
    private String singerName;

    @SerializedName("write_dtime")
    private String date;

    private int hit;

    @SerializedName("favorite_cnt")
    private int favorite;

    private boolean isSelected;

    @SerializedName("singer_id")
    int singerId;

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
