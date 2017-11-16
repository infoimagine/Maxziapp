package com.zingbytes.maxziapp.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zingbytes.maxziapp.R;

/**
 * Created by Ravi on 29-12-2016.
 */

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView heading;
    public ImageView image;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        heading = (TextView)itemView.findViewById(R.id.heading);
        image = (ImageView)itemView.findViewById(R.id.image);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Item Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}