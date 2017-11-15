package com.zingbytes.maxziapp.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.zingbytes.maxziapp.R;
import com.zingbytes.maxziapp.volleyExtra.SignInClass;

import org.json.JSONException;
import org.json.JSONObject;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        final EditText et_email = (EditText) findViewById(R.id.email);
        final EditText et_pass = (EditText) findViewById(R.id.password);
        final Button btn_login = (Button) findViewById(R.id.login);
        final TextView txt_registration = (TextView)findViewById(R.id.registerTxt);
        final TextView txt_forgive = (TextView)findViewById(R.id.forgivepassTxt);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = et_email.getText().toString().trim();
                final String password = et_pass.getText().toString().trim();

                if(email.equals("") || password.equals("")){

                    Toast.makeText(SignIn.this, "Please filled all field !!", Toast.LENGTH_SHORT).show();

                }
                else
                {

                    if(isValidEmail(email)){


                        Response.Listener<String> responseListener = new Response.Listener<String>() {


                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    String code = jsonResponse.getString("code");
                                    int id = jsonResponse.getInt("id");
                                    String name = jsonResponse.getString("name");
                                    if (code.equals("reg_success")) {

                            /*    Intent i = new Intent(login.this, dashboard.class);
                                i.putExtra("id", id + "");
                                i.putExtra("name", name);
                                i.putExtra("email", email);
                                i.putExtra("pass", password);
                                Toast.makeText(getApplicationContext(), name + id + email + password, Toast.LENGTH_LONG).show();
                            */

                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(SignIn.this);
                                        builder.setMessage("Failed")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();


                                    }
                                }
                                catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        };

                        SignInClass signInClass = new SignInClass(email, password, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(SignIn.this);
                        queue.add(signInClass);

                    }
                    else
                    {

                        Toast.makeText(SignIn.this, "Enter valid email ID !!", Toast.LENGTH_SHORT).show();

                    }

                }

            }
        });


        txt_forgive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignIn.this,Forgotpassword.class);
                startActivity(intent);
                finish();

            }
        });


        txt_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignIn.this,SignUp.class);
                startActivity(intent);
                finish();

            }
        });

    }
    public boolean isValidEmail(String email){

        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}