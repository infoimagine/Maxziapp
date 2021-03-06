package com.zingbytes.maxziapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zingbytes.maxziapp.R;

import net.skoumal.fragmentback.BackFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Eats extends Fragment implements BackFragment {


    Fragment fragment=null;
    public Eats() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eats, container, false);
    }

    @Override
    public boolean onBackPressed() {
        Fragment fragment=new HomeFragment();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
        return true;
    }

    @Override
    public int getBackPriority() {
        return 0;
    }
}
