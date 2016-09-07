package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.data.NetworkResult;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class PaymentRequest extends AbstractRequest<NetworkResult<String>>{
    Request request;

    public PaymentRequest(Context context, int id, int type) {
        HttpUrl.Builder builder = getBaseUrlBuilder();
        builder.addPathSegment("reservations")
                .addPathSegment(String.valueOf(id));

        RequestBody body = new FormBody.Builder()
                .add("type", String.valueOf(type))
                .build();

        request = new Request.Builder()
                .url(builder.build())
                .put(body)
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
