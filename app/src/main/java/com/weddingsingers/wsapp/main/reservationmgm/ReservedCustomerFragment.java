package com.weddingsingers.wsapp.main.reservationmgm;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.function.chatting.chatting.ChattingActivity;
import com.weddingsingers.wsapp.function.payment.payment.PaymentActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.EstimateListRequest;
import com.weddingsingers.wsapp.request.PaymentRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReservedCustomerFragment extends Fragment {

    private static final int TAB_ESTIMATE_LIST = 1;
    private static final int TYPE_REJECT_RESERVATION = 11;

    @BindView(R.id.reserved_customer_rv_list)
    RecyclerView recyclerView;

    ReservedCustomerListAdapter mAdapter;

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    public ReservedCustomerFragment() {
        // Required empty public constructor
    }

    private int estimateId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reserved_customer, container, false);

        ButterKnife.bind(this, view);

        mAdapter = new ReservedCustomerListAdapter();

        mAdapter.setOnAdapterChatBtnClickListener(new ReservedCustomerListAdapter.OnAdapterChatBtnClickListener() {
            @Override
            public void onAdapterChatBtnClick(View view, Estimate estimate, int position) {
                Intent intent = new Intent(getContext(), ChattingActivity.class);
                startActivity(intent);
            }
        });

        mAdapter.setOnAdapterAcceptBtnClickListener(new ReservedCustomerListAdapter.OnAdapterAcceptBtnClickListener() {
            @Override
            public void onAdapterAcceptBtnClick(View view, Estimate estimate, int position) {
                Toast.makeText(getContext(), "reservation accepted", Toast.LENGTH_SHORT).show();
                estimateId = estimate.getId();
                movePaymentActivity();

            }
        });

        mAdapter.setOnAdapterCancelBtnClickListener(new ReservedCustomerListAdapter.OnAdapterCancelBtnClickListener() {
            @Override
            public void onAdapterCancelBtnClick(View view, Estimate estimate, int position) {
                Log.i("ReservedCustomerFragment", "Cancel Btn Click");
            }
        });

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    private void acceptReservation(int type) {
        PaymentRequest paymentRequest = new PaymentRequest(getContext(), estimateId, type);
        NetworkManager.getInstance().getNetworkData(paymentRequest, new NetworkManager.OnResultListener<NetworkResult<String>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {
                Log.i("ReservedCustomerFragment", "result : " + result.getResult().toString());
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e) {
                Log.i("ReservedCustomerFragment", "errorMessage : " + errorMessage);
            }
        });
    }

    private void init() {
        mAdapter.clear();
        EstimateListRequest estimateListRequest = new EstimateListRequest(getContext(), TAB_ESTIMATE_LIST);
        NetworkManager.getInstance().getNetworkData(estimateListRequest, new NetworkManager.OnResultListener<NetworkResult<List<Estimate>>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<List<Estimate>>> request, NetworkResult<List<Estimate>> result) {

                        for (Estimate e : result.getResult()) {
                            Estimate estimate = new Estimate();
                            estimate.setId(e.getId());
                            estimate.setCustomerName(e.getCustomerName());
                            estimate.setCustomerImage(e.getCustomerImage());
                            estimate.setDate(e.getDate());
                            estimate.setLocation(e.getLocation());
                            estimate.setSongs(e.getSongs());
                            estimate.setSpecial(e.getSpecial());
                            mAdapter.add(estimate);

                        }
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<List<Estimate>>> request,
                                       int errorCode, String errorMessage, Throwable e) {

                    }
                }

        );

    }


    private void movePaymentActivity() {
        Intent intent = new Intent(getActivity(), PaymentActivity.class);

        //deprecated. 왜냐하면 고객의 최종 결제가 떨어지지 않았기 때문에 일정목록으로 가봤자 방금 승인한거 안나옴.
//        intent.putExtra(PaymentActivity.FRAG_NAME,"DetailScheduleFragment");
//        startActivityForResult(intent, MainActivity.RC_FRAG);


//        ReservedCustomerFragment로 돌아와라!
        intent.putExtra(PaymentActivity.FRAG_NAME, "ReservedCustomerFragment");
        intent.putExtra(PaymentActivity.EXTRA_ESTIMATE_ID, estimateId);
        startActivity(intent);
    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        switch (requestCode) {
//            case MainActivity.RC_FRAG:
//                if (resultCode == PaymentActivity.RESULT_OK) {
//                    ((MainActivity) getActivity()).changeFragment(new ScheduleMgmFragment());
//                    startActivity(new Intent(getActivity(), DetailScheduleActivity.class));
//                }
//                break;
//        }
//    }


}
