package com.weddingsingers.wsapp.function.mypage.mypage;


import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.BoolRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.NetworkResult;
import com.weddingsingers.wsapp.data.User;
import com.weddingsingers.wsapp.main.MainActivity;
import com.weddingsingers.wsapp.manager.NetworkManager;
import com.weddingsingers.wsapp.manager.NetworkRequest;
import com.weddingsingers.wsapp.request.ModifyUserRequest;
import com.weddingsingers.wsapp.request.MyPageRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends Fragment {

    @BindView(R.id.user_info_riv_picture)
    RoundedImageView userImageView;

    @BindView(R.id.user_info_tv_name_content)
    TextView nameView;

    @BindView(R.id.user_info_tv_email_content)
    TextView emailView;

    @BindView(R.id.user_info_tv_phone_content)
    TextView phoneView;

    @BindView(R.id.user_info_et_password)
    EditText passwordView;

    @BindView(R.id.user_info_et_password_confirm)
    EditText passwordConfirmView;

    public UserInfoFragment() {
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

        View view = inflater.inflate(R.layout.fragment_user_info, container, false);

        ButterKnife.bind(this, view);

        userImageView.mutateBackground(true);
        userImageView.setOval(true);

        initData();

        return view;
    }

    private static final int RC_PERMISSION = 100;

    private void checkPermission() {
        List<String> permissions = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.CAMERA);
        }

        if (permissions.size() > 0) {
            boolean isShowUI = false;
            for (String perm : permissions) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), perm)) {
                    isShowUI = true;
                    break;
                }
            }

            final String[] perms = permissions.toArray(new String[permissions.size()]);

            if (isShowUI) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Permission");
                builder.setMessage("External Storage and Camera...");
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(getActivity(), perms, RC_PERMISSION);
                    }
                });
                builder.create().show();
                return;
            }

            ActivityCompat.requestPermissions(getActivity(), perms, RC_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RC_PERMISSION) {
            if (permissions != null) {
                boolean granted = true;
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        granted = false;
                    }
                }
                if (!granted) {
                    Toast.makeText(getActivity(), "permission not granted", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
            }
        }
    }

    public void initData() {
        MyPageRequest request = new MyPageRequest(getContext());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<User>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {

                User user = new User();
                user.setPhotoURL(result.getResult().getPhotoURL());
                user.setName(result.getResult().getName());
                user.setEmail(result.getResult().getEmail());
                user.setPhone(result.getResult().getPhone());

                Glide.with(getActivity())
                        .load(user.getPhotoURL())
                        //.load("https://www.ethos3.com/wp-content/uploads/2015/11/humor-public-speaking.jpg")
                        .centerCrop()
                        .crossFade()
                        .placeholder(ContextCompat.getDrawable(getActivity(), R.drawable.ic_nav_logout))
                        .into(userImageView);

                nameView.setText(user.getName());
                emailView.setText(user.getEmail());
                phoneView.setText(user.getPhone());

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "UserInfoFragment failure", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                getActivity().finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private static final int RC_GET_IMAGE = 1;

    @OnClick(R.id.user_info_riv_picture)
    public void onGetImageClick(View view) {

        checkPermission();

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, RC_GET_IMAGE);
    }

    File uploadFile = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_GET_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                Cursor c = getContext().getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                if (c.moveToNext()) {
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    uploadFile = new File(path);
                    Glide.with(this)
                            .load(uploadFile)
                            .centerCrop()
                            .crossFade()
                            .error(ContextCompat.getDrawable(getActivity(), R.drawable.ic_nav_logout))
                            .into(userImageView);
                }
            }
        }
    }

    @OnClick(R.id.user_info_btn_apply)
    public void onApplyClick() {

        String password = passwordView.getText().toString();
        String passwordConfirm = passwordConfirmView.getText().toString();

        if (!password.equals(passwordConfirm)) {
            Toast.makeText(getActivity(), "패스워드가 일치하지 않습니다. 확인해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        ModifyUserRequest request = new ModifyUserRequest(getContext(), password, uploadFile);

        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Boolean>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Boolean>> request, NetworkResult<Boolean> result) {

                Toast.makeText(getActivity(), "success code : " + result.getCode(), Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Boolean>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getActivity(), "SingerMyPageFragment fail - " + errorMessage, Toast.LENGTH_SHORT).show();

            }
        });

        //getActivity().finish();
    }

    @OnClick(R.id.user_info_btn_cancel)
    public void onCancelClick() {
        getActivity().finish();
    }

}
