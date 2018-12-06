package com.example.user.myparkingapp.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.user.myparkingapp.MainPageActivity;
import com.example.user.myparkingapp.R;
import com.example.user.myparkingapp.Shared_Preferance;
import com.example.user.myparkingapp.Singleton;
import com.example.user.myparkingapp.User.Authentication.UserLoginActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserBookingActivity extends AppCompatActivity {
    String property_id, user_id, type, startTime, endTime, address, price;
    TextView addressDisplay, priceDisplay, typeDisplay, startTimeDisplay, endTimeDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_booking);
        addressDisplay = (TextView) findViewById(R.id.addressInBooking);
       // startTimeDisplay = (TextView) findViewById(R.id.startTimeConf);
       // endTimeDisplay = (TextView) findViewById(R.id.endTimeConf);
        priceDisplay = (TextView) findViewById(R.id.price);
        typeDisplay = (TextView) findViewById(R.id.typeConf);


        Intent intent = getIntent();
        property_id = intent.getStringExtra("property_id");
        user_id = intent.getStringExtra("user_id");
        type = intent.getStringExtra("type");
        startTime = intent.getStringExtra("startTime");
        endTime = intent.getStringExtra("startTime");
        address = intent.getStringExtra("address");
        price = intent.getStringExtra("price");

        addressDisplay.setText(address);
    //    startTimeDisplay.setText(startTime);
    //    endTimeDisplay.setText(endTime);
        priceDisplay.setText(price);
        typeDisplay.setText(type);


    }

    public void book(View view) {
//        property_id = propertyIdDisplay.getText().toString().trim();
//        user_id     = userIdDisplay.getText().toString().trim();
//        type          = typeDisplay.getText().toString().trim();
//        startTime     = startTimeDisplay.getText().toString().trim();
//        startTime      = endTimeDisplay.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.0.116/parking_system/finale_booking.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String value = jsonObject.getString("error");

                            if (value.equals("false")) {
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            } else {
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override

            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserBookingActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("property_id", "1");
                param.put("user_id", "1");
                param.put("type", "four_wheeler");
                param.put("start_time", "2018-03-30 02:30:00");
                param.put("end_time", "2018-03-30 04:30:00");
                param.put("price", "80");

                return param;
            }
        };
        Singleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public boolean onOptionsItemsSelected(MenuItem item) {
        //
        switch (item.getItemId()) {

            case R.id.logout:

                Shared_Preferance.getInstance(getApplicationContext()).logout();

                Intent intent = new Intent(this, MainPageActivity.class);
                this.startActivity(intent);



                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }
}
