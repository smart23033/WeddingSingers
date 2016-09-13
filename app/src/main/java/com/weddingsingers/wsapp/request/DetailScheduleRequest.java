package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.data.NetworkResult;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class DetailScheduleRequest extends AbstractRequest<NetworkResult<List<Estimate>>> {
    Request request;

    public DetailScheduleRequest(Context context, int year, int month) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("reservations")
                .addPathSegment("me")
                .addQueryParameter("year", String.valueOf(year))
                .addQueryParameter("month", String.valueOf(month))
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();

    }


    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<Estimate>>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }

}
