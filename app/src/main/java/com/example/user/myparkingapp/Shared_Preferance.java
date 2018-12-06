package com.example.user.myparkingapp;

import android.content.Context;
import android.content.SharedPreferences;


public class Shared_Preferance {

    private static Shared_Preferance mInstance;
    private static Context ctx;

    private static final String SHARED_PREF_NAME ="sharedpref";

    private static final String Owner_ID = "owner_id";
    private static final String User_ID = "user_id";

    public Shared_Preferance(Context context) {
        ctx=context;
    }
    public static synchronized Shared_Preferance getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Shared_Preferance(context);
        }
        return mInstance;
    }
    public boolean ownerId(String id)
    {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Owner_ID,id);
        editor.apply();
        return true;
    }
    public String getOwnerId()
    {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return  sharedPreferences.getString(Owner_ID,null);
    }
    public boolean userId(String id)
    {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(User_ID,id);
        editor.apply();
        return true;
    }
    public String getUserId()
    {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return  sharedPreferences.getString(User_ID,null);
    }
    public boolean logout()
    {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

}
