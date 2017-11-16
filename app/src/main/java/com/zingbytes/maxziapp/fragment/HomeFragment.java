package com.zingbytes.maxziapp.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.zingbytes.maxziapp.R;

import net.skoumal.fragmentback.BackFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements BackFragment {

    Fragment fragment = null;
    LinearLayout marketLayout,eatsLayout;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);


        marketLayout = (LinearLayout)view.findViewById(R.id.market_layout);
        eatsLayout = (LinearLayout)view.findViewById(R.id.eats_layout);


        marketLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(getActivity(), "market", Toast.LENGTH_SHORT).show();
                fragment = new Market();
                android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });

        eatsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "eats", Toast.LENGTH_SHORT).show();
                fragment = new Eats();
                android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });


        return view;



    }

    @Override
    public boolean onBackPressed() {
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
