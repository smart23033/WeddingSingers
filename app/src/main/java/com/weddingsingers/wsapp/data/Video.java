package com.weddingsingers.wsapp.data;

import java.io.Serializable;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class Video implements Serializable {
    private String url;
    private String title;
    private String singerName;
    private String date;
    private int favorite;
    private int hit;
    private String comment;
    private int standard;
    private int special;
    private int rating;
    private int review;
    private int id;

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public void setSpecial(int special) {
        this.special = special;
    }

    public int getStandard() {
        return standard;
    }

    public int getSpecial() {
        return special;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
