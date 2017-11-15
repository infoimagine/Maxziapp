package com.zingbytes.maxziapp.volleyExtra;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import com.zingbytes.maxziapp.util.Weburl;

public class SignInClass extends StringRequest {

    private Map<String,String> params;
    public SignInClass(String email, String password, Response.Listener<String> listener){
        super(Method.POST,Weburl.SIGNIN_REQUEST_URL,listener,null);
        params= new HashMap<>();
        params.put("email",email);
        params.put("password",password);

    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }

}