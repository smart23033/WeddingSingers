package com.weddingsingers.wsapp.function.search.search;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.weddingsingers.wsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailSearchFragment extends Fragment {
    private static DetailSearchFragment instance;

    public DetailSearchFragment() {
        // Required empty public constructor
    }

    public static DetailSearchFragment getInstance(){
        if(instance == null){
            instance = new DetailSearchFragment();
        }
        return instance;
    }

    @BindView(R.id.detail_search_et_keyword)
    EditText keywordView;

    @BindView(R.id.detail_search_ib_filter)
    ImageButton imageBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_search, container, false);

        ButterKnife.bind(this,view);

        FragmentTransaction ft = getChildFragmentManager()
                .beginTransaction();
        RecentSearchFragment recentSearchFragment = RecentSearchFragment.getInstance();
        ft.add(R.id.detail_search_fl_container,recentSearchFragment);
        ft.commit();

        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilterFragment filterFragment = new FilterFragment();
                FragmentTransaction ft = getChildFragmentManager()
                        .beginTransaction();
                ft.replace(R.id.detail_search_fl_container,filterFragment);
                ft.commit();
            }
        });

        return view;
    }

}
