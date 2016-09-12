package com.weddingsingers.wsapp.main.reservationmgm;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.weddingsingers.wsapp.function.reservation.cancelreservation.CancelReservationActivity;
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
public class ReservationListFragment extends Fragment {

    private static final int TAB_RESERVATION_LIST = 1;
    private static final String ARG_MESSAGE = "param1";
    public static final int TYPE_CANCEL_RESERVATION = 21;

    public ReservationListFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.reservation_list_rv_list)
    RecyclerView recyclerView;

    ReservationListAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ReservationListAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }
    int estimateId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservation_list, container, false);

        ButterKnife.bind(this, view);

        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnAdapterPayBtnClickListener(new ReservationListAdapter.OnAdapterPayBtnClickListener() {
            @Override
            public void onAdapterPayBtnClick(View view, Estimate estimate, int position) {

                estimateId = estimate.getId();
                ((ReservationMgmFragment) getParentFragment()).startPaymentActivity(estimateId);
            }
        });

        mAdapter.setOnAdapterChatBtnClickListener(new ReservationListAdapter.OnAdapterChatBtnClickListener() {
            @Override
            public void onAdapterChatBtnClick(View view, Estimate estimate, int position) {
                Intent intent = new Intent(getContext(), ChattingActivity.class);
                startActivity(intent);
            }
        });

        mAdapter.setOnAdapterCancelBtnClickListener(new ReservationListAdapter.OnAdapterCancelBtnClickListener() {
            @Override
            public void onAdapterCancelBtnClick(View view, Estimate estimate, int position) {
//                Intent intent = new Intent(getContext(), CancelReservationActivity.class);
//                estimateId = estimate.getId();
//                intent.putExtra(CancelReservationActivity.EXTRA_ESTIMATE_ID, estimateId);
//                startActivity(intent);
                estimateId = estimate.getId();

                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Cancel complete")
                        .setMessage("Are you sure?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                cancelReservation();
                            }
                        });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog = builder.create();
                dialog.show();

            }
        });

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(manager);

//        init();

        return view;
    }


    private void cancelReservation() {
        PaymentRequest paymentRequest = new PaymentRequest(getContext(), estimateId, TYPE_CANCEL_RESERVATION);
        NetworkManager.getInstance().getNetworkData(paymentRequest, new NetworkManager.OnResultListener<NetworkResult<String>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {
                Toast.makeText(getContext(), "cancelReservation Request Success", Toast.LENGTH_SHORT).show();
                init();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "cancelReservation Request Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        mAdapter.clear();
        EstimateListRequest estimateListRequest = new EstimateListRequest(getContext(), TAB_RESERVATION_LIST);
        NetworkManager.getInstance().getNetworkData(estimateListRequest, new NetworkManager.OnResultListener<NetworkResult<List<Estimate>>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<List<Estimate>>> request, NetworkResult<List<Estimate>> result) {

                        for (Estimate e : result.getResult()) {
                            Log.i("ReservationListFragment","e.getSingerImage() : " + e.getSingerImage());
                            Estimate estimate = new Estimate();
                            estimate.setId(e.getId());
                            estimate.setSingerName(e.getSingerName());
                            estimate.setSingerImage(e.getSingerImage());
                            estimate.setDate(e.getDate());
                            estimate.setLocation(e.getLocation());
                            estimate.setSongs(e.getSongs());
                            estimate.setStatus(e.getStatus());
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
}