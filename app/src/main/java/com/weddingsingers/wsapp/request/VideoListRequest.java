package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Video;
import com.weddingsingers.wsapp.data.VideoList;
import com.weddingsingers.wsapp.manager.NetworkRequest;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class VideoListRequest extends AbstractRequest<NetworkResult<List<VideoList>>>{
    Request request;

    public VideoListRequest(Context context,int type) {
        HttpUrl url = getBaseUrlBuilder()
            .addPathSegment("videos")
            .addPathSegment("main")
            .addQueryParameter("type", String.valueOf(type))
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
