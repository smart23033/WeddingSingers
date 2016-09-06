package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Singer;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class SingerMyProfileRequest extends AbstractRequest<NetworkResult<Singer>> {

    Request request;

    public SingerMyProfileRequest(Context context) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("singers")
                .addPathSegment("me")
                .build();
        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Singer>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
