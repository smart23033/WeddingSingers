package com.weddingsingers.wsapp.data;

import java.io.Serializable;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class ReviewWrite implements Serializable {

    private int reservationId;
    private int singerId;
    private int point;
    private String content;
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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
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
