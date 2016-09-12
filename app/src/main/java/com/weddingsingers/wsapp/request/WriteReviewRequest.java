package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Review;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class WriteReviewRequest extends AbstractRequest<NetworkResult<String>>{

    Request request;

    public WriteReviewRequest(Context context, Review review) {
        HttpUrl.Builder builder = getBaseUrlBuilder();
        builder.addPathSegment("reviews");

        RequestBody body = new FormBody.Builder()
                .add("rid", "" + review.getReservationId())
                .add("content", review.getContent())
                .add("point", "" + review.getIntPoint())
                .add("write_dtime", review.getWriteDTime())
                .build();

        request = new Request.Builder()
                .url(builder.build())
                .post(body)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<String>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
