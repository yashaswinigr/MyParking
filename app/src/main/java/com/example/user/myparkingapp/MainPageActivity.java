package com.example.user.myparkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.user.myparkingapp.Owner.Authentication.OwnerLoginActivity;
import com.example.user.myparkingapp.Owner.OwnerCheckForLocationActivity;
import com.example.user.myparkingapp.Shared_Preferance;
import com.example.user.myparkingapp.User.Authentication.UserLoginActivity;

public class MainPageActivity extends AppCompatActivity {
Button btnOwner,btnUser;
String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        btnOwner=(Button)findViewById(R.id.id_owner);
        btnUser=(Button)findViewById(R.id.id_user);
        str= Shared_Preferance.getInstance(getApplicationContext()).getOwnerId();
//        if(TextUtils.isEmpty(str))
//        {
//
//        }else {
//            Intent intent = new Intent(MainPageActivity.this, OwnerCheckForLocationActivity.class);
//            startActivity(intent);
//
//        }
        btnOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainPageActivity.this, OwnerLoginActivity.class);
                startActivity(intent);

            }
        });
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainPageActivity.this, UserLoginActivity.class);
                startActivity(intent);

            }
        });
    }

}
