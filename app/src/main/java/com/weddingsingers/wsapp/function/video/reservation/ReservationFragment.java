package com.weddingsingers.wsapp.function.video.reservation;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationFragment extends Fragment {

    final static String FRAG_RESERVATION_MGM = "ReservationMgmFragment";

    public ReservationFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.view_profile_btn_reserve)
    Button reserveBtn;

    @BindView(R.id.reservation_et_special)
    EditText specialInput;

//    dateSpinner와 timeSpinner는 나중에 datePicker와 timePicker로 만들 것
    @BindView(R.id.reservation_spinner_date)
    Spinner dateSpinner;

    @BindView(R.id.reservation_spinner_time)
    Spinner timeSpinner;

    @BindView(R.id.reservation_spinner_standard)
    Spinner standardSpinner;

    @BindView(R.id.reservation_tv_type_selected)
    TextView typeView;

    @BindView(R.id.reservation_rb_standard)
    RadioButton standardRadioBtn;

    PriceFilterSpinnerAdapter mAdapter;

    private static final String ARG_MESSAGE = "param1";
    private String message;
    private static ReservationFragment instance;

    public static ReservationFragment newInstance(String message) {
        ReservationFragment fragment = new ReservationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
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
        View view =  inflater.inflate(R.layout.fragment_reservation, container, false);

        ButterKnife.bind(this,view);

        mAdapter = new PriceFilterSpinnerAdapter();

        reserveBtn.setVisibility(View.GONE);
        standardRadioBtn.setChecked(true);

        standardSpinner.setAdapter(mAdapter);
        dateSpinner.setAdapter(mAdapter);
        timeSpinner.setAdapter(mAdapter);


        initData();

        return view;
    }

    @OnClick(R.id.reservation_btn_reserve)
    void onReserveBtnClicked(){
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Reservation Complete")
                .setMessage("Reservation complete,\ncould you go to confirm?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moveReservationMgmFragment();                    }
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

    @OnClick({R.id.reservation_rb_standard, R.id.reservation_rb_special})
    void onRadioBtnClicked(RadioButton radioButton){
        boolean checked = radioButton.isChecked();

        switch (radioButton.getId()){
            case R.id.reservation_rb_standard:{
                if(checked) {
                    specialInput.setVisibility(View.GONE);
                    standardSpinner.setVisibility(View.VISIBLE);
                    typeView.setText("STANDARD");
                    break;
                }
            }
            case R.id.reservation_rb_special:{
                if(checked) {
                    specialInput.setVisibility(View.VISIBLE);
                    standardSpinner.setVisibility(View.GONE);
                    typeView.setText("SPECIAL");
                    break;
                }
            }
        }
    }

    @OnItemSelected(R.id.reservation_spinner_standard)
    void onItemSelected(int position){
        Toast.makeText(getContext(),"item : " + mAdapter.getItem(position),Toast.LENGTH_SHORT).show();
    }

    private void initData(){
        String[] items = getResources().getStringArray(R.array.items);
        mAdapter.addAll(items);
    }

    private void moveReservationMgmFragment(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("fragmentName", FRAG_RESERVATION_MGM);
        startActivity(intent);
        getActivity().finish();
    }

    private void moveMainActivity(){
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

}
