package com.weddingsingers.wsapp.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class Review implements Serializable {

    private int reservationId;

    private int singerId;

    @SerializedName("customer_photoURL")
    private String thumbnail;

    @SerializedName("customer_name")
    private String customerName;

    @SerializedName("singer_name")
    private String SingerName;

    private String point;

    private String content;

    @SerializedName("write_dtime")

    private String wrtieDTime;

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSingerName() {
        return SingerName;
    }

    public void setSingerName(String singerName) {
        SingerName = singerName;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWrtieDTime() {
        return wrtieDTime;
    }

    public void setWrtieDTime(String wrtieDTime) {
        this.wrtieDTime = wrtieDTime;
    }
}