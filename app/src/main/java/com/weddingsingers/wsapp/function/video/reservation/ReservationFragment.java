package com.weddingsingers.wsapp.function.video.reservation;


import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.Singer;
import com.weddingsingers.wsapp.data.view.ProfileView;
import com.weddingsingers.wsapp.function.search.search.CalendarDialogFragment;
import com.weddingsingers.wsapp.main.MainActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.ReservationRequest;
import com.weddingsingers.wsapp.request.SingerProfileRequest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationFragment extends Fragment {

    final static int FRAG_RESERVATION_MGM = 300;
    final static int ARG_SIMPLE = 1;
    final static int TYPE_STANDARD = 1;
    final static int TYPE_SPECIAL = 2;

    public ReservationFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.reservation_et_special)
    EditText specialInput;

    @BindView(R.id.reservation_spinner_standard)
    Spinner standardSpinner;

    @BindView(R.id.reservation_spinner_location)
    Spinner locationSpinner;

    @BindView(R.id.reservation_tv_type_selected)
    TextView typeView;

    @BindView(R.id.reservation_rb_standard)
    RadioButton standardRadioBtn;

    @BindView(R.id.reservation_tv_date)
    TextView dateView;

    @BindView(R.id.reservation_tv_time)
    TextView timeView;

    @BindView(R.id.reservation_pv_profile)
    ProfileView singerProfileView;


    ReservationSpinnerAdapter priceSpinnerAdapter;
    ReservationSpinnerAdapter locationSpinnerAdapter;

    private static final String KEY_SINGER_ID = "singerId";
    private static ReservationFragment instance;

    int singerId;

    public static ReservationFragment newInstance(int singerId) {
        ReservationFragment fragment = new ReservationFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_SINGER_ID, singerId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservation, container, false);

        ButterKnife.bind(this, view);

        priceSpinnerAdapter = new ReservationSpinnerAdapter();
        locationSpinnerAdapter = new ReservationSpinnerAdapter();

        standardRadioBtn.setChecked(true);

        standardSpinner.setAdapter(priceSpinnerAdapter);
        locationSpinner.setAdapter(locationSpinnerAdapter);

        initSingerProfile();
        initData();

        return view;
    }


    private void initSingerProfile(){
        SingerProfileRequest singerProfileRequest = new SingerProfileRequest(getContext(),singerId,ARG_SIMPLE);
        NetworkManager.getInstance().getNetworkData(singerProfileRequest, new NetworkManager.OnResultListener<NetworkResult<Singer>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Singer>> request, NetworkResult<Singer> result) {
                String singerName = result.getResult().getSingerName();
                String singerImage = result.getResult().getSingerImage();
                String comment = result.getResult().getComment();

                singerProfileView.setSingerId(singerId);
                singerProfileView.setComment(comment);
                singerProfileView.setSingerName(singerName);
                singerProfileView.setSingerImage(singerImage);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Singer>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }


    @OnClick(R.id.reservation_tv_date)
    void onDateClick() {
        CalendarDialogFragment calendarDialogFragment = CalendarDialogFragment.newInstance(CalendarDialogFragment.FRAG_RESERVATION);
        calendarDialogFragment.show(getActivity().getSupportFragmentManager(), "calendarDialog");

        calendarDialogFragment.setOnCalendarDateChanged(new CalendarDialogFragment.OnCalendarDateChangedListener() {
            @Override
            public void onCalendarDateChanged(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int year = date.getYear();
                int month = date.getMonth() + 1;
                int day = date.getDay();
                String reservationDate = String.format("%d-%d-%d", year, month, day);
                dateView.setText(reservationDate);
            }
        });
    }


    @OnClick(R.id.reservation_tv_time)
    void onTimeClick() {

        GregorianCalendar calendar = new GregorianCalendar();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                String reservationTime = String.format("%d:%d", hourOfDay, minute);
                timeView.setText(reservationTime);
            }
        }, hour, minute, false);

        timePickerDialog.show();
    }

    String location;
    @OnItemSelected(R.id.reservation_spinner_location)
    void onLocationSelected(int position){
        location = (String) locationSpinner.getItemAtPosition(position);
    }

    String song;
    @OnItemSelected(R.id.reservation_spinner_standard)
    void onSongSelected(int position){
        song = (String) standardSpinner.getItemAtPosition(position);
    }

    int type = TYPE_STANDARD;
    @OnClick(R.id.reservation_rb_standard)
    void onStandardClick(){
        type = TYPE_STANDARD;
    }

    @OnClick(R.id.reservation_rb_special)
    void onSpecialClick(){
        type = TYPE_SPECIAL;
    }


    @OnClick(R.id.reservation_btn_reserve)
    void onReserveBtnClick() {
        String special = (specialInput.getText().toString() != null) ?  specialInput.getText().toString() : "none" ;

        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String writeDate = dayTime.format(new Date(time));

        String reservationDate = dateView.getText().toString();
        String reservationTime = timeView.getText().toString();

        if(type == TYPE_STANDARD){
            special = "";
        }else if(type == TYPE_SPECIAL){
            song = "";
        }

        ReservationRequest reservationRequest = new ReservationRequest(getContext(),singerId,location,special,reservationDate,reservationTime,writeDate,type,song);
        NetworkManager.getInstance().getNetworkData(reservationRequest, new NetworkManager.OnResultListener<NetworkResult<String>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {
                Toast.makeText(getContext(),"Reservation success",Toast.LENGTH_SHORT).show();

                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Reservation Complete")
                        .setMessage("Reservation complete,\ncould you go to confirm?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveReservationMgmFragment();
                            }
                        });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moveMainActivity();
                    }
                });
                dialog = builder.create();
                dialog.show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(),"Reservation fail",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @OnClick({R.id.reservation_rb_standard, R.id.reservation_rb_special})
    void onRadioBtnClick(RadioButton radioButton) {
        boolean checked = radioButton.isChecked();

        switch (radioButton.getId()) {
            case R.id.reservation_rb_standard: {
                if (checked) {
                    specialInput.setVisibility(View.GONE);
                    standardSpinner.setVisibility(View.VISIBLE);
                    typeView.setText("STANDARD");
                    break;
                }
            }
            case R.id.reservation_rb_special: {
                if (checked) {
                    specialInput.setVisibility(View.VISIBLE);
                    standardSpinner.setVisibility(View.GONE);
                    typeView.setText("SPECIAL");
                    break;
                }
            }
        }
    }

    private void initData() {
        priceSpinnerAdapter.clear();
        locationSpinnerAdapter.clear();

        String[] items = getResources().getStringArray(R.array.price);
        priceSpinnerAdapter.addAll(items);

        items = getResources().getStringArray(R.array.location);
        locationSpinnerAdapter.addAll(items);
    }

    private void moveReservationMgmFragment() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra(MainActivity.FRAG_NAME, FRAG_RESERVATION_MGM);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();
    }

    private void moveMainActivity() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            singerId = getArguments().getInt(KEY_SINGER_ID);
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
