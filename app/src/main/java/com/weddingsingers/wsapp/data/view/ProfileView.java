package com.weddingsingers.wsapp.data.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.weddingsingers.wsapp.R;

/**
 * Created by SJSJ on 2016-08-28.
 */

//Compound Widget
public class ProfileView extends FrameLayout{

    public ProfileView(Context context) {
       this(context, null);
    }

    public ProfileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    ImageView imageView;
    TextView singerNameTextView;
    TextView commentTextView;
    Button reservationBtn;

    private void init(){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.view_profile,this);
    }

}
