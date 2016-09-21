package com.weddingsingers.wsapp.main.alarm;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Alarm;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.AlarmListRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmFragment extends Fragment {

    public static final String KEY_USER_TYPE = "userType";
    public static final int TYPE_SINGER = 1;
    public static final int TYPE_CUSTOMER = 2;

    private static final String TYPE_RESERVE = "10";
    private static final String TYPE_REJECT = "11";
    private static final String TYPE_ACCEPT = "20";
    private static final String TYPE_CANCEL = "21";
    private static final String TYPE_PAY = "30";
    private static final String TYPE_CANCEL_SCHEDULE = "31";
    private static final String TYPE_FAVORITE = "50";
    private static final String TYPE_REVIEW = "60";


    AlarmListAdapter mAdapter;


    @BindView(R.id.alarm_rv_list)
    RecyclerView recyclerView;

    public AlarmFragment() {
        // Required empty public constructor
    }

    public static AlarmFragment newInstance(int userType) {
        AlarmFragment fragment = new AlarmFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_USER_TYPE, userType);
        fragment.setArguments(args);
        return fragment;
    }

    int userType;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            userType = getArguments().getInt(KEY_USER_TYPE);
        }
        mAdapter = new AlarmListAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);

        ButterKnife.bind(this, view);

        recyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        mAdapter.setOnAdapterItemClickListener(new AlarmListAdapter.OnAdapterItemClickListener() {
                                                   @Override
                                                   public void onAdapterItemClick(View view, Alarm alarm, int position) {
//                타입에 따라 인텐트를 다르게 이동
                                                       String type = String.valueOf(alarm.getType());
                                                       switch (type) {
                                                           case TYPE_RESERVE: {

                                                               break;
                                                           }
                                                           case TYPE_REJECT: {

                                                               break;
                                                           }
                                                           case TYPE_ACCEPT: {

                                                               break;
                                                           }
                                                           case TYPE_CANCEL: {

                                                               break;
                                                           }
                                                           case TYPE_PAY: {

                                                               break;
                                                           }
                                                           case TYPE_CANCEL_SCHEDULE: {

                                                               break;
                                                           }
                                                           case TYPE_FAVORITE: {

                                                               break;
                                                           }
                                                           case TYPE_REVIEW: {

                                                               break;
                                                           }
                                                       }
                                                   }

                                               }
        );

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        mAdapter.clear();

        AlarmListRequest alarmListRequest = new AlarmListRequest(getContext());
        NetworkManager.getInstance().getNetworkData(alarmListRequest, new NetworkManager.OnResultListener<NetworkResult<List<Alarm>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<Alarm>>> request, NetworkResult<List<Alarm>> result) {

                for (Alarm a : result.getResult()) {
                    Alarm alarm = new Alarm();

                    if (userType == TYPE_SINGER) {
                        alarm.setUserImage(a.getCustomerImage());
                        alarm.setUserName(a.getCustomerName());
                    } else if (userType == TYPE_CUSTOMER) {
                        alarm.setUserImage(a.getSingerImage());
                        alarm.setUserName(a.getSingerName());
                    }

                    alarm.setDataId(a.getDataId());
                    alarm.setMessage(a.getMessage());
                    alarm.setType(a.getType());

                    mAdapter.add(alarm);

                    Log.i("AlarmFragment", "alarm.getUserImage : " + alarm.getUserImage());
                    Log.i("AlarmFragment", "alarm.getUserName : " + alarm.getUserName());
                }

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<Alarm>>> request, int errorCode, String errorMessage, Throwable e) {
                Log.i("AlarmFragment", "alarmRequest fail : " + errorMessage);
            }
        });
    }

}
