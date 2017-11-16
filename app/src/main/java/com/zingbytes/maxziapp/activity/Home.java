package com.zingbytes.maxziapp.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.zingbytes.maxziapp.R;
import com.zingbytes.maxziapp.adapter.AllFoodItemAdapter;
import com.zingbytes.maxziapp.fragment.Eats;
import com.zingbytes.maxziapp.fragment.HomeFragment;
import com.zingbytes.maxziapp.fragment.Market;
import com.zingbytes.maxziapp.fragment.Profile;
import com.zingbytes.maxziapp.model.AllFoodItem;
import com.zingbytes.maxziapp.model.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    List<Category> ListOfAllFoodItemAdapter;
    boolean doubleBackToExitPressedOnce = false;
    String HTTP_JSON_URL = "https://api.myjson.com/bins/1cb183";
    String Image_Name_JSON = "image_title";
    JsonArrayRequest AllfoodItemJSonArray;
    RequestQueue requestQueueAllfoodItem;
    ArrayList<AllFoodItem> mSuggestionArrayList;
    AutoCompleteTextView mAutoCompleteTextView;
    AllFoodItem allFoodItem;
    Toolbar toolbar;
    AllFoodItemAdapter allFoodItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ListOfAllFoodItemAdapter = new ArrayList<>();
        mSuggestionArrayList = new ArrayList<AllFoodItem>();

        mAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autocompletetextview);
        getAllItemData();

        displaySelectedScreen(R.id.nav_menu1);


    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (isTaskRoot()) {

                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                {
                    boolean done = getSupportFragmentManager().popBackStackImmediate();
                }
             else
                {
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.ic_dialog_alert)
                        .setTitle("Close")
                        .setMessage("Do you want to close maxzi app ? ")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //  mediaPlayer.stop();
                                //  status=false;
                                finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();

                }
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

            mAutoCompleteTextView.setVisibility(View.VISIBLE);
            displaySelectedScreen(R.id.nav_menu1);
            Intent intent = new Intent(Home.this,Home.class);
            startActivity(intent);
            //Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.tracking)
        {

            Toast.makeText(this, "tracks", Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.coins)
        {

            Toast.makeText(this, "coins", Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.order)
        {

            Toast.makeText(this, "order", Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.account)
        {

            mAutoCompleteTextView.setVisibility(View.GONE);
            Fragment fragment = new Profile();
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.addToBackStack(null);
            ft.commit();
            //Toast.makeText(this, "account", Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.contact)
        {

            Toast.makeText(this, "Contact", Toast.LENGTH_SHORT).show();


        }

        else if (id == R.id.logout)
        {

            //Toast.makeText(this, "Contact", Toast.LENGTH_SHORT).show();
            displaySelectedScreen(R.id.nav_menu1);
            Intent intent = new Intent(Home.this,SignIn.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    private void displaySelectedScreen(int itemId) {
        //creating fragment object
        Fragment fragment = null;
        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_menu1:
                fragment = new HomeFragment();
                break;
            case R.id.nav_menu2:
                toolbar.setTitle("MARKET");
                fragment = new Market();
                break;
            case R.id.nav_menu3:
                toolbar.setTitle("EATS");
                fragment = new Eats();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }

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

                        Toast.makeText(Home.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueueAllfoodItem = Volley.newRequestQueue(Home.this);
        requestQueueAllfoodItem.add(AllfoodItemJSonArray);
        allFoodItemAdapter = new AllFoodItemAdapter(Home.this, mSuggestionArrayList,mAutoCompleteTextView);
        mAutoCompleteTextView.setThreshold(1);//will start working from first character
        mAutoCompleteTextView.setAdapter(allFoodItemAdapter);//

    }



}
