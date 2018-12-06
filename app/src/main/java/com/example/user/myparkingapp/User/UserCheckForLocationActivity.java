package com.example.user.myparkingapp.User;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.myparkingapp.R;
import com.skyfishjy.library.RippleBackground;

public class UserCheckForLocationActivity extends AppCompatActivity {
    Context context;
    LocationManager lm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_check_for_loction);
        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content1);
        ImageView imageView=(ImageView)findViewById(R.id.centerImage1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rippleBackground.startRippleAnimation();

                lm= (LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
                boolean gps_enabled = false;
                boolean network_enabled = false;

                try {
                    gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
                } catch(Exception ex) {}

                try {
                    network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                } catch(Exception ex) {}

                if(!gps_enabled && !network_enabled) {
                    // notify user
                    AlertDialog.Builder dialog = new AlertDialog.Builder(UserCheckForLocationActivity.this);
                    AlertDialog alertDialog = dialog.create();
                    alertDialog.setMessage("gps not enabled");
                    dialog.setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            // TODO Auto-generated method stub
                            Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(myIntent);

                            //get gps
                        }
                    });
                    dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            // TODO Auto-generated method stub

                        }
                    });
                    dialog.show();
                    if(gps_enabled && network_enabled){

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                final Intent mainIntent = new Intent(UserCheckForLocationActivity.this,UserPropertyLocationUsingMapActivity.class);
                                UserCheckForLocationActivity.this.startActivity(mainIntent);
                                UserCheckForLocationActivity.this.finish();
                            }
                        }, 5000);
                    }else if(!gps_enabled && network_enabled ){
                        Toast.makeText(UserCheckForLocationActivity.this, "Enable Gpsq", Toast.LENGTH_SHORT).show();
                    }else if(gps_enabled && !network_enabled){
                        Toast.makeText(UserCheckForLocationActivity.this, "Enable Network", Toast.LENGTH_SHORT).show();
                    }
                }

                else{


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            final Intent mainIntent = new Intent(UserCheckForLocationActivity.this, UserPropertyLocationUsingMapActivity.class);
                            UserCheckForLocationActivity.this.startActivity(mainIntent);
                            UserCheckForLocationActivity.this.finish();
                        }
                    }, 5000);
                }}
        });
    }
}
