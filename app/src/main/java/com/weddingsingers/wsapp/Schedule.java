package com.weddingsingers.wsapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tacademy on 2016-09-07.
 */
public class Schedule implements Serializable {
    ArrayList<String> reservationDate;
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
