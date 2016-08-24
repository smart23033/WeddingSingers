package com.weddingsingers.wsapp.main.qna;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QNAFragment extends Fragment {


    public QNAFragment() {
        // Required empty public constructor
    }

    private static QNAFragment instance;

    public static QNAFragment getInstance(){
        if(instance == null){
            instance = new QNAFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qna, container, false);
    }

}
