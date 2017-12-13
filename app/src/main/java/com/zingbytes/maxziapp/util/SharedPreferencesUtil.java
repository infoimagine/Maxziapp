package com.mobantica.rideIt.comman;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by PC-2 on 25-Feb-16.
 */
public class SharedPreferencesUtil {

    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static SharedPreferencesUtil preferencesUtil;



    public static SharedPreferencesUtil getInstance(Context context) {
        if (preferencesUtil == null) {
            preferencesUtil = new SharedPreferencesUtil();
            preferences = context.getSharedPreferences("com.mobantica.driverapp", Context.MODE_PRIVATE);
            editor = preferences.edit();
        }
        return preferencesUtil;
    }

    public void putData(String completed, String completedCount) {
        editor.putString(completed, completedCount);
        editor.apply();
    }

    public String getData(String completed) {
        return preferences.getString(completed, "");
    }
}
