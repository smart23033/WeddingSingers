package com.weddingsingers.wsapp.function.mypage.singervideomgm;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.weddingsingers.wsapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoModifyFragment extends DialogFragment {


    public VideoModifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_video_modify, container, false);

        ButterKnife.bind(this, view);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        if (getDialog() != null) {
            getDialog().setCanceledOnTouchOutside(false);
        }

        return view;
    }

    @OnClick(R.id.video_modify_btn_apply)
    public void onApplyClick() {
        dismiss();
    }

    @OnClick(R.id.video_modify_btn_cancel)
    public void onCancelClick() {
        dismiss();
    }
}
