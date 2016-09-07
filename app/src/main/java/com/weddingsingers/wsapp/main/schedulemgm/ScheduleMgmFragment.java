package com.weddingsingers.wsapp.main.schedulemgm;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.CalendarList;
import com.weddingsingers.wsapp.function.schedulemgm.schedulemgm.DayOffActivity;
import com.weddingsingers.wsapp.function.schedulemgm.schedulemgm.DetailScheduleActivity;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

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
                int month = calendarList.getMonth();
                int year = calendarList.getYear();
                Intent intent = new Intent(getActivity(), DetailScheduleActivity.class);
                intent.putExtra(DetailScheduleActivity.EXTRA_YEAR, year);
                intent.putExtra(DetailScheduleActivity.EXTRA_MONTH, month);
                startActivity(intent);
            }
        });

        GridLayoutManager manager =
                new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);

        initData();

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.schedule_menu_plus){
            getActivity().startActivity(new Intent(getActivity(), DayOffActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.schedule_menu,menu);
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
