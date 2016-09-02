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
import com.weddingsingers.wsapp.data.User;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFirstFragment extends Fragment {

    final static int TYPE_SINGER = 1;
    final static int TYPE_CUSTOMER = 2;

    public SignUpFirstFragment() {
        // Required empty public constructor
    }

    public static SignUpFirstFragment newInstance(User user){
        SignUpFirstFragment fragment = new SignUpFirstFragment();
        Bundle b = new Bundle();
        b.putSerializable(LoginActivity.KEY_USER_INFO,user);
        fragment.setArguments(b);
        return fragment;
    }

    User user;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle b = getArguments();
        if(b != null){
            user = (User)getArguments().getSerializable(LoginActivity.KEY_USER_INFO);
        }
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
        user.setType(TYPE_CUSTOMER);
        moveSignUpSecond(user);
    }

    @OnClick(R.id.sign_up_first_btn_singer)
    void onSingerBtnClick(){
        user.setType(TYPE_SINGER);
        moveSignUpSecond(user);
    }

    private void moveSignUpSecond(User user){

        FragmentTransaction ft = getActivity().getSupportFragmentManager()
                .beginTransaction();
        SignUpSecondFragment signUpSecondFragment = SignUpSecondFragment.newInstance(user);
        ft.add(R.id.act_login_container,signUpSecondFragment,LoginActivity.FRAG_SIGN_UP_SECOND);
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
