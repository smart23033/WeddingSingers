package com.weddingsingers.wsapp.login;


import android.content.Intent;
import android.os.Bundle;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.User;
import com.weddingsingers.wsapp.main.MainActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.manager.PropertyManager;
import com.weddingsingers.wsapp.request.LoginRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @BindView(R.id.login_et_email)
    EditText emailInput;

    @BindView(R.id.login_et_password)
    EditText passwordInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this,view);

        return view;
    }

    @OnClick(R.id.login_btn_login)
    void onLoginBtnClick(){

        final String email = emailInput.getText().toString();
        final String password = passwordInput.getText().toString();

        //잠깐 temp로 넣어놓음 1이면 싱어로그인, 2면 고객로그인
        int type = 1;

        LoginRequest request = new LoginRequest(getContext(),email,password,type);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<User>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {
                Toast.makeText(getContext(),result.getResult().getEmail(),Toast.LENGTH_SHORT).show();
                PropertyManager.getInstance().setEmail(email);
                PropertyManager.getInstance().setPassword(password);
                int id = result.getResult().getId();
                int type = result.getResult().getType();
                String email = result.getResult().getEmail();
                String name = result.getResult().getName();
                moveMainActivity(id,type,name,email);

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(),errorMessage,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void moveMainActivity(int id,int type,String name, String email){
        Intent intent = new Intent(getActivity(),MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_USER_ID, id);
        intent.putExtra(MainActivity.EXTRA_USER_TYPE, type);
        intent.putExtra(MainActivity.EXTRA_USER_NAME,name);
        intent.putExtra(MainActivity.EXTRA_USER_EMAIL,email);
        intent.putExtra(MainActivity.FRAG_NAME, MainActivity.FRAG_MAIN);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();
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
