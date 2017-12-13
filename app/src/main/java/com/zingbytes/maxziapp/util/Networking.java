package com.mobantica.rideIt;


import android.content.Context;
import android.util.Log;

import com.mobantica.rideIt.comman.SharedPreferencesUtil;
import com.mobantica.rideIt.comman.UtilsConstants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

import static com.mobantica.rideIt.comman.UtilsConstants.TOKEN_KEY;
import static com.mobantica.rideIt.comman.UtilsConstants.X_API_KEY;
import static com.mobantica.rideIt.comman.UtilsConstants.X_API_KEY_VALUE;

/**
 * Created by PC-2 on 10/14/2015.
 */
public class Networking {
    public static final MediaType JSON = MediaType.parse("application/json; charset=UTF8");
    OkHttpClient client = new OkHttpClient();
    private String TAG = "Networking";
    private String token = "";

    public Networking() {
        client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }


    public String doPostRequest(String url, RequestBody requestBody, Context context) throws IOException {
        token = SharedPreferencesUtil.getInstance(context).getData(TOKEN_KEY);
        Log.d(TAG, "token:" + token);
        Request request = new Request.Builder()
                .header(X_API_KEY, X_API_KEY_VALUE)
                .header(TOKEN_KEY, token)
                .url(url)
                .post(requestBody)
                .build();
//        client.connectTimeoutMillis();  //Connect timeout
//        client.readTimeoutMillis();    //Socket timeout
        String requestvody = bodyToString(requestBody);
        Log.d(TAG, "requestBody:" + requestvody);
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String doGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    public String withHeaderDoGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .header(X_API_KEY, X_API_KEY_VALUE)
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    public String withTokenHeaderDoGetRequest(String url, Context context) throws IOException {
        token = SharedPreferencesUtil.getInstance(context).getData(TOKEN_KEY);
        Log.d(TAG, "token:" + token);
        Request request = new Request.Builder()
                .header(X_API_KEY, X_API_KEY_VALUE)
                .header(TOKEN_KEY, token)
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    private static String bodyToString(final RequestBody request) {
        try {
            final Buffer buffer = new Buffer();
            request.writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
