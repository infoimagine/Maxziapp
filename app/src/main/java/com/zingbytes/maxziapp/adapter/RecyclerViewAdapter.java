package com.zingbytes.maxziapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.squareup.picasso.Picasso;
import com.zingbytes.maxziapp.R;
import com.zingbytes.maxziapp.model.ItemObject;
import com.zingbytes.maxziapp.util.RecyclerViewHolders;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<ItemObject> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<ItemObject> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_market_product, null);
//        layoutView.setMinimumWidth(50);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.heading.setText(itemList.get(position).getHeading());
        Picasso.with(context).load(itemList.get(position).getPhoto()).resize(120, 120).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}