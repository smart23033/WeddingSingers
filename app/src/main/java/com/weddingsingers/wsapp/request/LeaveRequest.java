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
 * Created by Tacademy on 2016-09-09.
 */
public class LeaveRequest extends AbstractRequest<NetworkResult<String>> {

    Request request;

    public LeaveRequest(Context context) {
        HttpUrl.Builder builder = getBaseUrlBuilder();
        builder.addPathSegment("users")
                .addPathSegment("me");


        RequestBody body = new FormBody.Builder()
                .build();

        request = new Request.Builder()
                .url(builder.build())
                .delete(body)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<String>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}

