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
import com.weddingsingers.wsapp.request.DayOffSettingRequest;
import com.weddingsingers.wsapp.request.ReservationDateRequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DayOffFragment extends Fragment implements
        OnDateSelectedListener {


    public static final String KEY_SINGER_ID = "singerId";
    public static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    @BindView(R.id.day_off_cv_calendar)
    MaterialCalendarView calendarView;

    public static DayOffFragment newInstance(int singerId) {
        DayOffFragment fragment = new DayOffFragment();
        Bundle b = new Bundle();
        b.putInt(KEY_SINGER_ID, singerId);
        fragment.setArguments(b);
        return fragment;
    }

    public DayOffFragment() {
        // Required empty public constructor
    }

    int singerId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            singerId = getArguments().getInt(KEY_SINGER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_day_off, container, false);

        ButterKnife.bind(this, view);

//        미완

        calendarView.setOnDateChangedListener(this);
        calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);

//        initReservationDate();

        initDayOff();
        initReservationDate();

        return view;
    }

    void initDayOff() {
        DayOffRequest dayOffRequest = new DayOffRequest(getContext(), singerId);
        NetworkManager.getInstance().getNetworkData(dayOffRequest, new NetworkManager.OnResultListener<NetworkResult<Schedule>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Schedule>> request, NetworkResult<Schedule> result) {
                for (String date : result.getResult().getDayOff()) {
                    try {

                        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date to = transFormat.parse(date);
                        calendarView.setDateSelected(to, true);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Schedule>> request, int errorCode, String errorMessage, Throwable e) {
                Log.i("DayOffFragment", "DayOffRequest errorMessage : " + errorMessage);
            }
        });
    }

    private class DisableDecorator implements DayViewDecorator {
        CalendarDay calendarDay;

        public DisableDecorator(Date date) {
            this.calendarDay = CalendarDay.from(date);

            Log.i("DisableDecorator","calendarDay : " + calendarDay);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            if (calendarDay.equals(day)) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setDaysDisabled(true);
        }
    }

    void initReservationDate() {
        ReservationDateRequest reservationDateRequest = new ReservationDateRequest(getContext(), singerId);
        NetworkManager.getInstance().getNetworkData(reservationDateRequest, new NetworkManager.OnResultListener<NetworkResult<Schedule>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Schedule>> request, NetworkResult<Schedule> result) {
                for (String date : result.getResult().getReservationDate()) {
                    try {

                        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date to = transFormat.parse(date);
                        calendarView.addDecorator(new DisableDecorator(to));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Schedule>> request, int errorCode, String errorMessage, Throwable e) {
                Log.i("DayOffFragment", "ReservationRequest errorMessage : " + errorMessage);
            }
        });
    }


    ArrayList<CalendarDay> calendarDays = new ArrayList<>();
    ArrayList<String> stringDates = new ArrayList<>();

    @OnClick(R.id.day_off_btn_set)
    void onSetBtnClick() {
        for (CalendarDay day : calendarDays) {
            calendarView.setDateSelected(day, true);

//            DateFormatDayFormatter dayFormatter = new DateFormatDayFormatter();
            DayFormatter dayFormatter = new DayFormatter() {
                @NonNull
                @Override
                public String format(@NonNull CalendarDay day) {
                    return  String.format("%d-%d-%d",day.getYear(),day.getMonth()+1,day.getDay());

                }
            };
            String date = dayFormatter.format(day);
            Log.i("DayOffFragment","date : " + date);
            Toast.makeText(getContext(),"date : " + date,Toast.LENGTH_SHORT).show();
            stringDates.add(date);
        }

            DayOffSettingRequest dayOffSettingRequest = new DayOffSettingRequest(getContext(),stringDates);
            NetworkManager.getInstance().getNetworkData(dayOffSettingRequest, new NetworkManager.OnResultListener<NetworkResult<String>>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {
                    getActivity().finish();
                }

                @Override
                public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e) {
                    Toast.makeText(getContext(),"DayOffSettingRequest Fail : " + errorMessage,Toast.LENGTH_SHORT).show();
                }
            });

    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        if (calendarDays.contains(date)) {
            calendarDays.remove(date);
        } else {
            calendarDays.add(date);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
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
