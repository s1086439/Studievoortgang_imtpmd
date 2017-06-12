package com.example.imtpmd1617.studievoortgang.Activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.imtpmd1617.studievoortgang.DatabaseHelper;
import com.example.imtpmd1617.studievoortgang.DatabaseInfo;
import com.example.imtpmd1617.studievoortgang.GsonRequest;
import com.example.imtpmd1617.studievoortgang.Models.Module;
import com.example.imtpmd1617.studievoortgang.Models.Token;
import com.example.imtpmd1617.studievoortgang.R;
import com.example.imtpmd1617.studievoortgang.VolleyHelper;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.imtpmd1617.studievoortgang.DatabaseHelper.getHelper;

public class LoginActivity extends AppCompatActivity {

    private final String PREFS_NAME = "imtpmd";
    private DatabaseHelper dbHelper;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        dbHelper = DatabaseHelper.getHelper(this);

        super.onCreate(savedInstanceState);
        this.settings = getSharedPreferences(PREFS_NAME, 0);

        settings.edit().putBoolean("firstrun", true).commit();

        if (settings.getBoolean("firstrun", true)) {
            getHelper(this);
            setContentView(R.layout.activity_login);

            Button loginButton = (Button) findViewById(R.id.loginButton);

            loginButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(isOnline()) {
                        requestSubjects();
                        requestToken();
                        startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
                        settings.edit().putBoolean("firstrun", false).commit();
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Geen internetverbinding. Probeer het later opnieuw.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
            finish();
        }
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //return true;
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void requestSubjects(){/*
        Type type = new TypeToken<List<Module>>(){}.getType();

        GsonRequest<List<Module>> request = new GsonRequest<List<Module>>("http://83.81.251.42/api/voortgang",
                type, null, new Response.Listener<List<Module>>() {
            @Override
            public void onResponse(List<Module> response) {
                processRequestSuccess(response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                processRequestError(error);
            }
        });
        VolleyHelper.getInstance(this).addToRequestQueue(request);*/
    }

    private void processRequestSuccess(List<Module> subjects ){
        for (Module cm : subjects) {
            ContentValues cv = new ContentValues();
            cv.put(DatabaseInfo.ModulesColumn.NAAM, cm.naam);
            cv.put(DatabaseInfo.ModulesColumn.ECT, cm.ect);
            dbHelper.insert(DatabaseInfo.Tables.MODULES, null, cv);
        }
        test();
    }

    private void test(){
        List<String> array = new ArrayList<String>();
        Cursor rs = dbHelper.query(DatabaseInfo.Tables.MODULES, new String[]{"*"}, null, null, null, null, null);
        rs.moveToFirst();
        while(rs.moveToNext()){
            String name = rs.getString(rs.getColumnIndex("naam"));
            array.add(name);
        }
        DatabaseUtils.dumpCursor(rs);
        Log.d("list: ", array.toString());
    }

    private void processRequestError(VolleyError error){}

    private void requestToken() {
        Map<String, String> mRequestHeader = new HashMap<String, String>();
        mRequestHeader.put("Content-Type","application/x-www-form-urlencoded");

        Map<String, String> mRequestParams = new HashMap<String, String>();
        mRequestParams.put("name", "student");
        mRequestParams.put("password", "wachtwoord");

        Type type = new TypeToken<List<Token>>(){}.getType();

        GsonRequest<List<Token>> request = new GsonRequest<List<Token>>("http://83.81.251.42/api/login",
                type, mRequestHeader, mRequestParams, new Response.Listener<List<Token>>() {
            @Override
            public void onResponse(List<Token> response) {
                //processRequestSuccess(response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                processRequestError(error);
            }
        });
        VolleyHelper.getInstance(this).addToRequestQueue(request);


        /*
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.POST, "http://83.81.251.42/api/login", new JSONObject(mRequestParams),
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            Log.d("Hallo: ", "" + response);
                            //Here you will receive your response

                        } catch (Exception e) {
                            Log.d("Fout", "" + e);
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                //Do what you want to do on error
            }
        });

        VolleyHelper.getInstance(this).addToRequestQueue(req);
    }*/
    }



}
