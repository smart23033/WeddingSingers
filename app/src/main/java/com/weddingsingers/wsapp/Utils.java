package com.weddingsingers.wsapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tacademy on 2016-09-13.
 */

public class Utils {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public static String convertTimeToString(Date date) {
        return sdf.format(date);
    }

    public static Date convertStringToTime(String text) throws ParseException {
        return sdf.parse(text);
    }
}
