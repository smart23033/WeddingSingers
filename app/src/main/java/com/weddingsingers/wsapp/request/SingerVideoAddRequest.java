package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.SingerVideoAdd;
import com.weddingsingers.wsapp.data.VideoList;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * Created by Tacademy on 2016-09-07.
 */
public class SingerVideoAddRequest extends AbstractRequest<NetworkResult<Boolean>> {

    Request request;

    public SingerVideoAddRequest(Context context, SingerVideoAdd singerVideoAdd) {
        HttpUrl.Builder builder = getBaseUrlBuilder();
        builder.addPathSegment("videos");

        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("title", singerVideoAdd.getTitle())
                .add("url", singerVideoAdd.getUrl())
                .add("write_dtime", singerVideoAdd.getWriteDtime());

        for (String item : singerVideoAdd.getHash()) {
            formBuilder.add("hash", item);
        }

        RequestBody body = formBuilder.build();

        request = new Request.Builder()
                .url(builder.build())
                .post(body)
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
