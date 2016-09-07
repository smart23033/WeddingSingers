package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.data.NetworkResult;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-07.
 */
public class EstimateRequest extends AbstractRequest<NetworkResult<Estimate>> {
    Request request;

    public EstimateRequest(Context context, int id) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("reservations")
                .addPathSegment(String.valueOf(id))
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();

    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Estimate>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
