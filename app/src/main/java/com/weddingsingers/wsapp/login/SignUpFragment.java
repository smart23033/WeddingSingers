package com.weddingsingers.wsapp.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.weddingsingers.wsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    public SignUpFragment() {
        // Required empty public constructor
    }

    private static SignUpFragment instance;

    public static SignUpFragment getInstance(){
        if(instance == null){
            instance = new SignUpFragment();
        }
        return instance;
    }

    @BindView(R.id.sign_up_et_email)
    EditText emailView;

    @BindView(R.id.sign_up_et_password)
    EditText passwordView;

    @BindView(R.id.sign_up_edit_password_confirm)
    EditText passwordConfirmView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this,view);

        return view;
    }

    @OnClick(R.id.sign_up_tv_clause)
    public void onClickClause(){
        ClauseDialogFragment f = new ClauseDialogFragment();
        FragmentManager fm = getChildFragmentManager();
        f.show(fm,"dialog");
    }

}
