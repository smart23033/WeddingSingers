package com.weddingsingers.wsapp.request;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Search;
import com.weddingsingers.wsapp.data.SearchResult;
import com.weddingsingers.wsapp.data.VideoList;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class SearchRequest extends AbstractRequest<NetworkResult<List<SearchResult>>> {
    Request request;

    public SearchRequest(Context context, Search search) {

        Log.i("SearchRequest", "search.getKeyword() : " + search.getKeyword());

        HttpUrl.Builder url = getBaseUrlBuilder();
        url.addPathSegment("videos");
        url.addQueryParameter("theme", String.valueOf(search.getTheme()));
        url.addQueryParameter("location", String.valueOf(search.getLocation()));
        url.addQueryParameter("price", String.valueOf(search.getPrice()));
        url.addQueryParameter("composition", String.valueOf(search.getComposition()));

        if (!TextUtils.isEmpty(search.getKeyword())) {
            url.addQueryParameter("keyword", search.getKeyword());
        }

        url.build();


        request = new Request.Builder()
                .url(String.valueOf(url))
                .tag(context)
                .build();

    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<SearchResult>>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
