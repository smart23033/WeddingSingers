package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.facebook.AccessToken;
import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.User;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class FacebookLoginRequest extends AbstractRequest<NetworkResult<User>> {
    Request request;

    public FacebookLoginRequest(Context context, String  token) {
        HttpUrl.Builder builder = getBaseUrlBuilder();
        builder.addPathSegment("auth")
                .addPathSegment("facebook")
                .addPathSegment("token");


        RequestBody body = new FormBody.Builder()
                .add("access_token", token)
                .build();

        request = new Request.Builder()
                .url(builder.build())
                .post(body)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<User>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
