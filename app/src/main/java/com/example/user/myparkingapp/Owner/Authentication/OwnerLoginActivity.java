package com.example.user.myparkingapp.Owner.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.user.myparkingapp.Owner.OwnerCheckForLocationActivity;
import com.example.user.myparkingapp.Singleton;
import com.example.user.myparkingapp.Shared_Preferance;
import com.example.user.myparkingapp.R;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class OwnerLoginActivity extends AppCompatActivity {

    EditText editTextOwnerMobileNo, editTextOwnerPassword;
    Button btnOwnerLogin, btnOwnerSignUp;
    String mobileNo, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getSupportActionBar().hide();
        setContentView(R.layout.activity_owner_login);

        editTextOwnerMobileNo = (EditText) findViewById(R.id.edit_mobile_no);
        editTextOwnerPassword = (EditText) findViewById(R.id.edit_password);
        btnOwnerLogin = (Button) findViewById(R.id.login_button);
        btnOwnerSignUp = (Button) findViewById(R.id.sign_up_button);
        btnOwnerSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OwnerLoginActivity.this, OwnerRegistrationActivity.class);
                startActivity(intent);
            }
        });
    }


    public void clkk(View view) {
        mobileNo = editTextOwnerMobileNo.getText().toString().trim();
        password = editTextOwnerPassword.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.0.116/parking_system/ownerlogin.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String value = jsonObject.getString("error");
                              String owner_id = jsonObject.getString("owner_id");

                            Shared_Preferance.getInstance(getApplicationContext()).ownerId(owner_id);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                            if (value.equals("false")) {
                                Toast.makeText(getApplicationContext(), owner_id, Toast.LENGTH_SHORT).show();


                                Intent intent = new Intent(OwnerLoginActivity.this, OwnerCheckForLocationActivity.class);

                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(OwnerLoginActivity.this, "not", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override

            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OwnerLoginActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("mobile_no", mobileNo);
                param.put("password", password);
                return param;
            }
        };
        Singleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}