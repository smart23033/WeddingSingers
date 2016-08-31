package com.weddingsingers.wsapp.data;

import java.io.Serializable;

/**
 * Created by Tacademy on 2016-08-31.
 */
//씨발 일정관리랑 공급자 예약관리 리싸이클러 뷰 같이쓰는것 떄매 사용
public class Estimate implements Serializable {

    private String singerImage;
    private String singerName;
    private String customerName;
    private String customerImage;
    private String location;
    private String date;
    private String songs;
    private String special;
    private String standard;

    public String getSingerImage() {
        return singerImage;
    }

    public void setSingerImage(String singerImage) {
        this.singerImage = singerImage;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerImage() {
        return customerImage;
    }

    public void setCustomerImage(String customerImage) {
        this.customerImage = customerImage;
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

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }
}
