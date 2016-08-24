package com.weddingsingers.wsapp.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentSearchFragment extends Fragment {
    private static RecentSearchFragment instance;

    public RecentSearchFragment() {
        // Required empty public constructor
    }

    public static RecentSearchFragment getInstance(){
        if(instance == null){
            instance = new RecentSearchFragment();
        }
        return instance;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent_search, container, false);
    }

}
