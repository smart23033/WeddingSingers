package com.weddingsingers.wsapp.function.search.search;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment {

    public FilterFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.filter_spinner_location)
    Spinner locationSpinner;

    @BindView(R.id.filter_spinner_composition)
    Spinner compositionSpinner;

    @BindView(R.id.filter_spinner_theme)
    Spinner themeSpinner;

    @BindView(R.id.filter_spinner_price)
    Spinner priceSpinner;

    FilterSpinnerAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_filter, container, false);
        ButterKnife.bind(this,view);

        mAdapter = new FilterSpinnerAdapter();
        locationSpinner.setAdapter(mAdapter);
        compositionSpinner.setAdapter(mAdapter);
        priceSpinner.setAdapter(mAdapter);
        themeSpinner.setAdapter(mAdapter);


        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getContext(),"item : " + mAdapter.getItem(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        initData();

        return view;
    }

    @OnItemSelected(R.id.filter_spinner_composition)
    void onItemSelected(int position){
        Toast.makeText(getContext(),"item : " + mAdapter.getItem(position),Toast.LENGTH_SHORT).show();
    }

    private void initData(){
        String[] items = getResources().getStringArray(R.array.items);
        mAdapter.addAll(items);
    }


}
