package com.zingbytes.maxziapp.volleyExtra;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.zingbytes.maxziapp.util.Weburl;

import java.util.HashMap;
import java.util.Map;

public class ForgotClass extends StringRequest {

    private Map<String,String> params;

    public ForgotClass(String email,Response.Listener<String> listener){
        super(Request.Method.POST, Weburl.FORGOT_REQUEST_URL,listener,null);

        params= new HashMap<>();
        params.put("email",email);

    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }

}
