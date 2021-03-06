package com.weddingsingers.wsapp.main.reservationmgm;


import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.ChatContract;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.User;
import com.weddingsingers.wsapp.fcm.MyFirebaseMessagingService;
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


    LocalBroadcastManager mLBM;

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            intent.putExtra(MyFirebaseMessagingService.EXTRA_RESULT, true);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    public ReservedCustomerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ReservedCustomerListAdapter();
        mLBM = LocalBroadcastManager.getInstance(getContext());
    }

    @Override
    public void onStart() {
        super.onStart();
        mLBM.registerReceiver(mReceiver, new IntentFilter(MyFirebaseMessagingService.ACTION_RESERVED_CUSTOMER));
    }

    @Override
    public void onStop() {
        super.onStop();
        mLBM.unregisterReceiver(mReceiver);
    }

    private int estimateId;
    private int userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reserved_customer, container, false);

        ButterKnife.bind(this, view);



        mAdapter.setOnAdapterChatBtnClickListener(new ReservedCustomerListAdapter.OnAdapterChatBtnClickListener() {
            @Override
            public void onAdapterChatBtnClick(View view, Estimate estimate, int position) {
                Intent intent = new Intent(getContext(), ChattingActivity.class);
                User user = new User();
                user.setId(estimate.getCustomerId());
                user.setName(estimate.getCustomerName());
                user.setPhotoURL(estimate.getCustomerImage());
                user.setEmail(estimate.getCustomerImage());
                intent.putExtra(ChattingActivity.EXTRA_USER, user);
                startActivity(intent);
                /*Cursor cursor = (Cursor)mAdapter.items.get(position);
                User user = new User();
                //user.setUserId(cursor.getLong(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_SERVER_ID)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_EMAIL)));
                user.setName(cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_NAME)));
                Intent intent = new Intent(getContext(),ChattingActivity.class);
                intent.putExtra(ChattingActivity.EXTRA_USER, user);
                startActivity(intent);
                getActivity().finish();*/
            }
        });

        mAdapter.setOnAdapterResponseBtnClickListener(new ReservedCustomerListAdapter.OnAdapterResponseBtnClickListener() {
            @Override
            public void onAdapterResponseBtnClick(View view, Estimate estimate, int position) {

//                다이얼로그!!
                estimateId = estimate.getId();
                userId = estimate.getCustomerId();

                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Response")
                        .setMessage("Will you accept customer's request? or not?")
                        .setPositiveButton("ACCEPT", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                movePaymentActivity();
                            }
                        });

                builder.setNegativeButton("REJECT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        취소 요청
                        rejectReservation(TYPE_REJECT_RESERVATION);
                    }
                });

                dialog = builder.create();
                dialog.show();

            }
        });

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    private void rejectReservation(int type) {
        PaymentRequest paymentRequest = new PaymentRequest(getContext(), estimateId, userId, type);
        NetworkManager.getInstance().getNetworkData(paymentRequest, new NetworkManager.OnResultListener<NetworkResult<String>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {
                Log.i("ReservedCustomerFragment", "results : " + result.getResult().toString());
                init();
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
                            estimate.setStatus(e.getStatus());
                            estimate.setCustomerId(e.getCustomerId());
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
