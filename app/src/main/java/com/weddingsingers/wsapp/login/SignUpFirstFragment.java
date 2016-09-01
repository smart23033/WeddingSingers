package com.weddingsingers.wsapp.login;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFirstFragment extends Fragment {


    public SignUpFirstFragment() {
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
        View view = inflater.inflate(R.layout.fragment_sign_up_first, container, false);

        ButterKnife.bind(this,view);

        return view;
    }

    @OnClick(R.id.sign_up_first_btn_customer)
    void onCustomerBtnClick(){
        moveSignUpSecond();
    }

    @OnClick(R.id.sign_up_first_btn_singer)
    void onSingerBtnClick(){
        moveSignUpSecond();
    }

    private void moveSignUpSecond(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager()
                .beginTransaction();
        SignUpSecondFragment signUpSecondFragment = new SignUpSecondFragment();
        ft.add(R.id.act_login_container,signUpSecondFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

}
