package com.example.user.myparkingapp.Owner;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myparkingapp.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.List;
import java.util.Locale;

public class OwnerPropertyLocationUsingMapActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private int PLACE_PICKER_REQUEST = 1;
    private TextView tvPlaceDetails;
   // private FloatingActionButton fabPickPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_property_location_using_map);
        initViews();

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this,this)
                .build();


                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(OwnerPropertyLocationUsingMapActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

    }

    private void initViews() {
     //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     //  setSupportActionBar(toolbar);
       // fabPickPlace = (FloatingActionButton) findViewById(R.id.fab);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
       // Snackbar.make(fabPickPlace, connectionResult.getErrorMessage() + "", Snackbar.LENGTH_LONG).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                StringBuilder stBuilder = new StringBuilder();
                String placename = String.format("%s", place.getName());
                String latitude = String.valueOf(place.getLatLng().latitude);
                String longitude = String.valueOf(place.getLatLng().longitude);
                String address = String.format("%s", place.getAddress());
                stBuilder.append("Name: ");
                stBuilder.append(placename);
                stBuilder.append("\n");
                stBuilder.append("Latitude: ");
                stBuilder.append(latitude);
                stBuilder.append("\n");
                stBuilder.append("Logitude: ");
                stBuilder.append(longitude);
                stBuilder.append("\n");
                stBuilder.append("Address: ");
                stBuilder.append(address);
//                Place place = PlacePicker.getPlace(data, this);
//
//                StringBuilder stBuilder = new StringBuilder();
//                String placename = String.format("%s", place.getName());
//                String latitude = String.valueOf(place.getLatLng().latitude);
//                String longitude = String.valueOf(place.getLatLng().longitude);
//                double Latitude =Double.parseDouble(latitude);
//                double Longitude =Double.parseDouble(longitude);
//                Toast.makeText(this, Latitude+"  "+Longitude, Toast.LENGTH_SHORT).show();
//
//                Geocoder geocoder;

//                List<Address> addresses;
//
//                geocoder = new Geocoder(this, Locale.getDefault());
//
//
//                addresses = geocoder.getFromLocation(Latitude, Longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//
//                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//                String city = addresses.get(0).getLocality();
//                String state = addresses.get(0).getAdminArea();
//                String country = addresses.get(0).getCountryName();
//                String postalCode = addresses.get(0).getPostalCode();
//                String knownName = addresses.get(0).getFeatureName();
//
//
               Intent intent=new Intent(OwnerPropertyLocationUsingMapActivity.this,OwnerAddressConfirmationaActivity.class);
                intent.putExtra("address",address);
                intent.putExtra("latitude",latitude);
                intent.putExtra("longitude",longitude);

//                intent.putExtra("city",city);
//                intent.putExtra("state",state);
//                intent.putExtra("country",country);
//                intent.putExtra("postalCode",postalCode);
//                intent.putExtra("knownName",knownName);
                startActivity(intent);
                finish();

            }
        }
    }
}