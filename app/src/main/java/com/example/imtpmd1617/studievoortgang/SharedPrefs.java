package com.example.imtpmd1617.studievoortgang;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    private static SharedPrefs sharePref = new SharedPrefs();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private SharedPrefs() {
    } //prevent creating multiple instances by making the constructor private

    //The context passed into the getInstance should be application level context.
    public static SharedPrefs getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return sharePref;
    }

    public void putStringValue(String name, String value) {
        editor.putString(name, value);
        editor.commit();
    }

    public void putBooleanValue(String name, Boolean bool){
        editor.putBoolean(name, bool);
        editor.commit();
    }

    public String getStringValue(String name) {
        return sharedPreferences.getString(name, "");
    }

    public Boolean getBooleanValue(String name) {
        return sharedPreferences.getBoolean(name, true);
    }

    public void removePlaceObj(String name) {
        editor.remove(name);
        editor.commit();
    }

    public void clearAll() {
        editor.clear();
        editor.commit();
    }
}