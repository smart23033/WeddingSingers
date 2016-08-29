package com.weddingsingers.wsapp.main.reservationmgm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationListFragment extends Fragment {

    private static final String ARG_MESSAGE = "param1";
    private String message;
    private static ReservationListFragment instance;

    public ReservationListFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.reservation_list_rv_list)
    RecyclerView recyclerView;

    public static ReservationListFragment newInstance(String message) {
        ReservationListFragment fragment = new ReservationListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservation_list, container, false);

        ButterKnife.bind(this,view);



        return view;
    }

}
