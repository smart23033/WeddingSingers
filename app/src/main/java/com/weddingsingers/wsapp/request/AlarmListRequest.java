package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.data.Alarm;
import com.weddingsingers.wsapp.data.NetworkResult;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class AlarmListRequest extends AbstractRequest<NetworkResult<List<Alarm>>> {
    Request mRequest;

    public AlarmListRequest(Context context) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("notifications")
                .addPathSegment("me")
                .build();

        mRequest = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }
    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<Alarm>>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return mRequest;
    }
}
