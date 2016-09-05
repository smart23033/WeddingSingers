package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.User;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-05.
 */
public class MyPageRequest extends AbstractRequest<NetworkResult<User>> {

    Request request;

    public MyPageRequest(Context context, long id) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("users")
                .addPathSegment("me")
                .build();
        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<User>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
