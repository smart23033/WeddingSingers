package com.weddingsingers.wsapp.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.weddingsingers.wsapp.Utils;
import com.weddingsingers.wsapp.data.ChatMessage;
import com.weddingsingers.wsapp.data.NetworkResult;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-13.
 */

public class MessageListRequest extends AbstractRequest<NetworkResult<List<ChatMessage>>> {
    Request mRequest;
    public MessageListRequest(Context context, Date date) {
        String dateString = Utils.convertTimeToString(date);
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("chatting")
                .addQueryParameter("lastDate",dateString)
                .build();
        mRequest = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }
    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<ChatMessage>>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return mRequest;
    }
}
