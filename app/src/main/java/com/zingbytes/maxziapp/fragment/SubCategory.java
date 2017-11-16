package com.zingbytes.maxziapp.fragment;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.zingbytes.maxziapp.R;

import net.skoumal.fragmentback.BackFragment;

public class SubCategory extends Fragment implements BackFragment {

    View view1;
    Fragment fragment=null;


    public SubCategory() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view1 = inflater.inflate(R.layout.fragment_sub_category, container, false);

        return view1;

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
