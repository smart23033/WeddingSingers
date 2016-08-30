package com.weddingsingers.wsapp.data;

import java.io.Serializable;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class CalendarList implements Serializable {
    private int month;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
