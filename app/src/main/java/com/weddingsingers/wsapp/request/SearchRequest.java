package com.weddingsingers.wsapp.request;

import android.content.Context;

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
public class SearchRequest  extends AbstractRequest<NetworkResult<List<SearchResult>>> {
    Request request;

    public SearchRequest(Context context, Search search) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("videos")
                .addQueryParameter("theme", String.valueOf(search.getTheme()))
                .addQueryParameter("location", String.valueOf(search.getLocation()))
                .addQueryParameter("start_date",search.getStartDate())
                .addQueryParameter("end_date",search.getEndDate())
                .addQueryParameter("price", String.valueOf(search.getPrice()))
                .addQueryParameter("composition", String.valueOf(search.getComposition()))
                .addQueryParameter("hash",search.getKeyword())
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();

    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<SearchResult>>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
