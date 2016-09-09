package com.weddingsingers.wsapp.request;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Rating;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-23.
 */

//리뷰조회(펑점)
public class RatingRequest extends AbstractRequest<NetworkResult<Rating>> {

    Request request;

    public RatingRequest(Context context, int singerId, int rating) {

        Log.i("RatingRequest","singerId : " + singerId + ", rating : " + rating);
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("reviews")
                .addQueryParameter("sid", String.valueOf(singerId))
                .addQueryParameter("rating", String.valueOf(rating))
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();

    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Rating>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
