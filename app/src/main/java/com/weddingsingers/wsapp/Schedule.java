package com.weddingsingers.wsapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Tacademy on 2016-09-07.
 */
public class Schedule implements Serializable {

//    어레이리스트로도 해보고 배열로도 해봐라
    @SerializedName("reservations")
ArrayList<String> reservationDate;

    @SerializedName("holidaies")
    ArrayList<String> dayOff;

    public ArrayList<String> getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(ArrayList<String> reservationDate) {
        this.reservationDate = reservationDate;
    }

    public ArrayList<String> getDayOff() {
        return dayOff;
    }

    public void setDayOff(ArrayList<String> dayOff) {
        this.dayOff = dayOff;
    }
}
