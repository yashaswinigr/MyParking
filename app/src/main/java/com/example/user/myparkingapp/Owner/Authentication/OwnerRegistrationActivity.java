package com.example.user.myparkingapp.Owner.Authentication;

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
import com.example.user.myparkingapp.Singleton;
import com.example.user.myparkingapp.R;
import com.example.user.myparkingapp.User.Authentication.UserRegistrationActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OwnerRegistrationActivity extends AppCompatActivity {
Button btn;
String mobileNo,password,name,address;
EditText editTextOwnerMobileNo,editTextOwnerPassword,editTextOwnerName,editTextOwnerAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_registration);
        editTextOwnerMobileNo=(EditText)findViewById(R.id.edit_owner_registration_mobile_no);
        editTextOwnerPassword=(EditText)findViewById(R.id.edit_owner_registration_password);
        editTextOwnerName=(EditText)findViewById(R.id.edit_owner_registration_name);
        editTextOwnerAddress=(EditText)findViewById(R.id.edit_owner_registration_address);
        btn=(Button)findViewById(R.id.login_user_button);

    }
    public  void onclk(View view){
        clk();
       //Intent intent=new Intent(OwnerRegistrationActivity.this,OwnerLoginActivity.class);
       //startActivity(intent);
    }
    public void clk() {
        mobileNo = editTextOwnerMobileNo.getText().toString().trim();
        password = editTextOwnerPassword.getText().toString().trim();
        name = editTextOwnerName.getText().toString().trim();
        address = editTextOwnerAddress.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://10.0.0.116/parking_system/registerowner.php",

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(OwnerRegistrationActivity.this,jsonObject.getString("message") , Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(OwnerRegistrationActivity.this,OwnerLoginActivity.class );
                            startActivity(intent);
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OwnerRegistrationActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("mobile_no", mobileNo);
                param.put("password", password);
                param.put("name", name);
                param.put("address", address);
                return param;
            }
        };
        Singleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}
