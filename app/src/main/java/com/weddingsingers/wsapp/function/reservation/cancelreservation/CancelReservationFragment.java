package com.weddingsingers.wsapp.function.reservation.cancelreservation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.view.EstimateView;
import com.weddingsingers.wsapp.main.MainActivity;
import com.weddingsingers.wsapp.main.reservationmgm.ReservationListAdapter;
import com.weddingsingers.wsapp.main.reservationmgm.ReservedOneAdapter;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.EstimateRequest;
import com.weddingsingers.wsapp.request.PaymentRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CancelReservationFragment extends Fragment {

    public static final String ARG_ESTIMATE_ID = "estimateId";
    public static CancelReservationFragment instance;
    public int estimateId;
    public static final int TYPE_CANCEL_RESERVATION = 21;

    @BindView(R.id.cancel_reservation_ev_profile)
    EstimateView estimateView;

    ReservedOneAdapter mAdapter;

    public CancelReservationFragment() {
        // Required empty public constructor
    }

    public static CancelReservationFragment newInstance(int estimateId) {
        CancelReservationFragment fragment = new CancelReservationFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ESTIMATE_ID, estimateId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            estimateId = getArguments().getInt(ARG_ESTIMATE_ID);
        }
        mAdapter = new ReservedOneAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cancel_reservation, container, false);

        ButterKnife.bind(this, view);

        init();

        return view;
    }

    int userId;
    void init(){
        EstimateRequest estimateRequest = new EstimateRequest(getContext(), estimateId);
        NetworkManager.getInstance().getNetworkData(estimateRequest, new NetworkManager.OnResultListener<NetworkResult<Estimate>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Estimate>> request, NetworkResult<Estimate> result) {

                userId = result.getResult().getSingerId();
                estimateView.setUserImage(result.getResult().getSingerImage());
                estimateView.setUserName(result.getResult().getSingerName());
                estimateView.setLocation(result.getResult().getLocation());
                estimateView.setDate(result.getResult().getDate());
                estimateView.setSong(result.getResult().getSongs());
                estimateView.setSpecial(result.getResult().getSpecial());

                Log.i("CancelReservationFragment", "userId : " + userId);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Estimate>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }

    @OnClick(R.id.cancel_reservation_btn_cancel)
    void onCancelBtnClick() {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Cancel complete")
                .setMessage("Cancel complete,\ncheck your account please")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cancelReservation();
                    }
                });
        dialog = builder.create();
        dialog.show();
    }

    private void cancelReservation() {
        PaymentRequest paymentRequest = new PaymentRequest(getContext(), estimateId, userId, TYPE_CANCEL_RESERVATION);
        NetworkManager.getInstance().getNetworkData(paymentRequest, new NetworkManager.OnResultListener<NetworkResult<String>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {
                Toast.makeText(getContext(), "cancelReservation Request Success", Toast.LENGTH_SHORT).show();
                mAdapter.remove(estimateId);
                Intent intent = new Intent();
                intent.putExtra(CancelReservationActivity.FRAG_NAME, "ReservationListFragment");
                getActivity().setResult(CancelReservationActivity.RESULT_OK, intent);
                getActivity().finish();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "cancelReservation Request Fail", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });
    }

    private void moveMainActivity() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra(MainActivity.FRAG_NAME, MainActivity.FRAG_MAIN);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        getActivity().finish();
    }

}
