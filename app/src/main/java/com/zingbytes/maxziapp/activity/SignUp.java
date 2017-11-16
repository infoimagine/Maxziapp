package com.zingbytes.maxziapp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.zingbytes.maxziapp.R;
import com.zingbytes.maxziapp.volleyExtra.SignUpClass;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUp extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

            final EditText et_fullname = (EditText) findViewById(R.id.fullname);
            final EditText et_prename = (EditText) findViewById(R.id.prename);
            final EditText et_email = (EditText) findViewById(R.id.email);
            final EditText et_pass = (EditText) findViewById(R.id.password);
            final EditText et_promocode = (EditText) findViewById(R.id.promocode);

            Button sign_up_btn = (Button) findViewById(R.id.createAccount);

            Button sign_in_btn=(Button) findViewById(R.id.login);

                sign_up_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final String fullname=et_fullname.getText().toString().trim();
                    final String prename=et_prename.getText().toString().trim();
                    final String email=et_email.getText().toString().trim();
                    final String password=et_pass.getText().toString().trim();
                    final String promocode=et_promocode.getText().toString().trim();

                    if(fullname.equals("") || prename.equals("") || email.equals("") || password.equals("")){

                        Toast.makeText(SignUp.this, "Please fill all field !!!", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {

                        if(isValidEmail(email))
                        {

                            Response.Listener<String> responseListener=new  Response.Listener<String>(){

                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonResponse= new JSONObject(response);
                                        Log.d("Response Registration",jsonResponse.toString());
                                        String code=jsonResponse.getString("code");
                                        if(code.equals("success")){
                                            Intent i=new Intent(SignUp.this,SignIn.class);
                                            startActivity(i);
                                            finish();

                                        }
                                        else
                                        {
                                            AlertDialog.Builder builder=new AlertDialog.Builder(SignUp.this);
                                            builder.setMessage("Failed")
                                                    .setNegativeButton("Retry",null)
                                                    .create()
                                                    .show();


                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };


                        //    Toast.makeText(SignUp.this, ""+fullname+prename+email+password+promocode, Toast.LENGTH_SHORT).show();
                            SignUpClass signUpClass =new SignUpClass(fullname,prename,email,password,promocode,responseListener);
                            RequestQueue queue= Volley.newRequestQueue(SignUp.this);
                            queue.add(signUpClass);

                        }
                        else
                        {

                            Toast.makeText(SignUp.this, "Enter Valid Email id !!", Toast.LENGTH_SHORT).show();

                        }

                    }

                }
            });

        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignUp.this,SignIn.class);
                startActivity(intent);
                finish();

            }
        });

    }

    private static boolean isValidEmail(String email)
    {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
        }
}
