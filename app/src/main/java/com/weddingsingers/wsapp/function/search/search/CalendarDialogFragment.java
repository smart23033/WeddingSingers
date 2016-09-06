package com.weddingsingers.wsapp.function.search.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener;
import com.weddingsingers.wsapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-09-05.
 */
public class CalendarDialogFragment extends DialogFragment implements
        OnRangeSelectedListener,
        OnDateSelectedListener{

    final public static String FRAG_FILTER = "FilterFragment";
    final public static String FRAG_RESERVATION = "ReservationFragment";
    final static String KEY_FRAGMENT = "fragmentName";


    public static CalendarDialogFragment newInstance(String tag) {
        CalendarDialogFragment fragment = new CalendarDialogFragment();
        Bundle b = new Bundle();
        b.putString(KEY_FRAGMENT, tag);
        fragment.setArguments(b);
        return fragment;
    }

    public CalendarDialogFragment() {
    }

    @BindView(R.id.filter_cv_calendar)
    MaterialCalendarView calendarView;

    String tag;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog);
        if (getArguments() != null) {
            tag = getArguments().getString(KEY_FRAGMENT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_filter_calendar, container, false);

        ButterKnife.bind(this, view);

//        calendarView = new MaterialCalendarView(getContext());

//        태그에 따른 분기
          Log.i("CalendarDialogFragment","tag : " + tag);


        if(tag.equals(FRAG_FILTER)) {
            calendarView.setOnRangeSelectedListener(this);
            calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_RANGE);
        }else if(tag.equals(FRAG_RESERVATION)){
            calendarView.setOnDateChangedListener(this);
            calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_SINGLE);
        }

        return view;
    }

    @Override
    public void onRangeSelected(@NonNull MaterialCalendarView widget, @NonNull List<CalendarDay> dates) {
        if(rangeSelectedListener != null){
            rangeSelectedListener.onCalendarRangeSelected(widget,dates);
        }
        dismiss();
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        if(dateChangedListener != null){
            dateChangedListener.onCalendarDateChanged(widget,date,selected);
        }
        dismiss();
    }


    public interface OnCalendarRangeSelectedListener {
        public void onCalendarRangeSelected(@NonNull MaterialCalendarView widget, @NonNull List<CalendarDay> dates);
    }

    OnCalendarRangeSelectedListener rangeSelectedListener;
    public void setOnCalendarRangeSelected(OnCalendarRangeSelectedListener rangeSelectedListener){
        this.rangeSelectedListener = rangeSelectedListener;
    }

    public interface OnCalendarDateChangedListener{
        public void onCalendarDateChanged(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected);
    }

    OnCalendarDateChangedListener dateChangedListener;
    public void setOnCalendarDateChanged(OnCalendarDateChangedListener dateChangedListener){
        this.dateChangedListener = dateChangedListener;
    }



}

