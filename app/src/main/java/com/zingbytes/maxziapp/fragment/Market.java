package com.zingbytes.maxziapp.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.zingbytes.maxziapp.R;
import com.zingbytes.maxziapp.adapter.CategoryAdapter;
import com.zingbytes.maxziapp.adapter.RecyclerViewAdapter;
import com.zingbytes.maxziapp.model.Category;
import com.zingbytes.maxziapp.model.ItemObject;

import net.skoumal.fragmentback.BackFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Market extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, BackFragment {


    List<Category> ListOfCategoryAdapter;
    List<ItemObject> allItems;
    String HTTP_JSON_URL = "https://api.myjson.com/bins/1cb183";
    String Image_Name_JSON = "image_title";
    String Image_URL_JSON = "image_url";
    JsonArrayRequest CategoryJSonArray,ProductJSonArray;
    RequestQueue requestQueueCategory,requestQueueProduct;
    View view ;


    //dyanamic slider
    SliderLayout sliderLayout;
    HashMap<String,String> Hash_file_maps ;



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
        allItems = new ArrayList<ItemObject>();


        recyclerView = (RecyclerView) view1.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);


        //horizonal slider

        LinearLayoutManager lLayout;
        List<ItemObject> rowListItem = getAllItemList();
        //No of items per row  is second argument
        lLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        lLayout.setStackFromEnd(true);

        RecyclerView rView = (RecyclerView) view1.findViewById(R.id.recycler_view1);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);

        // end of horizonatal slider

        //slider
        Hash_file_maps = new HashMap<String, String>();
        sliderLayout = (SliderLayout)view1.findViewById(R.id.slider);
        Hash_file_maps.put("Android CupCake", "https://preview.ibb.co/f3OgiR/Sample_Loss_Leader.png");
        Hash_file_maps.put("Android Donut", "https://preview.ibb.co/f3OgiR/Sample_Loss_Leader.png");
        Hash_file_maps.put("Android Eclair", "https://preview.ibb.co/f3OgiR/Sample_Loss_Leader.png");
        Hash_file_maps.put("Android Froyo", "https://preview.ibb.co/f3OgiR/Sample_Loss_Leader.png");
        Hash_file_maps.put("Android GingerBread", "https://preview.ibb.co/f3OgiR/Sample_Loss_Leader.png");


        for(String name : Hash_file_maps.keySet()){

            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView
                    //.description(name)
                    .image(Hash_file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            // textSliderView.getBundle().putString("extra",name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        //  sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(this);
        // end slider
        // recyclerview for category image

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
                    Fragment fragment = new SubCategory();
                    android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, fragment);
                    ft.commit();
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

    // for page slider of MAZMImun offer
    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(getActivity(),slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}



    @Override
    public void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();
    }

    // horizonal

    private List<ItemObject> getAllItemList() {

        ProductJSonArray = new JsonArrayRequest(HTTP_JSON_URL,new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0; i<response.length(); i++) {

                    JSONObject json = null;
                    Log.d("Horizonatal View  ",response.toString());
                    try {
                        json = response.getJSONObject(i);
                        allItems.add(new ItemObject("product"+i+"","https://image.ibb.co/dHBvSm/product.png", "Total Time"));

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }

                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueueCategory = Volley.newRequestQueue(getActivity());
        requestQueueCategory.add(ProductJSonArray);

        return allItems;
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
