package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Video;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class VideoRequest extends AbstractRequest<NetworkResult<Video>>{
    Request request;

    public VideoRequest (Context context, int videoId) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("videos")
                .addPathSegment(String.valueOf(videoId))
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();

    }

    @Override
    protected Type getType() {
            return new  TypeToken<NetworkResult<Video>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
