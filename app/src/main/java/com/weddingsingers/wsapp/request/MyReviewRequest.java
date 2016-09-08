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
 * Created by Tacademy on 2016-09-07.
 */


//리뷰조회 목록
// * 리뷰조회(평점)은 RatingRequest로 따로 관리 중
public class MyReviewRequest extends AbstractRequest<NetworkResult<List<Review>>> {

    Request request;

    public MyReviewRequest(Context context, Review review) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("reviews")
                .addPathSegment("me")
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