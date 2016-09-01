package com.weddingsingers.wsapp.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.weddingsingers.wsapp.R;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginIntroFragment extends Fragment {

    @BindView(R.id.login_intro_btn_login)
    Button loginBtn;

    public LoginIntroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_intro, container, false);
    }

}
