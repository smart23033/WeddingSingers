package com.weddingsingers.wsapp.main.schedulemgm;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.CalendarList;
import com.weddingsingers.wsapp.function.schedulemgm.schedulemgm.DetailScheduleActivity;
import com.weddingsingers.wsapp.main.MainActivity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleMgmFragment extends Fragment {

    @BindView(R.id.schedule_mgm_rv_grid)
    RecyclerView recyclerView;

    CalendarListAdapter mAdapter;

    public ScheduleMgmFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule_mgm, container, false);

        ButterKnife.bind(this,view);

        mAdapter = new CalendarListAdapter();

        mAdapter.setOnAdapterItemClickListener(new CalendarListAdapter.OnAdapterItemClickListener() {
            @Override
            public void onAdapterItemClick(View view, CalendarList calendarList, int position) {
                startActivity(new Intent(getActivity(), DetailScheduleActivity.class));
            }
        });

        GridLayoutManager manager =
                new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);

        initData();

        return view;
    }

    private void initData(){
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        while(true){
            if(month > 12){
                year++;
                month = 1;
            }
            if (year > currentYear + 20) {
                return;
            }

            CalendarList calendarList = new CalendarList();
            calendarList.setMonth(month);
            mAdapter.add(calendarList);
            month++;
        }

    }

}
