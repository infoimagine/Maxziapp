package com.zingbytes.maxziapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zingbytes.maxziapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Eats extends Fragment {


    public Eats() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eats, container, false);
    }

}
