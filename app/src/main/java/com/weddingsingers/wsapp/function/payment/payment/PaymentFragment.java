package com.weddingsingers.wsapp.function.payment.payment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.view.EstimateView;
import com.weddingsingers.wsapp.function.schedulemgm.schedulemgm.ScheduleListAdapter;
import com.weddingsingers.wsapp.main.MainActivity;
import com.weddingsingers.wsapp.main.reservationmgm.ReservationListAdapter;
import com.weddingsingers.wsapp.main.reservationmgm.ReservedCustomerListAdapter;
import com.weddingsingers.wsapp.main.reservationmgm.ReservedOneAdapter;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.EstimateRequest;
import com.weddingsingers.wsapp.request.PaymentRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {

    public static final String ARG_FRAG_NAME = "fragmentName";
    public static final String ARG_ESTIMATE_ID = "estimateId";
    public static final String FRAG_RESERVED_ONE = "ReservedOneFragment";
    public static final String FRAG_RESERVED_CUSTOMER = "ReservedCustomerFragment";

    //    싱어가 고객의 예약에 대한 수락으로 보증금 지불할 때
    private static final int TYPE_ACCEPT_RESERVATION = 20;

    //    고객이 예약하고나서 싱어가 수락해가지고 실제 금액 지불할 때
    private static final int TYPE_PAYMENT_SUCCESS = 30;


    private String fragmentName;
    private int estimateId;
    private static PaymentFragment instance;

    @BindView(R.id.payment_ev_profile)
    EstimateView estimateView;

    ReservationListAdapter reservationListAdapter;
    ReservedOneAdapter reservedOneAdapter;
    ReservedCustomerListAdapter reservedCustomerListAdapter;
    ScheduleListAdapter scheduleListAdapter;

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
        reservationListAdapter = new ReservationListAdapter();
        reservedOneAdapter = new ReservedOneAdapter();
        reservedCustomerListAdapter = new ReservedCustomerListAdapter();
        scheduleListAdapter = new ScheduleListAdapter();

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
                Log.i("PaymentFragment","fragName : " + fragmentName);
                if(fragmentName.equals(FRAG_RESERVED_CUSTOMER)) {
                    estimateView.setUserImage(result.getResult().getCustomerImage());
                    estimateView.setUserName(result.getResult().getCustomerName());
                } else if(fragmentName.equals(FRAG_RESERVED_ONE)) {
                    estimateView.setUserImage(result.getResult().getSingerImage());
                    estimateView.setUserName(result.getResult().getSingerName());
                }

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

//                        사실 DetailScheduleFragment로 갈 필요가 없다. 그냥 백으로 가면 된다.
//                        왜냐하면 싱어가 수락을 한다고해도, 고객이 예약목록에서 초록불인 싱어를 선택해서 최종 결제가 나는 것이기 때문에 싱어일정에 추가가 되지 않는다.
//                         나중에 지우자..

//                        if (fragmentName.equals("DetailScheduleFragment")) {
//                            makePayment(TYPE_ACCEPT_RESERVATION);
//                            moveDetailScheduleFragment();
//                        } else {}

                        if (fragmentName.equals(FRAG_RESERVED_CUSTOMER)) {
                            makePayment(TYPE_ACCEPT_RESERVATION);
                        } else {
                            makePayment(TYPE_PAYMENT_SUCCESS);
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

//                        사실 DetailScheduleFragment로 갈 필요가 없다. 그냥 백으로 가면 된다.
//                        왜냐하면 싱어가 수락을 한다고해도, 고객이 예약목록에서 초록불인 싱어를 선택해서 최종 결제가 나는 것이기 때문에 싱어일정에 추가가 되지 않는다.
//                         나중에 지우자.. 참고용으로 사용하도록 하자 일단.
//                        if (fragmentName.equals("DetailScheduleFragment")) {
//                            makePayment(TYPE_ACCEPT_RESERVATION);
//                            moveDetailScheduleFragment();
//                        }

                        if (fragmentName.equals(FRAG_RESERVED_CUSTOMER)) {
                            makePayment(TYPE_ACCEPT_RESERVATION);
                        } else {
                            makePayment(TYPE_PAYMENT_SUCCESS);
                        }

                    }
                });
        dialog = builder.create();
        dialog.show();
    }

    private void makePayment(int type) {
        PaymentRequest paymentRequest = new PaymentRequest(getContext(), estimateId, type);
        NetworkManager.getInstance().getNetworkData(paymentRequest, new NetworkManager.OnResultListener<NetworkResult<String>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {
                Toast.makeText(getContext(), "Payment Request Success", Toast.LENGTH_SHORT).show();
                if(fragmentName.equals(FRAG_RESERVED_CUSTOMER)){
                    reservedCustomerListAdapter.remove(estimateId);
                    scheduleListAdapter.add(estimateId);
                    getActivity().finish();
                }else if (fragmentName.equals(FRAG_RESERVED_ONE)){
                    reservationListAdapter.remove(estimateId);
                    reservedOneAdapter.add(estimateId);
                    moveReservedOneFragment();
                }
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "Payment Request Fail", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });
    }

    //    프레그먼트 이동 + 액티비티 하나 띄워줘야 함. deprecated
    private void moveDetailScheduleFragment() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.FRAG_NAME, "DetailScheduleFragment");
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
