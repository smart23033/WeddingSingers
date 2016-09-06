package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.NetworkResult;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class ReservationRequest  extends AbstractRequest<NetworkResult<String>>{
    Request request;

    public ReservationRequest(Context context,int singerId, String location, String special, String reservationDate, String reservationTime, String writeDate,
                              int type, String song) {
        HttpUrl.Builder builder = getBaseUrlBuilder();
        builder.addPathSegment("reservations");

        RequestBody body = new FormBody.Builder()
                .add("place",location)
                .add("demand",special)
                .add("reservation_date",reservationDate)
                .add("reservation_time",reservationTime)
                .add("write_dtime",writeDate)
                .add("singer_id", String.valueOf(singerId))
                .add("type", String.valueOf(type))
                .add("song",song)
                .build();

        request = new Request.Builder()
                .url(builder.build())
                .post(body)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<String>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
