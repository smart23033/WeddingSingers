package com.weddingsingers.wsapp.request;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.NetworkResult;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class FavoriteRequest extends AbstractRequest<NetworkResult<String>> {
    Request request;

    public FavoriteRequest(Context context, int videoId, int singerId) {
        Log.i("FavoriteRequest","videoId : " + videoId);

        HttpUrl.Builder builder = getBaseUrlBuilder();
        builder.addPathSegment("favorites");

        RequestBody body = new FormBody.Builder()
                .add("vid", String.valueOf(videoId))
                .add("sid", String.valueOf(singerId))
                .build();

        request = new Request.Builder()
                .url(builder.build())
                .post(body)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<String>>() {}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }

}