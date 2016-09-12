package com.weddingsingers.wsapp.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class Review implements Serializable {

    private int reservationId;

    private int singerId;

    @SerializedName("singer_photoURL")
    private String thumbnail;

    @SerializedName("customer_photoURL")
    private String customerThumbnail;

    @SerializedName("singer_name")
    private String SingerName;

    @SerializedName("customer_name")
    private String customerName;

    // 싱어 리뷰 조회 평점
    private String point;

    // 리뷰 작성 시 평점
    private int intPoint;

    private String content;

    @SerializedName("write_dtime")
    private String writeDTime;



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

    public String getCustomerThumbnail() {
        return customerThumbnail;
    }

    public void setCustomerThumbnail(String customerThumbnail) {
        this.customerThumbnail = customerThumbnail;
    }

    public String getSingerName() {
        return SingerName;
    }

    public void setSingerName(String singerName) {
        SingerName = singerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public int getIntPoint() {
        return intPoint;
    }

    public void setIntPoint(int intPoint) {
        this.intPoint = intPoint;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriteDTime() {
        return writeDTime;
    }

    public void setWriteDTime(String writeDTime) {
        this.writeDTime = writeDTime;
    }
}