package com.weddingsingers.wsapp.function.reservation.cancelreservation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CancelReservationFragment extends Fragment {

    private static final String ARG_MESSAGE = "param1";
    private String message;
    private static CancelReservationFragment instance;

    public CancelReservationFragment() {
        // Required empty public constructor
    }


    @BindView(R.id.cancel_reservation_riv_profile)
    RoundedImageView singerImageView;


    public static CancelReservationFragment newInstance(String message) {
        CancelReservationFragment fragment = new CancelReservationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cancel_reservation, container, false);

        ButterKnife.bind(this, view);

        singerImageView.mutateBackground(true);
        singerImageView.setOval(true);
        singerImageView.setBackgroundColor(Color.LTGRAY);

        return view;
    }

    @OnClick(R.id.cancel_reservation_btn_cancel)
    void onCancelBtnClicked() {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Cancel complete")
                .setMessage("Cancel complete,\ncheck your account please")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moveMainActivity();
                    }
                });
        dialog = builder.create();
        dialog.show();
    }

    private void moveMainActivity() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

}
