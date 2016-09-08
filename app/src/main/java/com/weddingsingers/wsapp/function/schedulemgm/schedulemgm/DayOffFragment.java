package com.weddingsingers.wsapp.function.schedulemgm.schedulemgm;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.DateFormatDayFormatter;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.Schedule;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.DayOffRequest;
import com.weddingsingers.wsapp.request.ReservationDateRequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DayOffFragment extends Fragment implements
        OnDateSelectedListener {

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    @BindView(R.id.day_off_cv_calendar)
    MaterialCalendarView calendarView;

    public DayOffFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_day_off, container, false);

        ButterKnife.bind(this,view);

//        미완

        calendarView.setOnDateChangedListener(this);
        calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);

//        initReservationDate();

        initDayOff();

        return view;
    }

    void initDayOff(){
        DayOffRequest dayOffRequest = new DayOffRequest(getContext(),1);
        NetworkManager.getInstance().getNetworkData(dayOffRequest, new NetworkManager.OnResultListener<NetworkResult<Schedule>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Schedule>> request, NetworkResult<Schedule> result) {
                for(String date : result.getResult().getDayOff()) {
                    try {

                        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date to = transFormat.parse(date);
                        calendarView.setDateSelected(to,true);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Schedule>> request, int errorCode, String errorMessage, Throwable e) {
                Log.i("DayOffFragment", "request fail : " + errorMessage);
            }
        });
    }

    void initReservationDate(){
        ReservationDateRequest reservationDateRequest = new ReservationDateRequest(getContext());
        NetworkManager.getInstance().getNetworkData(reservationDateRequest, new NetworkManager.OnResultListener<NetworkResult<Schedule>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Schedule>> request, NetworkResult<Schedule> result) {
                Log.i("DayOffFragment","code : " + result.getCode());
                Log.i("DayOffFragment","reservationDate : " + result.getResult().getReservationDate());
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Schedule>> request, int errorCode, String errorMessage, Throwable e) {
                Log.i("DayOffFragment","errorMessage : " + errorMessage);
            }
        });
    }


    ArrayList<CalendarDay> dates = new ArrayList<>();

    @OnClick(R.id.day_off_btn_set)
    void onSetBtnClick(){
        for(CalendarDay d : dates) {
            Log.i("DayOffFragment", "dates : " + FORMATTER.format(d.getDate()));
            calendarView.setDateSelected(d,true);
        }
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        Toast.makeText(getContext(),"date : " + date,Toast.LENGTH_SHORT).show();
       if(dates.contains(date)){
          dates.remove(date);
       }else{
           dates.add(date);
       }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                getActivity().finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

}
