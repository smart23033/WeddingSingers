package com.weddingsingers.wsapp.function.mypage.pointstore;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PointStoreFragment extends Fragment {


    public PointStoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_point_store, container, false);

        return view;
    }

}
