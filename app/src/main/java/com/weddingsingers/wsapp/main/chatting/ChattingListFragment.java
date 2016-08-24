package com.weddingsingers.wsapp.main.chatting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChattingListFragment extends Fragment {


    public ChattingListFragment() {
        // Required empty public constructor
    }

    private static ChattingListFragment instance;

    public static ChattingListFragment getInstance(){
        if(instance == null){
            instance = new ChattingListFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chatting_list, container, false);
    }

}
