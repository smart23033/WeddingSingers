package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.Schedule;
import com.weddingsingers.wsapp.data.NetworkResult;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-07.
 */
public class ReservationDateRequest extends AbstractRequest<NetworkResult<Schedule>> {
    Request request;

    public ReservationDateRequest(Context context,int singerId) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("reservations")
                .addQueryParameter("sid", String.valueOf(singerId))
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();

    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Schedule>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
