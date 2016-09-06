package com.weddingsingers.wsapp.function.search.search;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Search;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment {

    final static int INITIAL_POSITION = 0;

    @BindView(R.id.filter_spinner_location)
    Spinner locationSpinner;

    @BindView(R.id.filter_spinner_composition)
    Spinner compositionSpinner;

    @BindView(R.id.filter_spinner_theme)
    Spinner themeSpinner;

    @BindView(R.id.filter_spinner_price)
    Spinner priceSpinner;

    @BindView(R.id.filter_tv_start_date)
    TextView startDateView;

    @BindView(R.id.filter_tv_end_date)
    TextView endDateView;

    FilterSpinnerAdapter locationAdapter;
    FilterSpinnerAdapter compositionAdapter;
    FilterSpinnerAdapter themeAdapter;
    FilterSpinnerAdapter priceAdapter;

    public static FilterFragment newInstance(Search search) {
        FilterFragment fragment = new FilterFragment();
        Bundle b = new Bundle();
        b.putSerializable(SearchActivity.KEY_SEARCH, search);
        fragment.setArguments(b);
        return fragment;
    }

    public Search search;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            search = (Search) getArguments().getSerializable(SearchActivity.KEY_SEARCH);
        }
    }

    public FilterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        ButterKnife.bind(this, view);

        locationAdapter = new FilterSpinnerAdapter();
        compositionAdapter = new FilterSpinnerAdapter();
        themeAdapter = new FilterSpinnerAdapter();
        priceAdapter = new FilterSpinnerAdapter();

        locationSpinner.setAdapter(locationAdapter);
        compositionSpinner.setAdapter(compositionAdapter);
        priceSpinner.setAdapter(priceAdapter);
        themeSpinner.setAdapter(themeAdapter);

        initData();

        return view;
    }

    @OnItemSelected(R.id.filter_spinner_composition)
    void onCompositionSelected(int position) {
        search.setComposition(position);
    }

    @OnItemSelected(R.id.filter_spinner_location)
    void onLocationSelected(int position) {
        search.setLocation(position);
    }

    @OnItemSelected(R.id.filter_spinner_price)
    void onPriceSelected(int position) {
        search.setPrice(position);
    }

    @OnItemSelected(R.id.filter_spinner_theme)
    void onThemeSelected(int position) {
        search.setTheme(position);
    }

    @OnClick(R.id.filter_btn_calendar)
    void onCalendarClick() {
        CalendarDialogFragment calendarDialogFragment = CalendarDialogFragment.newInstance(CalendarDialogFragment.FRAG_FILTER);
        calendarDialogFragment.show(getActivity().getSupportFragmentManager(), "calendarDialog");


        calendarDialogFragment.setOnCalendarRangeSelected(new CalendarDialogFragment.OnCalendarRangeSelectedListener() {
            @Override
            public void onCalendarRangeSelected(@NonNull MaterialCalendarView widget, @NonNull List<CalendarDay> dates) {

                int startYear = dates.get(0).getYear();
                int startMonth = dates.get(0).getMonth() + 1;
                int startDay = dates.get(0).getDay();
                String startDate = String.format("%d-%d-%d",startYear,startMonth,startDay);

                int endYear = dates.get(dates.size() - 1).getYear();
                int endMonth = dates.get(dates.size() - 1).getMonth() + 1;
                int endDay = dates.get(dates.size() - 1).getDay();
                String endDate = String.format("%d-%d-%d",endYear,endMonth,endDay);

                startDateView.setText(startDate);
                endDateView.setText(endDate);

                search.setStartDate(startDate);
                search.setEndDate(endDate);

            }
        });
    }

    private void initData() {
        String[] items = getResources().getStringArray(R.array.location);
        locationAdapter.addAll(items);
        items = getResources().getStringArray(R.array.composition);
        compositionAdapter.addAll(items);
        items = getResources().getStringArray(R.array.theme);
        themeAdapter.addAll(items);
        items = getResources().getStringArray(R.array.price);
        priceAdapter.addAll(items);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search_menu_filter) {
            item.setIcon(R.drawable.search_ic_filter_off);
            FragmentTransaction ft = getActivity().getSupportFragmentManager()
                    .beginTransaction();
            RecentSearchFragment recentSearchFragment = new RecentSearchFragment();
            ft.replace(R.id.act_search_fl_container, recentSearchFragment, SearchActivity.FRAG_RECENT_SEARCH);
            ft.commit();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem filterMenuItem = menu.findItem(R.id.search_menu_filter);
        filterMenuItem.setIcon(R.drawable.search_ic_filter_on);
    }

}
