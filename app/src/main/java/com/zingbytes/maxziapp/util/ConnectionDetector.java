package com.mobantica.rideIt.comman;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by admin on 3/15/2016.
 */


public class ConnectionDetector {

    public static boolean isNetworkAvailable(Context _context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) _context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
