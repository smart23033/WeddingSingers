package com.weddingsingers.wsapp.login;


import android.content.Intent;
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
import android.widget.Button;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginIntroFragment extends Fragment {

    public LoginIntroFragment() {
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
        View view = inflater.inflate(R.layout.fragment_login_intro, container, false);

        ButterKnife.bind(this,view);

        return view;
    }

    @OnClick(R.id.login_intro_btn_login)
    void onLoginBtnClick(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager()
                .beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        ft.add(R.id.act_login_container,loginFragment,LoginActivity.FRAG_LOGIN);
        ft.addToBackStack(null);
        ft.commit();
    }

    User user;

    @OnClick(R.id.login_intro_btn_sign_up)
    void onSignUpBtnClick(){
        user = new User();
        FragmentTransaction ft = getActivity().getSupportFragmentManager()
                .beginTransaction();
        SignUpFirstFragment signUpFirstFragment = SignUpFirstFragment.newInstance(user);
        ft.add(R.id.act_login_container,signUpFirstFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @OnClick(R.id.login_intro_btn_facebook_login)
    void onFacebookBtnClick(){
        Intent intent = new Intent(getActivity(),FacebookLoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
