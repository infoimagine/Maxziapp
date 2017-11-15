package com.zingbytes.maxziapp.fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

import com.zingbytes.maxziapp.R;

public class Profile extends Fragment implements View.OnClickListener {

    public static Profile thisfragmentProfile = null;
    Context mContext;
    ImageView addAddressDetail,addPaymentDetail;


    //Dialog
    private Dialog dialogAddMoney;
    private Button btnCancel, btnSubmit;
    private EditText edtEnterAmount;


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

                openDialogAddAddress();


            }
        });

        return view;
    }

    private void openDialogAddAddress() {
        View lstGroupView = getActivity().getLayoutInflater().inflate(R.layout.dailog_add_address, null);
        dialogAddMoney = new Dialog(getActivity(), R.style.MaterialDialogSheet);
        dialogAddMoney.setContentView(lstGroupView);
        dialogAddMoney.setCancelable(true);
        dialogAddMoney.getWindow().setGravity(Gravity.CENTER);
        dialogAddMoney.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        edtEnterAmount = (EditText) dialogAddMoney.findViewById(R.id.edtEnterAmount);
        btnCancel = (Button) dialogAddMoney.findViewById(R.id.btnCancel);
        btnSubmit = (Button) dialogAddMoney.findViewById(R.id.btnSubmit);
        btnCancel.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        dialogAddMoney.show();
    }


    private void openDialogPaymwent() {
        View lstGroupView = getActivity().getLayoutInflater().inflate(R.layout.dailog_add_address, null);
        dialogAddMoney = new Dialog(getActivity(), R.style.MaterialDialogSheet);
        dialogAddMoney.setContentView(lstGroupView);
        dialogAddMoney.setCancelable(true);
        dialogAddMoney.getWindow().setGravity(Gravity.CENTER);
        dialogAddMoney.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        edtEnterAmount = (EditText) dialogAddMoney.findViewById(R.id.edtEnterAmount);
        btnCancel = (Button) dialogAddMoney.findViewById(R.id.btnCancel);
        btnSubmit = (Button) dialogAddMoney.findViewById(R.id.btnSubmit);
        btnCancel.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        dialogAddMoney.show();
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnSubmit:
                        dialogAddMoney.dismiss();
                        String AMOUNT = edtEnterAmount.getText().toString().trim();
                        Intent intent = new Intent(mContext, Profile.class);
                        getActivity().startActivity(intent);
                         break;
            case R.id.btnCancel:
                dialogAddMoney.dismiss();
                break;
        }

    }
}
