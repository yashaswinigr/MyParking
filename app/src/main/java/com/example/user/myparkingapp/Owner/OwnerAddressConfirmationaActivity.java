package com.example.user.myparkingapp.Owner;

import android.content.Context;
import android.content.Intent;
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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myparkingapp.R;

import java.util.List;
import java.util.Locale;


public class OwnerAddressConfirmationaActivity extends AppCompatActivity {
    Button btnYes,btnNo;
    TextView locationText;
    LocationManager locationManager;
    String address ,latitude,longitude;
    String city;
    String state;
    String country;
    String postalCode;
    String knownName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_address_confirmation);
        btnYes=(Button)findViewById(R.id.latlong);
        btnNo=(Button)findViewById(R.id.addChanges);

        Intent intent=getIntent();
        address=intent.getStringExtra("address");
        latitude=intent.getStringExtra("latitude");
        longitude=intent.getStringExtra("longitude");
//         add       =intent.getStringExtra("address");
//         city      =intent.getStringExtra("city");
//         state     =intent.getStringExtra("state");
//         country   =intent.getStringExtra("country");
//         postalCode=intent.getStringExtra("postalCode");
//         knownName =intent.getStringExtra("knownName");

  //      String address=add+city+state+country+postalCode+knownName;

        locationText = (TextView)findViewById(R.id.location);
        locationText.setText(address);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OwnerAddressConfirmationaActivity.this,OwnerPropertyDetailsActivity.class);
                intent.putExtra("address",address);
                intent.putExtra("latitude",latitude);
                intent.putExtra("longitude",longitude);
//                intent.putExtra("address",add);
//                intent.putExtra("city",city);
//                intent.putExtra("state",state);
//                intent.putExtra("country",country);
//                intent.putExtra("postalCode",postalCode);
//                intent.putExtra("knownName",knownName);
                startActivity(intent);
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OwnerAddressConfirmationaActivity.this,OwnerPropertyLocationUsingMapActivity.class);
                startActivity(intent);
                finish();
//                Intent intent=new Intent(OwnerAddressConfirmationaActivity.this,OwnerAddressChangesActivity.class);
//                intent.putExtra("address",add);
//                intent.putExtra("city",city);
//                intent.putExtra("state",state);
//                intent.putExtra("country",country);
//                intent.putExtra("postalCode",postalCode);
//                intent.putExtra("knownName",knownName);
//                startActivity(intent);
            }
        });


    }

}