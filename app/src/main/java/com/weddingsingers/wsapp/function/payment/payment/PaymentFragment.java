package com.weddingsingers.wsapp.function.payment.payment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Payment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {

    private static final String ARG_MESSAGE = "param1";

    private String message;
    private static PaymentFragment instance;

    public PaymentFragment() {
        // Required empty public constructor
    }

    public static PaymentFragment newInstance(String message) {
        PaymentFragment fragment = new PaymentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            message = getArguments().getString(ARG_MESSAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.payment_btn_remittance)
    void onRemittanceBtnClicked(){
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Remittance")
                .setMessage("Remittance")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moveReservedOneFragment();
                    }
                });
        dialog = builder.create();
        dialog.show();
    }

    @OnClick(R.id.payment_btn_kakao_pay)
    void onKakaoPayBtnClicked(){
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Kakao Pay")
                .setMessage("Kakao Pay")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moveReservedOneFragment();
                    }
                });
        dialog = builder.create();
        dialog.show();
    }

    private void moveReservedOneFragment(){
        Intent intent = new Intent();
        intent.putExtra(PaymentActivity.RESULT_MESSAGE,"ReservedOneFragment");
        getActivity().setResult(PaymentActivity.RESULT_OK, intent);
        getActivity().finish();
    }


}
