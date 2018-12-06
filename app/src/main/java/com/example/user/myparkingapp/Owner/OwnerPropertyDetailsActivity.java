package com.example.user.myparkingapp.Owner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.user.myparkingapp.MainPageActivity;
import com.example.user.myparkingapp.Owner.Authentication.OwnerLoginActivity;
import com.example.user.myparkingapp.R;
import com.example.user.myparkingapp.Shared_Preferance;
import com.example.user.myparkingapp.Singleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class OwnerPropertyDetailsActivity extends AppCompatActivity {

    EditText editOwnerPropertyAreaSize, editOwnerPropertyAddress, editOwnerPropertyNoOfTwoWheeler,
            editOwnerPropertyNoOfFourWheeler;
    LocationManager locationManager;
    ImageView imageOfProperty;
    RadioGroup radioGroup;
    RadioButton radioButtonYes, radioButtonNo;
    RadioButton radioButton;
    Button submit;

    String areaSize, address, noOfTwoWheeler, noOfFourWheeler, ownerid;
    String Latitude, Longitude, str = null, addressIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_owner_property_details);

        editOwnerPropertyAreaSize = (EditText) findViewById(R.id.areaSize);
        editOwnerPropertyAddress = (EditText) findViewById(R.id.addressInPropertyDetails);


        editOwnerPropertyNoOfTwoWheeler = (EditText) findViewById(R.id.noOfTwoWheeler);
        editOwnerPropertyNoOfFourWheeler = (EditText) findViewById(R.id.noOfFourWheeler);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioButtonNo = (RadioButton) findViewById(R.id.radioButtoNo);
        radioButtonYes = (RadioButton) findViewById(R.id.radioButtonYes);

      //  imageOfProperty = (ImageView) findViewById(R.id.photos);

        Intent intent = getIntent();
        addressIntent = intent.getStringExtra("address");
        Latitude = intent.getStringExtra("latitude");
        Longitude = intent.getStringExtra("longitude");
        editOwnerPropertyAddress.setText(addressIntent);
        //getLocationBtn = (Button)findViewById(R.id.getLocationBtn);
        submit = (Button) findViewById(R.id.submitInPropertyDetails);


    }

    public void propertyDataToDatabase(View view) {
        areaSize = editOwnerPropertyAreaSize.getText().toString().trim();
        address = editOwnerPropertyAddress.getText().toString().trim();
        noOfTwoWheeler = editOwnerPropertyNoOfFourWheeler.getText().toString().trim();
        noOfFourWheeler = editOwnerPropertyNoOfTwoWheeler.getText().toString().trim();
       // Toast.makeText(this, areaSize+address+noOfTwoWheeler+noOfFourWheeler+Longitude+Longitude, Toast.LENGTH_SHORT).show();

        // int selectedId = radioGroup .getCheckedRadioButtonId();
        // radioButton=(RadioButton)findViewById(selectedId);

        ownerid = Shared_Preferance.getInstance(getApplicationContext()).getOwnerId();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.0.116/parking_system/propertydetail.php",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String value = jsonObject.getString("error");
                            if (value.equals("false")) {
                                Toast.makeText(OwnerPropertyDetailsActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(OwnerPropertyDetailsActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
               param.put("owner_id", "1");
               param.put("area_size", "45");
                param.put("address", "kgh");
                param.put("security", "1");
                param.put("two_wheeler", "3");
                param.put("four_wheeler", "3");
                param.put("photo", "jsbshdbirb");


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

        // }
        //public void img(View view){
        //    Intent intent = new Intent();
        //    intent.setType("image/*");
        //    intent.setAction(Intent.ACTION_GET_CONTENT);//
        //    startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELET_IMAGE);
        //}
        //public void onActivityResult(int requestCode, int resultCode, Intent data)
        //{

        //    super.onActivityResult(requestCode, resultCode, data);
        //    if (requestCode == SELECT_IMAGE)
        //    {
        //        if (resultCode == Activity.RESULT_OK)
        //        {
        //            if (data != null)
        //            {
        //                try
        //                {

        //                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());

        //                } catch (IOException e)
        //                {
        //                    e.printStackTrace();
        //                }

        //            }
        //        } else if (resultCode == Activity.RESULT_CANCELED)
        //        {
        //            Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
        //        }
        //    }
        //}

        return true;}
}