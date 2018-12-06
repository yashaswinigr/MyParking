package com.example.user.myparkingapp.User.Authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.user.myparkingapp.Owner.Authentication.OwnerLoginActivity;
import com.example.user.myparkingapp.Singleton;
import com.example.user.myparkingapp.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserRegistrationActivity extends AppCompatActivity {
    Button btn;
    EditText editTextUserMobileNo,editTextUserPassword;
    String mobileNo,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        btn=(Button)findViewById(R.id.login_user_button);
        editTextUserMobileNo=(EditText)findViewById(R.id.edit_user_register_mobile_no);
        editTextUserPassword=(EditText)findViewById(R.id.edit_user_register_password);
    }
    public  void registeruser(View view){
        mobileNo = editTextUserMobileNo.getText().toString().trim();
        password = editTextUserPassword.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.0.116/parking_system/registeruser.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String value = jsonObject.getString("error");
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(UserRegistrationActivity.this,UserLoginActivity.class);
                            startActivity(intent);
finish();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override

            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserRegistrationActivity.this, "error", Toast.LENGTH_SHORT).show();

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
