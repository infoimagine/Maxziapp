package com.zingbytes.maxziapp.activity;

;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.zingbytes.maxziapp.R;
import com.zingbytes.maxziapp.adapter.AllFoodItemAdapter;
import com.zingbytes.maxziapp.adapter.CategoryAdapter;
import com.zingbytes.maxziapp.model.AllFoodItem;
import com.zingbytes.maxziapp.model.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    List<Category> ListOfCategoryAdapter;
    List<Category> ListOfAllFoodItemAdapter;

    String HTTP_JSON_URL = "http://androidblog.esy.es/ImageJsonData.php";
    String Image_Name_JSON = "image_title";
    String Image_URL_JSON = "image_url";
    JsonArrayRequest CategoryJSonArray,AllfoodItemJSonArray;
    RequestQueue requestQueueCategory,requestQueueAllfoodItem;
    View view ;

    int RecyclerViewItemPosition ;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManagerOfrecyclerView;
    RecyclerView.Adapter recyclerViewadapter;
    ArrayList<String> ImageTitleNameArrayListForClick;
    ArrayList<AllFoodItem> mSuggestionArrayList;
    AutoCompleteTextView mAutoCompleteTextView;
    AllFoodItem allFoodItem;
    AllFoodItemAdapter allFoodItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageTitleNameArrayListForClick = new ArrayList<>();

        ListOfCategoryAdapter = new ArrayList<>();
        ListOfAllFoodItemAdapter = new ArrayList<>();
        mSuggestionArrayList = new ArrayList<AllFoodItem>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManagerOfrecyclerView = new GridLayoutManager(MainActivity.this, 3);

     //   layoutManagerOfrecyclerView = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManagerOfrecyclerView);

        mAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autocompletetextview);
        getCategoryData();
        getAllItemData();
        // Implementing Click Listener on RecyclerView.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

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
                    Toast.makeText(MainActivity.this, ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();
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

                        Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueueCategory = Volley.newRequestQueue(MainActivity.this);
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

        recyclerViewadapter = new CategoryAdapter(ListOfCategoryAdapter, this);
        recyclerView.setAdapter(recyclerViewadapter);
    }

    public void getAllItemData(){

        AllfoodItemJSonArray = new JsonArrayRequest(HTTP_JSON_URL,new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {


                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject json = response.getJSONObject(i);
                        allFoodItem = new AllFoodItem();
                        allFoodItem.setFoodname(json.getString(Image_Name_JSON));
                        allFoodItem.setFoodname(json.getString(Image_Name_JSON));
                        mSuggestionArrayList.add(allFoodItem);

                    }

                    catch (JSONException e){
                        e.printStackTrace();

                    }

                }
                Log.d("response all item ",response.toString());

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueueAllfoodItem = Volley.newRequestQueue(MainActivity.this);
        requestQueueAllfoodItem.add(AllfoodItemJSonArray);
        allFoodItemAdapter = new AllFoodItemAdapter(MainActivity.this, mSuggestionArrayList,mAutoCompleteTextView);
        mAutoCompleteTextView.setThreshold(1);//will start working from first character
        mAutoCompleteTextView.setAdapter(allFoodItemAdapter);//
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home)
        {

            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.tracking)
        {

            Toast.makeText(this, "tracks", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.coins)
        {

            Toast.makeText(this, "coins", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.order)
        {

            Toast.makeText(this, "order", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.account)
        {

            Toast.makeText(this, "account", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.contact)
        {

            Toast.makeText(this, "Contact", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }



}

