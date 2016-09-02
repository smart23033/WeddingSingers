package com.weddingsingers.wsapp.main.home;

import android.content.Context;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by Tacademy on 2016-09-02.
 */
public class DummyContentFactory implements TabHost.TabContentFactory {
    Context mContext;

    public DummyContentFactory(Context context) {
        mContext = context;
    }

    @Override
    public View createTabContent(String tag) {
        TextView textView = new TextView(mContext);
        return textView;
    }
}
