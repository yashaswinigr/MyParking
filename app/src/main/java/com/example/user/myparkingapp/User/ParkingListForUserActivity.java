package com.example.user.myparkingapp.User;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.user.myparkingapp.Singleton;
import com.example.user.myparkingapp.R;
import com.example.user.myparkingapp.User.Adapter.ParkingListForUserAdapter;
import com.example.user.myparkingapp.User.Models.ParkingListForUserModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingListForUserActivity extends AppCompatActivity {
CardView cardView;
    RecyclerView list;
    List<ParkingListForUserModel> parkingList;
    ParkingListForUserModel parking;
    String str,str1;
    SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_list_for_user);


parkingList=new ArrayList<>();

clk();

    }
    public void clk() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://10.0.0.116/parking_system/detail.php",

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        list=(RecyclerView)findViewById(R.id.recycler_view);
                        try {


                            JSONObject myjson = new JSONObject(response);
                            JSONArray the_json_array = myjson.getJSONArray("detail");
                            int size = the_json_array.length();



                            if (myjson.getString("error").equals("false")) {

                                Toast.makeText(getApplicationContext(),myjson.getString("message"), Toast.LENGTH_SHORT).show();

                                parking=new ParkingListForUserModel();
                                for (int i = 0; i < size; i++) {
                                    JSONObject another_json_object = the_json_array.getJSONObject(i);

                                    parking.setAddress(another_json_object.getString("address"));
                                    parking.setMobileNo(another_json_object.getString("mobile_no"));
                                    parking.setName(another_json_object.getString("name"));
                                    parking.setPropertyId(another_json_object.getString("property_id"));
                                    parkingList.add(parking);
                                }

                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ParkingListForUserActivity.this, LinearLayoutManager.VERTICAL, false);
                                list.setLayoutManager(layoutManager);
                                ParkingListForUserAdapter Adapter = new ParkingListForUserAdapter(ParkingListForUserActivity.this, parkingList);
                                list.setAdapter(Adapter);
                            } else {
                               // Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();

                            }

                            }

                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParkingListForUserActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> param = new HashMap<String, String>();

               return param;
            }
        };
        Singleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}