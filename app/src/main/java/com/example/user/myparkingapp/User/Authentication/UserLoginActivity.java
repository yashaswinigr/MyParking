package com.example.user.myparkingapp.User.Authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.user.myparkingapp.Shared_Preferance;
import com.example.user.myparkingapp.Singleton;
import com.example.user.myparkingapp.R;
import com.example.user.myparkingapp.User.UserCheckForLocationActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserLoginActivity extends AppCompatActivity {
    EditText editTextUserMobileNo, editTextUserPassword;
    Button btnUserLogin, btnUserSignUp;
    String mobilenum,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        btnUserLogin=(Button)findViewById(R.id.login_user_button);
        btnUserSignUp=(Button)findViewById(R.id.id_user_sign_up);
        editTextUserMobileNo=(EditText)findViewById(R.id.edit_user_login_mobile_no);
        editTextUserPassword=(EditText)findViewById(R.id.edit_user_login_password);
    }
    public void loginuser(View view) {
        mobilenum = editTextUserMobileNo.getText().toString().trim();
        pass = editTextUserPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.0.116/parking_system/userlogin.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

//                        final ProgressBar progressBar = new ProgressBar(getApplicationContext());
//                        progressBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));



                        try {
//                            int visibility = (progressBar.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
//                            progressBar.setVisibility(visibility);


                            JSONObject jsonObject = new JSONObject(response);
                            String value = jsonObject.getString("error");

                            if (value.equals("false")) {
                              Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
//                                Shared_Preferance.getInstance(getApplicationContext()).userId(jsonObject.getString("user_id"));
                                Intent intent = new Intent(UserLoginActivity.this, UserCheckForLocationActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override

            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserLoginActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("mobile_no", mobilenum);
                param.put("password", pass);
                return param;
            }
        };
        Singleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    public void register(View view){

        Intent intent=new Intent(UserLoginActivity.this,UserRegistrationActivity.class);
        startActivity(intent);

        }

}
