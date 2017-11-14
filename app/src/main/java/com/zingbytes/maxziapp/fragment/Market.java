package com.zingbytes.maxziapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.zingbytes.maxziapp.R;
import com.zingbytes.maxziapp.adapter.CategoryAdapter;
import com.zingbytes.maxziapp.model.Category;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Market extends Fragment {


    List<Category> ListOfCategoryAdapter;
    String HTTP_JSON_URL = "http://androidblog.esy.es/ImageJsonData.php";
    String Image_Name_JSON = "image_title";
    String Image_URL_JSON = "image_url";
    JsonArrayRequest CategoryJSonArray;
    RequestQueue requestQueueCategory;
    View view ;

    int RecyclerViewItemPosition ;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManagerOfrecyclerView;
    RecyclerView.Adapter recyclerViewadapter;
    ArrayList<String> ImageTitleNameArrayListForClick;


    public Market() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view1  =  inflater.inflate(R.layout.fragment_market, container, false);
        ImageTitleNameArrayListForClick = new ArrayList<>();

        ListOfCategoryAdapter = new ArrayList<>();

        recyclerView = (RecyclerView) view1.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManagerOfrecyclerView = new GridLayoutManager(getActivity(), 3);

        //   layoutManagerOfrecyclerView = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManagerOfrecyclerView);

        getCategoryData();
        // Implementing Click Listener on RecyclerView.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                view = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if(view != null && gestureDetector.onTouchEvent(motionEvent)) {

                    //Getting RecyclerView Clicked Item value.
                    RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(view);

                    // Showing RecyclerView Clicked Item value using Toast.
                    Toast.makeText(getActivity(), ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


        return view1;
    }

    public void getCategoryData(){

        CategoryJSonArray = new JsonArrayRequest(HTTP_JSON_URL,new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                ParseJSonResponse(response);
                Log.d("response Category ",response.toString());
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueueCategory = Volley.newRequestQueue(getActivity());
        requestQueueCategory.add(CategoryJSonArray);
    }

    public void ParseJSonResponse(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            Category GetDataAdapter2 = new Category();
            JSONObject json = null;
            try
            {

                json = array.getJSONObject(i);
                GetDataAdapter2.setName(json.getString(Image_Name_JSON));
                // Adding image title name in array to display on RecyclerView click event.
                ImageTitleNameArrayListForClick.add(json.getString(Image_Name_JSON));
                GetDataAdapter2.setImageurl(json.getString(Image_URL_JSON));

            }
            catch (JSONException e)
            {

                e.printStackTrace();
            }
            ListOfCategoryAdapter.add(GetDataAdapter2);
        }

        recyclerViewadapter = new CategoryAdapter(ListOfCategoryAdapter, getActivity());
        recyclerView.setAdapter(recyclerViewadapter);
    }

}
