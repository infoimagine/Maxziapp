package com.zingbytes.maxziapp.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.zingbytes.maxziapp.R;
import com.zingbytes.maxziapp.model.AllFoodItem;

import java.util.ArrayList;
import java.util.List;

public class AllFoodItemAdapter extends BaseAdapter implements Filterable {

    private Context mContext;
    private List<AllFoodItem> originalList;
    private List<AllFoodItem> suggestions = new ArrayList<>();
    private Filter filter = new CustomFilter();
    private AutoCompleteTextView autoCompleteTextView;

    public AllFoodItemAdapter(Context context, List<AllFoodItem> originalList,AutoCompleteTextView autoCompleteTextView) {
        this.mContext = context;
        this.originalList = originalList;
        this.autoCompleteTextView = autoCompleteTextView;
    }

    @Override
    public int getCount() {
        return suggestions.size();
    }

    @Override
    public AllFoodItem getItem(int i) {
        return suggestions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        final int j = i;
        if (view == null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.auto_all_food_item, viewGroup, false);
            holder = new ViewHolder();
            holder.autoText = ((TextView) view.findViewById(R.id.textview));
            holder.Id = (TextView) view.findViewById(R.id.textviewId);
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        holder.autoText.setText(suggestions.get(i).getFoodname());
        holder.Id.setText(suggestions.get(i).getFoodCat());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), ""+suggestions.get(j).getFoodname(), Toast.LENGTH_SHORT).show();
                autoCompleteTextView.setText(suggestions.get(j).getFoodname());
            }
        });

        return view;

    }

    private static class ViewHolder {
        TextView autoText;
        TextView Id;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            suggestions.clear();
            // Check if the Original List and Constraint aren't null.
            if (originalList != null && constraint != null) {
                for (int i = 0; i < originalList.size(); i++) {
                    // Compare item in original list if it contains constraints.
                    if (originalList.get(i).getFoodname().toLowerCase().contains(constraint)) {
                        // If TRUE add item in Suggestions.
                        suggestions.add(originalList.get(i));
                    }
                }
            }
            // Create new Filter Results and return this to publishResults;
            FilterResults results = new FilterResults();
            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }


}
