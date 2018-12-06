package com.example.user.myparkingapp.User;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.user.myparkingapp.R;
import java.util.List;
import java.util.Locale;


public class UserAddressConfirmationActivity extends AppCompatActivity  {
    Button btnYes ,btnNo;
    TextView locationText;
    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_address_confirmation);
        btnYes=(Button)findViewById(R.id.latlong1);
        btnNo=(Button)findViewById(R.id.editaddress);

        Intent intent=getIntent();
        String result=intent.getStringExtra("address");

btnNo.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(UserAddressConfirmationActivity.this,UserPropertyLocationUsingMapActivity.class);
        startActivity(intent);
        finish();
    }
});
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserAddressConfirmationActivity.this,ParkingListForUserActivity.class);
                startActivity(intent);
            }
        });
        locationText = (TextView)findViewById(R.id.location1);
        locationText.setText(result);



    }


}