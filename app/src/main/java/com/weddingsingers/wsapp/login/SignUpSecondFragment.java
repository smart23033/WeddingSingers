package com.weddingsingers.wsapp.login;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.User;
import com.weddingsingers.wsapp.main.MainActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.manager.PropertyManager;
import com.weddingsingers.wsapp.request.SignUpRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpSecondFragment extends Fragment {

    public SignUpSecondFragment() {
        // Required empty public constructor
    }


    public static SignUpSecondFragment newInstance(User user){
        SignUpSecondFragment fragment = new SignUpSecondFragment();
        Bundle b = new Bundle();
        b.putSerializable(LoginActivity.KEY_USER_INFO,user);
        fragment.setArguments(b);
        return fragment;
    }

    User user;

    @BindView(R.id.sign_up_second_et_email)
    EditText emailInput;

    @BindView(R.id.sign_up_second_et_password)
    EditText passwordInput;

    @BindView(R.id.sign_up_second_et_name)
    EditText nameInput;

    @BindView(R.id.sign_up_second_et_phone)
    EditText phoneInput;

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
        View view = inflater.inflate(R.layout.fragment_sign_up_second, container, false);

        ButterKnife.bind(this,view);

        return view;
    }

    @OnClick(R.id.sign_up_second_btn_sign_up)
    void onSignUpClick(){
        user.setEmail(emailInput.getText().toString());
        user.setPassword(passwordInput.getText().toString());
        user.setName(nameInput.getText().toString());
        user.setPhone(phoneInput.getText().toString());
//      구글GCM 토근 아직 안받아옴
        String regToken = "1234";
        SignUpRequest request = new SignUpRequest(getContext(), user, regToken);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<User>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {
                PropertyManager.getInstance().setEmail(user.getEmail());
                PropertyManager.getInstance().setPassword(user.getPassword());

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

}
