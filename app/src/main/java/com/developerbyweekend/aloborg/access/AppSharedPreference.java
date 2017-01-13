package com.developerbyweekend.aloborg.access;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ashish on 13/01/17.
 */

public class AppSharedPreference {
    private static String APPNAME = "AloBorg";
    private static String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static String LOGGED_STATUS = "LOGGED_STATUS";

    public void saveApplicationContext(Context context, String access_token, Boolean logged_status){
        SharedPreferences pref = context.getSharedPreferences(APPNAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(ACCESS_TOKEN, access_token);
        editor.putBoolean(LOGGED_STATUS, logged_status);
        editor.apply();
    }

    public void clearApplicationContext(Context context){
        SharedPreferences pref = context.getSharedPreferences(APPNAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.commit();
    }

}
