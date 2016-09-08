package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Review;
import com.weddingsingers.wsapp.data.User;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class SingerReviewRequest extends AbstractRequest<NetworkResult<List<Review>>> {

    Request request;

    public SingerReviewRequest(Context context, int singerId) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("reviews")
                .addQueryParameter("sid", String.valueOf(singerId))
                .build();
        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<Review>>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}