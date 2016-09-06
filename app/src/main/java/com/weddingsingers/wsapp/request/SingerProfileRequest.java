package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Singer;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class SingerProfileRequest extends AbstractRequest<NetworkResult<Singer>> {
    Request request;

    //일반 싱어 프로필 조회
    public SingerProfileRequest(Context context, int singerId) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("singers")
                .addPathSegment(String.valueOf(singerId))
                .build();


        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    //여기저기서 쓰이는 싱어의 작은 프로필 조회(사진,이름,코멘트 붙어있는 것을 말함)
    public SingerProfileRequest(Context context, int singerId, int isSimple) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("singers")
                .addPathSegment(String.valueOf(singerId))
                .addQueryParameter("simple", String.valueOf(isSimple))
                .build();


        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new  TypeToken<NetworkResult<Singer>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
