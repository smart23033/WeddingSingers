package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.VideoList;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-07.
 */
public class SingerVideoMgmRequest extends AbstractRequest<NetworkResult<List<VideoList>>>{
    Request request;

    public SingerVideoMgmRequest(Context context) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("videos")
                .addPathSegment("me")
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();

    }

    public SingerVideoMgmRequest(Context context, int SingerId) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("videos")
                .addQueryParameter("sid", String.valueOf(SingerId))
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();

    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<VideoList>>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
