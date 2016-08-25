package com.weddingsingers.wsapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by Tacademy on 2016-08-25.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}

