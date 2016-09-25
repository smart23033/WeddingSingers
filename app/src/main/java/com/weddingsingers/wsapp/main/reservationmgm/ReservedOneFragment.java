package com.weddingsingers.wsapp.main.reservationmgm;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.ChatContract;
import com.weddingsingers.wsapp.data.Estimate;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.User;
import com.weddingsingers.wsapp.function.chatting.chatting.ChattingActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.EstimateListRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReservedOneFragment extends Fragment {

    private static final int TAB_RESERVED_ONE = 2;

    @BindView(R.id.reserved_one_rv_list)
    RecyclerView recyclerView;

    ReservedOneAdapter mAdapter;

    public ReservedOneFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_reserved_one, container, false);

        ButterKnife.bind(this,view);

        mAdapter = new ReservedOneAdapter();

        mAdapter.setOnAdapterChatBtnClickListener(new ReservedOneAdapter.OnAdapterChatBtnClickListener() {
            @Override
            public void onAdapterChatBtnClick(View view, Estimate profile, int position) {
                Intent intent = new Intent(getContext(), ChattingActivity.class);
                User user = new User();
                user.setId(profile.getSingerId());
                user.setName(profile.getSingerName());
                user.setPhotoURL(profile.getSingerImage());
                user.setEmail(profile.getSingerName());
                intent.putExtra(ChattingActivity.EXTRA_USER, user);
                startActivity(intent);
                /*Cursor cursor = (Cursor)mAdapter.items.get(position);
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_SERVER_ID)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_EMAIL)));
                user.setName(cursor.getString(cursor.getColumnIndex(ChatContract.ChatUser.COLUMN_NAME)));
                Intent intent = new Intent(getContext(),ChattingActivity.class);
                intent.putExtra(ChattingActivity.EXTRA_USER, user);
                startActivity(intent);
                getActivity().finish();*/
            }
        });

        mAdapter.setOnAdapterCancelBtnClickListener(new ReservedOneAdapter.OnAdapterCancelBtnClickListener() {
            @Override
            public void onAdapterCancelBtnClick(View view, Estimate estimate, int position) {
                estimateId = estimate.getId();
                ((ReservationMgmFragment) getParentFragment()).startCancelReservationActivity(estimateId);
            }
        });

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    void init(){
        mAdapter.clear();
        EstimateListRequest estimateListRequest = new EstimateListRequest(getContext(), TAB_RESERVED_ONE);
        NetworkManager.getInstance().getNetworkData(estimateListRequest, new NetworkManager.OnResultListener<NetworkResult<List<Estimate>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<Estimate>>> request, NetworkResult<List<Estimate>> result) {

                for(Estimate e : result.getResult()){
                    Estimate estimate = new Estimate();
                    estimate.setSingerId(e.getSingerId());
                    estimate.setSingerImage(e.getSingerImage());
                    estimate.setSingerName(e.getSingerName());
                    estimate.setCustomerId(e.getCustomerId());
                    estimate.setDate(e.getDate());
                    estimate.setLocation(e.getLocation());
                    estimate.setSongs(e.getSongs());
                    estimate.setSpecial(e.getSpecial());
                    estimate.setId(e.getId());

                    mAdapter.add(estimate);

                }

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<Estimate>>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }

}
