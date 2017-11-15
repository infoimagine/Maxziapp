package com.zingbytes.maxziapp.volleyExtra;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.zingbytes.maxziapp.util.Weburl;

import java.util.HashMap;
import java.util.Map;

public class SignUpClass extends StringRequest {

    private Map<String,String> params;
    public SignUpClass(String fullname, String prename, String email, String password, String promocode, Response.Listener<String> listener){
        super(Method.POST, Weburl.SIGNUP_REQUEST_URL,listener,null);
        params= new HashMap<>();
        params.put("fullname",fullname);
        params.put("prename",prename);
        params.put("email",email);
        params.put("password",password);
        params.put("promocode",promocode);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}