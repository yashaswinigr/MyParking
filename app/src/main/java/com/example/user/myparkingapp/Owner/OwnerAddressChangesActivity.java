package com.example.user.myparkingapp.Owner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import com.example.user.myparkingapp.R;

public class OwnerAddressChangesActivity extends AppCompatActivity {
EditText userAddress,userCity,userState,userCountry,userPostalCode,userKnownName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_address_changes);

        Intent intent=getIntent();
        String address=intent.getStringExtra("address");
        String city=intent.getStringExtra("city");
        String state=intent.getStringExtra("state");
        String country=intent.getStringExtra("country");
        String postalCode=intent.getStringExtra("postalCode");
        String knownName=intent.getStringExtra("knownName");

userAddress=(EditText)findViewById(R.id.userAddress);
userCity=(EditText)findViewById(R.id.userCity);
userState=(EditText)findViewById(R.id.userState);
userCountry=(EditText)findViewById(R.id.userCountry);
userPostalCode=(EditText)findViewById(R.id.userPostalCode);
userKnownName=(EditText)findViewById(R.id.userKnownName);

    }
}
