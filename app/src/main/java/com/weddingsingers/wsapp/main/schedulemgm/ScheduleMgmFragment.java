package com.weddingsingers.wsapp.main.schedulemgm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleMgmFragment extends Fragment {


    public ScheduleMgmFragment() {
        // Required empty public constructor
    }

    private static ScheduleMgmFragment instance;

    public static ScheduleMgmFragment getInstance(){
        if(instance == null){
            instance = new ScheduleMgmFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_mgm, container, false);
    }

}
