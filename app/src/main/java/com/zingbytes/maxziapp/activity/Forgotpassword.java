package com.zingbytes.maxziapp.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.zingbytes.maxziapp.R;
import com.zingbytes.maxziapp.volleyExtra.ForgotClass;
import org.json.JSONException;
import org.json.JSONObject;

public class Forgotpassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        final EditText et_email = (EditText) findViewById(R.id.email);
        final Button btn_enter = (Button) findViewById(R.id.enter);
        final Button btn_backLogin = (Button) findViewById(R.id.backLogin);


        btn_backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Forgotpassword.this,SignIn.class);
                startActivity(intent);
                finish();

            }
        });

        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = et_email.getText().toString().trim();

                if(email.equals("")){

                    Toast.makeText(Forgotpassword.this, "Pleae enter Email ID !!", Toast.LENGTH_SHORT).show();

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

                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(Forgotpassword.this);
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

                        ForgotClass forgotClass = new ForgotClass(email, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(Forgotpassword.this);
                        queue.add(forgotClass);

                    }
                    else
                    {

                        Toast.makeText(Forgotpassword.this, "Please enter valid Email ID !!", Toast.LENGTH_SHORT).show();

                    }

                }

            }
        });

    }
    public boolean isValidEmail(String email){

        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


}
