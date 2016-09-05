package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.User;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-05.
 */
public class MessageSendRequest  extends AbstractRequest<NetworkResult<String>> {
    Request request;

    public MessageSendRequest(Context context, User user, String message) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("sendmessage")
                .build();
        RequestBody body = new FormBody.Builder()
                .add("receiver", "" + user.getId())
                .add("message", message)
                .build();

        request = new Request.Builder()
                .url(url)
                .post(body)
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