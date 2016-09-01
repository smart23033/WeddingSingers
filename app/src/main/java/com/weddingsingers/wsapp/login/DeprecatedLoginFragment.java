package com.weddingsingers.wsapp.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.main.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeprecatedLoginFragment extends Fragment {

    public DeprecatedLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deprecated_login, container, false);
        ButterKnife.bind(this,view);

        return view;
    }

    @OnClick(R.id.login_btn_login)
    void onLoginBtnClicked(){
        moveMainActivity();
    }

    void moveMainActivity(){
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtra("login", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();
    }
}