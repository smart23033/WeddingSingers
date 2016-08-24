package com.weddingsingers.wsapp.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.weddingsingers.wsapp.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search, container, false);

        RecentSearchFragment f = RecentSearchFragment.getInstance();
        int fm = getChildFragmentManager().beginTransaction()
                .add(R.id.search_fl_container,f)
                .commit();
        return view;
    }

    @OnClick(R.id.search_ib_filter)
    public void onImageBtnClick(){

    }
}
