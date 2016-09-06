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
 * Created by Tacademy on 2016-09-06.
 */
public class ReservedSingerRequest extends AbstractRequest<NetworkResult<List<Estimate>>> {
    Request request;

    public ReservedSingerRequest(Context context, int tab) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("reservations")
                .addPathSegment("me")
                .addQueryParameter("tab", String.valueOf(tab))
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