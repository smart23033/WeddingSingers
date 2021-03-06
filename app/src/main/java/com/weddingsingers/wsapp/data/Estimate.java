package com.weddingsingers.wsapp.data;

import android.support.v4.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;
import com.weddingsingers.wsapp.R;

import java.io.Serializable;

/**
 * Created by Tacademy on 2016-08-31.
 */
//일정관리랑 공급자 예약관리 리싸이클러 뷰 같이쓰는것 떄매 사용

public class Estimate implements Serializable {
    private int id;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("singer_id")
    private int singerId;

    @SerializedName("singer_photoURL")
    private String singerImage;

    @SerializedName("singer_name")
    private String singerName;

    @SerializedName("customer_id")
    private int customerId;

    @SerializedName("customer_name")
    private String customerName;

    @SerializedName("customer_photoURL")
    private String customerImage;

    @SerializedName("place")
    private String location;

    @SerializedName("reservation_dtime")
    private String date;

    @SerializedName("song")
    private String songs;

    @SerializedName("type")
    private int type;

    private int status;

    @SerializedName("demand")
    private String special;

    private String userName;

    private String userImage;

    public String getUserName() {
        if(singerName != null){
            return singerName;
        }else return customerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    /*

        private int id;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("customer_id")
    private int customerId;

    @SerializedName("singer_id")
    private int singerId;

    @SerializedName("singer_photoURL")
    private String singerImage;

    @SerializedName("singer_name")
    private String singerName;

    @SerializedName("customer_name")
    private String customerName;

    @SerializedName("customer_photoURL")
    private String customerImage;

    @SerializedName("place")
    private String location;

    @SerializedName("reservation_dtime")
    private String date;

    @SerializedName("song")
    private String songs;

    @SerializedName("type")
    private int type;

    private int status;

    @SerializedName("demand")
    private String special;

    private String userName;

    private String userImage;

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getUserName() {
        if(singerName != null){
            return singerName;
        }else return customerName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        if(singerImage != null){
            return singerImage;
        }else return customerImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

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
     */
}
