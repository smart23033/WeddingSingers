package com.weddingsingers.wsapp.function.schedulemgm.schedulemgm;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.view.EstimateView;
import com.weddingsingers.wsapp.main.MainActivity;
import com.weddingsingers.wsapp.main.reservationmgm.ReservedCustomerListAdapter;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.EstimateListRequest;
import com.weddingsingers.wsapp.request.EstimateRequest;
import com.weddingsingers.wsapp.request.PaymentRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CancelScheduleFragment extends Fragment {

    public final static int FRAG_MY_PAGE = 200;
    public static final String KEY_FRAG_NAME = "fragmentName";
    public final static String KEY_ESTIMATE_ID = "estimateId";

    public static final int TYPE_CANCEL_SCHEDULE = 31;

    public CancelScheduleFragment() {
        // Required empty public constructor
    }


    public static CancelScheduleFragment newInstance(int estimateId) {
        CancelScheduleFragment fragment = new CancelScheduleFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_ESTIMATE_ID, estimateId);
        fragment.setArguments(args);
        return fragment;
    }


    int estimateId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            estimateId = getArguments().getInt(KEY_ESTIMATE_ID);
        }
        scheduleListAdapter = new ScheduleListAdapter();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cancel_schedule, container, false);

        ButterKnife.bind(this, view);

        init();

        return view;

    }

    @BindView(R.id.cancel_schedule_ev_profile)
    EstimateView estimateView;

    ScheduleListAdapter scheduleListAdapter;
    ReservedCustomerListAdapter reservedCustomerListAdapter;

    void init() {
        EstimateRequest estimateRequest = new EstimateRequest(getContext(), estimateId);
        NetworkManager.getInstance().getNetworkData(estimateRequest, new NetworkManager.OnResultListener<NetworkResult<Estimate>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Estimate>> request, NetworkResult<Estimate> result) {
                estimateView.setUserImage(result.getResult().getCustomerImage());
                estimateView.setUserName(result.getResult().getCustomerName());
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

    @OnClick(R.id.cancel_schedule_btn_cancel)
    void onCancelBtnClick() {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Cancel Schedule")
                .setMessage("Are you sure? You will get penalty")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cancelSchedule(TYPE_CANCEL_SCHEDULE);
                    }
                });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(),"cancel",Toast.LENGTH_SHORT).show();
            }
        });

        dialog = builder.create();
        dialog.show();
    }

    private void cancelSchedule(int type) {
        PaymentRequest paymentRequest = new PaymentRequest(getContext(), estimateId, type);
        NetworkManager.getInstance().getNetworkData(paymentRequest, new NetworkManager.OnResultListener<NetworkResult<String>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {
                Toast.makeText(getContext(), "CancelSchedule Request Success", Toast.LENGTH_SHORT).show();
                scheduleListAdapter.remove(estimateId);
                getActivity().finish();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "CancelSchedule Request Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void moveMyPageFragment() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra(MainActivity.FRAG_NAME, FRAG_MY_PAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        getActivity().finish();
    }

    private void moveMainActivity() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
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

}
