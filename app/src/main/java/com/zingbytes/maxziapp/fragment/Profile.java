package com.zingbytes.maxziapp.fragment;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zingbytes.maxziapp.R;

import net.skoumal.fragmentback.BackFragment;

public class Profile extends Fragment implements View.OnClickListener, BackFragment {

    ImageView addAddressDetail,addPaymentDetail;
Fragment fragment=null;
    //Dialog
    private Dialog dialogAddAddress;
    private Button btnCancel, btnSubmit;
    private EditText edtEnterAddress;

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        addAddressDetail = (ImageView)view.findViewById(R.id.addAddressDetail);
        addPaymentDetail = (ImageView)view.findViewById(R.id.addPaymentDetail);


        addAddressDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDialogAddAddress();
            }
        });

        addPaymentDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDialogPaymwent();


            }
        });

        return view;
    }

    private void openDialogAddAddress() {

        View lstGroupView = getActivity().getLayoutInflater().inflate(R.layout.dailog_add_address, null);
        dialogAddAddress = new Dialog(getActivity(), R.style.MaterialDialogSheet);
        dialogAddAddress.setContentView(lstGroupView);
        dialogAddAddress.setCancelable(true);
        dialogAddAddress.getWindow().setGravity(Gravity.CENTER);
        dialogAddAddress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        edtEnterAddress = (EditText) dialogAddAddress.findViewById(R.id.edtEnterAddress);

        btnCancel = (Button) dialogAddAddress.findViewById(R.id.btnCancel);
        btnSubmit = (Button) dialogAddAddress.findViewById(R.id.btnAdd);

        btnCancel.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        dialogAddAddress.show();
    }

    private void openDialogPaymwent() {


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnAdd:
                        String address = edtEnterAddress.getText().toString().trim();
                        Toast.makeText(getActivity(), address, Toast.LENGTH_SHORT).show();
                        dialogAddAddress.dismiss();
                         break;

            case R.id.btnCancel:
                dialogAddAddress.dismiss();
                break;

        }

    }

    @Override
    public boolean onBackPressed() {
        Toast.makeText(getActivity(), "back press", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
        return false;
    }

    @Override
    public int getBackPriority() {
        return 0;
    }
}
