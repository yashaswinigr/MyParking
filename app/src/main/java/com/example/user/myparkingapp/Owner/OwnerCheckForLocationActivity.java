package com.example.user.myparkingapp.Owner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.user.myparkingapp.R;
import com.skyfishjy.library.RippleBackground;

public class OwnerCheckForLocationActivity extends AppCompatActivity {
    Context context;
    LocationManager lm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_check_for_location);
        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);
        ImageView imageView=(ImageView)findViewById(R.id.centerImage);
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
                    AlertDialog.Builder dialog = new AlertDialog.Builder(OwnerCheckForLocationActivity.this);
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
                                final Intent mainIntent = new Intent(OwnerCheckForLocationActivity.this, OwnerAddressConfirmationaActivity.class);
                                OwnerCheckForLocationActivity.this.startActivity(mainIntent);
                                OwnerCheckForLocationActivity.this.finish();
                            }
                        }, 5000);
                    }else if(!gps_enabled && network_enabled ){
                        Toast.makeText(OwnerCheckForLocationActivity.this, "Enable Gps", Toast.LENGTH_SHORT).show();
                    }else if(gps_enabled && !network_enabled){
                        Toast.makeText(OwnerCheckForLocationActivity.this, "Enable Network", Toast.LENGTH_SHORT).show();
                    }
                }

                else{


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            final Intent mainIntent = new Intent(OwnerCheckForLocationActivity.this, OwnerPropertyLocationUsingMapActivity.class);
                            OwnerCheckForLocationActivity.this.startActivity(mainIntent);
                            OwnerCheckForLocationActivity.this.finish();
                        }
                    }, 5000);
                }}
        });
    }
}

//        import android.app.AlertDialog;
//        import android.content.Context;
//        import android.content.DialogInterface;
//        import android.content.Intent;
//        import android.location.LocationManager;
//        import android.os.Handler;
//        import android.provider.Settings;
//        import android.support.v7.app.AppCompatActivity;
//        import android.os.Bundle;
//        import android.view.View;
//        import android.widget.ImageView;
//        import android.widget.Toast;
//
//
//        import com.example.user.myparkingapp.R;
//        import com.skyfishjy.library.RippleBackground;
//
//public class OwnerCheckForLocationActivity extends AppCompatActivity {
//    Context context;
//    LocationManager lm;
//    ProgressDialog progressBar;
//    private int progressBarStatus = 0;
//    private Handler progressBarHandler = new Handler();
//    private long fileSize = 0;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_owner_check_for_location);
//        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);
//        onclk();
//    }
//    public onclk(){
//        ImageView imageView=(ImageView)findViewById(R.id.centerImage);
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // creating progress bar dialog
//                progressBar = new ProgressDialog(v.getContext());
//                progressBar.setCancelable(true);
//                progressBar.setMessage("File downloading ...");
//                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                progressBar.setProgress(0);
//                progressBar.setMax(100);
//                progressBar.show();
//                //reset progress bar and filesize status
//                progressBarStatus = 0;
//                fileSize = 0;
//
//                new Thread(new Runnable() {
//                    public void run() {
//                        while (progressBarStatus < 100) {
//                            // performing operation
//                            progressBarStatus = opera;
//                            try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
//                            // Updating the progress bar
//                            progressBarHandler.post(new Runnable() {
//                                public void run() {
//                                    progressBar.setProgress(progressBarStatus);
//                                }
//                            });
//                        }
//                        // performing operation if file is downloaded,
//                        if (progressBarStatus >= 100) {
//                            // sleeping for 1 second after operation completed
//                            try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
//                            // close the progress bar dialog
//                            progressBar.dismiss();
//                        }
//                    }
//                }).start();
//            }//end of onClick method
//        });
//    }
//}
//    public operation(){
//        {
//            rippleBackground.startRippleAnimation();
//
//            lm= (LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
//            boolean gps_enabled = false;
//            boolean network_enabled = false;
//
//            try {
//                gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
//            } catch(Exception ex) {}
//
//            try {
//                network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//            } catch(Exception ex) {}
//
//            if(!gps_enabled && !network_enabled) {
//                // notify user
//                AlertDialog.Builder dialog = new AlertDialog.Builder(OwnerCheckForLocationActivity.this);
//                AlertDialog alertDialog = dialog.create();
//                alertDialog.setMessage("gps not enabled");
//                dialog.setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
//                        // TODO Auto-generated method stub
//                        Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                        startActivity(myIntent);
//
//                        //get gps
//                    }
//                });
//                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
//                        // TODO Auto-generated method stub
//
//                    }
//                });
//                dialog.show();
//                if(gps_enabled && network_enabled){
//
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            final Intent mainIntent = new Intent(OwnerCheckForLocationActivity.this, OwnerAddressConfirmationaActivity.class);
//                            OwnerCheckForLocationActivity.this.startActivity(mainIntent);
//                            OwnerCheckForLocationActivity.this.finish();
//                        }
//                    }, 5000);
//                }else if(!gps_enabled && network_enabled ){
//                    Toast.makeText(OwnerCheckForLocationActivity.this, "Enable Gps", Toast.LENGTH_SHORT).show();
//                }else if(gps_enabled && !network_enabled){
//                    Toast.makeText(OwnerCheckForLocationActivity.this, "Enable Network", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            else{
//
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        final Intent mainIntent = new Intent(OwnerCheckForLocationActivity.this, OwnerPropertyLocationUsingMapActivity.class);
//                        OwnerCheckForLocationActivity.this.startActivity(mainIntent);
//                        OwnerCheckForLocationActivity.this.finish();
//                    }
//                }, 5000);
//            }}
//    });
//        }
//        }
