package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.User;

import java.io.File;
import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class ModifyUserRequest extends AbstractRequest<NetworkResult<Boolean>> {

    MediaType jpeg = MediaType.parse("image/jpeg");
    Request request;

    public ModifyUserRequest(Context context, String password, File file) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("users")
                .addPathSegment("me")
                .build();

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("password", password);

        if (file != null) {
            builder.addFormDataPart("photo", file.getName(), RequestBody.create(jpeg, file));
        }

        RequestBody body = builder.build();

        request = new Request.Builder()
                .url(url)
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
