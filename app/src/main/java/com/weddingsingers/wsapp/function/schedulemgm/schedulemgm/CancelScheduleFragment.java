package com.weddingsingers.wsapp.function.schedulemgm.schedulemgm;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.main.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CancelScheduleFragment extends Fragment {

    final static int FRAG_MY_PAGE = 200;


    public CancelScheduleFragment() {
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
        View view = inflater.inflate(R.layout.fragment_cancel_schedule, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.cancel_schedule_btn_cancel)
    void onCancelBtnClicked() {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Cancel complete")
                .setMessage("Cancel complete,\nyou can check your penalty in my page")
                .setPositiveButton("GO MY PAGE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moveMyPageFragment();
                    }
                });

        builder.setNegativeButton("GO MAIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                moveMainActivity();
            }
        });

        dialog = builder.create();
        dialog.show();
    }

    private void moveMyPageFragment(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra(MainActivity.FRAG_NAME, FRAG_MY_PAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }

    private void moveMainActivity() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                Toast.makeText(getContext(), "CancelScheduleActivity's HomeAsUp Clicked", Toast.LENGTH_SHORT).show();
                getActivity().finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
