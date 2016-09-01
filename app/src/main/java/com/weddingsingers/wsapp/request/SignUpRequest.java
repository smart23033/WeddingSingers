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
 * Created by Tacademy on 2016-08-23.
 */
public class SignUpRequest extends AbstractRequest<NetworkResult<User>> {
    Request request;

    public SignUpRequest(Context context, String email, String password,
                         String name, String phone, int type, String regId) {
        HttpUrl.Builder builder = getBaseUrlBuilder();
        builder.addPathSegment("users")
                .addPathSegment("local");

        RequestBody body = new FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .add("name",name)
                .add("phone", phone)
                .add("type", String.valueOf(type))
                .add("registrationId", regId)
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
