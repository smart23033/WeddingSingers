package com.weddingsingers.wsapp.request;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.NetworkResult;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-12.
 */
public class VideoDeleteRequest extends AbstractRequest<NetworkResult<String>> {
    Request request;

    public VideoDeleteRequest(Context context, ArrayList<Integer> videoId) {
        HttpUrl.Builder builder = getBaseUrlBuilder();
        builder.addPathSegment("videos");

        FormBody.Builder formBuilder = new FormBody.Builder();

        ArrayList<String> videoIds = new ArrayList<>();

        for (int i = 0; i < videoId.size(); i++) {
            Log.i("delete request", String.valueOf(videoId.get(i)));
            videoIds.add(String.valueOf(videoId.get(i)));
        }

        for (String item : videoIds) {
            formBuilder.add("vid", item);
        }

        RequestBody body = formBuilder.build();


        request = new Request.Builder()
                .url(builder.build())
                .delete(body)
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