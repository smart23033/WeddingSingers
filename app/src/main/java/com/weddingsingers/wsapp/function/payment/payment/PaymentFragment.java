package com.weddingsingers.wsapp.function.payment.payment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.view.EstimateView;
import com.weddingsingers.wsapp.main.MainActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.EstimateRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {

    private static final String ARG_FRAG_NAME = "fragmentName";
    private static final String ARG_ESTIMATE_ID = "estimateId";

    private String fragmentName;
    private int estimateId;
    private static PaymentFragment instance;

    @BindView(R.id.payment_ev_profile)
    EstimateView estimateView;

    public PaymentFragment() {
        // Required empty public constructor
    }

    public static PaymentFragment newInstance(String fragmentName, int estimateId) {
        PaymentFragment fragment = new PaymentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FRAG_NAME, fragmentName);
        args.putInt(ARG_ESTIMATE_ID, estimateId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            fragmentName = getArguments().getString(ARG_FRAG_NAME);
            estimateId = getArguments().getInt(ARG_ESTIMATE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        ButterKnife.bind(this, view);

        init();

        return view;
    }

    void init() {
        EstimateRequest estimateRequest = new EstimateRequest(getContext(), estimateId);
        NetworkManager.getInstance().getNetworkData(estimateRequest, new NetworkManager.OnResultListener<NetworkResult<Estimate>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Estimate>> request, NetworkResult<Estimate> result) {
//               이미지 나중에
//                estimateView.setUserImage(result.getResult().getSingerImage());
                estimateView.setUserName(result.getResult().getSingerName());
                estimateView.setLocation(result.getResult().getLocation());
                estimateView.setDate(result.getResult().getDate());
                estimateView.setSong(result.getResult().getSongs());
                estimateView.setSpecial(result.getResult().getSpecial());

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Estimate>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }

    @OnClick(R.id.payment_btn_remittance)
    void onRemittanceBtnClick() {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Remittance")
                .setMessage("Remittance")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (fragmentName.equals("DetailScheduleFragment")) {
                            moveDetailScheduleFragment();
                        } else {
                            moveReservedOneFragment();
                        }
                    }
                });
        dialog = builder.create();
        dialog.show();
    }

    @OnClick(R.id.payment_btn_kakao_pay)
    void onKakaoPayBtnClick() {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Kakao Pay")
                .setMessage("Kakao Pay")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (fragmentName.equals("DetailScheduleFragment")) {
                            moveDetailScheduleFragment();
                        } else {
                            moveReservedOneFragment();
                        }
                    }
                });
        dialog = builder.create();
        dialog.show();
    }

    //    프레그먼트 이동 + 액티비티 하나 띄워줘야 함.
    private void moveDetailScheduleFragment() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.FRAG_NAME, "DetailScheduleFragment");
        getActivity().setResult(PaymentActivity.RESULT_OK, intent);
        getActivity().finish();
    }

    private void moveReservedOneFragment() {
        Intent intent = new Intent();
        intent.putExtra(PaymentActivity.FRAG_NAME, "ReservedOneFragment");
        getActivity().setResult(PaymentActivity.RESULT_OK, intent);
        getActivity().finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                getActivity().finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

}
