package com.weddingsingers.wsapp.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
public class DeprecatedSignUpFragment extends Fragment {


    public DeprecatedSignUpFragment() {
        // Required empty public constructor
    }
//
//    @BindView(R.id.sign_up_et_email)
//    EditText emailInput;
//
//    @BindView(R.id.sign_up_et_password)
//    EditText passwordInput;
//
//    @BindView(R.id.sign_up_edit_password_confirm)
//    EditText passwordConfirmInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_deprecated__sign_up, container, false);
        ButterKnife.bind(this,view);

        return view;
    }

//    @OnClick(R.id.sign_up_tv_clause)
//    public void onClickClause(){
//        ClauseDialogFragment f = new ClauseDialogFragment();
//        FragmentManager fm = getChildFragmentManager();
//        f.show(fm,"dialog");
//    }

}
