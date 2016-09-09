package com.weddingsingers.wsapp.function.mypage.singervideomgm;


import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.SingerVideoAdd;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.SingerVideoAddRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoAddFragment extends DialogFragment {

    @BindView(R.id.video_add_et_title)
    EditText titleInput;

    @BindView(R.id.video_add_et_url)
    EditText urlInput;

    @BindView(R.id.video_add_et_hahstag)
    EditText hashInput;

    public VideoAddFragment() {
        // Required empty public constructor
    }

    private DialogInterface.OnDismissListener onDismissListener;

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
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

    SingerVideoAdd singerVideoAdd;


    @OnClick(R.id.video_add_btn_apply)
    public void onApplyClick() {

        String[] hashTemp = hashInput.getText().toString().split(",");

        ArrayList<String> hash = new ArrayList<>();
        for (String item : hashTemp) {
            hash.add(item);
        }

        String dTime = "";
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT+9"));
        dTime = dateFormatGmt.format(new Date()).toString();

        singerVideoAdd = new SingerVideoAdd();

        singerVideoAdd.setTitle(titleInput.getText().toString());
        singerVideoAdd.setUrl(urlInput.getText().toString());
        singerVideoAdd.setHash(hash);
        singerVideoAdd.setWriteDTime(dTime);

        SingerVideoAddRequest request = new SingerVideoAddRequest(getContext(), singerVideoAdd);

        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Boolean>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Boolean>> request, NetworkResult<Boolean> result) {
                dismiss();
                //Toast.makeText(getActivity(), "success code : " + result.getCode(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Boolean>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getActivity(), "SingerMyPageFragment fail - " + errorMessage, Toast.LENGTH_SHORT).show();

            }
        });

    }

    @OnClick(R.id.video_add_btn_cancel)
    public void onCancelClick() {
        dismiss();
    }

}
