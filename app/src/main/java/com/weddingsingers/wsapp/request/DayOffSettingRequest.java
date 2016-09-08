package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.NetworkResult;

import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class DayOffSettingRequest extends AbstractRequest<NetworkResult<String>> {
    Request request;

    public DayOffSettingRequest(Context context, ArrayList<String> date) {
        HttpUrl.Builder builder = getBaseUrlBuilder();
        builder.addPathSegment("singers")
                .addPathSegment("me")
                .addPathSegment("holidays");


        FormBody.Builder formBuilder = new FormBody.Builder();
        for (String d : date) {
            formBuilder.add("update_dates", String.valueOf(d));
        }

        RequestBody body = formBuilder.build();

        request = new Request.Builder()
                .url(builder.build())
                .put(body)
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
