package com.weddingsingers.wsapp.function.mypage.singervideomgm;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoAddFragment extends DialogFragment {

    public VideoAddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_add, container, false);

        ButterKnife.bind(this, view);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        if (getDialog() != null) {
            getDialog().setCanceledOnTouchOutside(false);
        }

        return view;
    }

    @OnClick(R.id.video_add_btn_apply)
    public void onApplyClick() {
        dismiss();
    }

    @OnClick(R.id.video_add_btn_cancel)
    public void onCancelClick() {
        dismiss();
    }

}
