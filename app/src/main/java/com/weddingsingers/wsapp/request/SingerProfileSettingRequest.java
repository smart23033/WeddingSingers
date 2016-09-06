package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Singer;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Arrays;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class SingerProfileSettingRequest  extends AbstractRequest<NetworkResult<Boolean>> {

    Request request;



    public SingerProfileSettingRequest(Context context, Singer singer) {

        String songList = "";
        for (String song : singer.getSongs())
        {
            songList += song + ",";
        }

        HttpUrl.Builder builder = getBaseUrlBuilder();
        builder.addPathSegment("singers")
                .addPathSegment("me");

        RequestBody body = new FormBody.Builder()
                .add("comment", singer.getComment())
                .add("description", singer.getDescription())
                .add("theme", singer.getTheme())
                .add("composition", singer.getComposition())
                .add("standard_price", String.valueOf(singer.getStandard()))
                .add("special_price", String.valueOf(singer.getSpecial()))
                .add("songs", songList)
                .build();

        request = new Request.Builder()
                .url(builder.build())
                .put(body)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Boolean>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}