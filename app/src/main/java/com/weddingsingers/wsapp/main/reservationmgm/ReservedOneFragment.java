package com.weddingsingers.wsapp.main.reservationmgm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReservedOneFragment extends Fragment {

    private static final String ARG_MESSAGE = "param1";
    private String message;
    private static ReservedOneFragment instance;

    public ReservedOneFragment() {
        // Required empty public constructor
    }


    public static ReservedOneFragment newInstance(String message) {
        ReservedOneFragment fragment = new ReservedOneFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reserved_one, container, false);
    }

}
