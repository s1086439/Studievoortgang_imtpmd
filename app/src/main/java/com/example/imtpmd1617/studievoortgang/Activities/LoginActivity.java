package com.example.imtpmd1617.studievoortgang.Activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.imtpmd1617.studievoortgang.DatabaseHelper;
import com.example.imtpmd1617.studievoortgang.DatabaseInfo;
import com.example.imtpmd1617.studievoortgang.GsonRequest;
import com.example.imtpmd1617.studievoortgang.Models.Module;
import com.example.imtpmd1617.studievoortgang.Models.Student;
import com.example.imtpmd1617.studievoortgang.R;
import com.example.imtpmd1617.studievoortgang.SharedPrefs;
import com.example.imtpmd1617.studievoortgang.VolleyHelper;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.imtpmd1617.studievoortgang.DatabaseHelper.getHelper;

public class LoginActivity extends AppCompatActivity {

    private final String PREFS_NAME = "imtpmd";
    private DatabaseHelper dbHelper;
    private String studentnummer, wachtwoord;
    private EditText studentnummerInput, wachtwoordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        dbHelper = DatabaseHelper.getHelper(this);

        SharedPrefs.getInstance(LoginActivity.this).clearAll();

        //SharedPrefs.getInstance(LoginActivity.this).putBooleanValue("firstRun", false);

        if (SharedPrefs.getInstance(LoginActivity.this).getBooleanValue("firstRun") == true) {
            getHelper(this);
            setContentView(R.layout.activity_login);

            studentnummerInput = (EditText)findViewById(R.id.studentnummerInput);
            wachtwoordInput = (EditText)findViewById(R.id.wachtwoordInput);

            Button loginButton = (Button) findViewById(R.id.loginButton);

            loginButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(isOnline()) {
                        requestToken(studentnummerInput.getText().toString(), wachtwoordInput.getText().toString());
                        //requestToken("1081231", "wachtwoord");
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
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void processStudentRequestSuccess(List<Student> subjects){
        for(Student s : subjects){
            SharedPrefs.getInstance(LoginActivity.this).putStringValue("token", s.token);
            ContentValues cv = new ContentValues();
            cv.put(DatabaseInfo.StudentColumn.STUDENTID, s.studentId);
            cv.put(DatabaseInfo.StudentColumn.VOORNAAM, s.voornaam);
            cv.put(DatabaseInfo.StudentColumn.ACHTERNAAM, s.achternaam);
            cv.put(DatabaseInfo.StudentColumn.STUDIERICHTING, s.studierichting);
            dbHelper.insert(DatabaseInfo.Tables.STUDENTEN, null, cv);
        }
        requestResultaten();
    }

    private void processResultatenRequestSuccess(List<Module> subjects){
        for(Module m : subjects){
            ContentValues cv = new ContentValues();
            cv.put(DatabaseInfo.ModulesColumn.NAAM, m.module_naam);
            cv.put(DatabaseInfo.ModulesColumn.AFKORTING, m.module_afkorting);
            cv.put(DatabaseInfo.ModulesColumn.ECT, m.ect);
            cv.put(DatabaseInfo.ModulesColumn.CIJFER, m.cijfer);
            cv.put(DatabaseInfo.ModulesColumn.PERIODE, m.periode);
            cv.put(DatabaseInfo.ModulesColumn.FASE, m.fase);
            dbHelper.insert(DatabaseInfo.Tables.MODULES, null, cv);
        }
        startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
        finish();
    }

    private void requestToken(String sn, String w) {
        Map<String, String> mRequestHeader = new HashMap<String, String>();
        mRequestHeader.put("Content-Type","application/x-www-form-urlencoded");

        Map<String, String> mRequestParams = new HashMap<String, String>();
        mRequestParams.put("name", sn);
        mRequestParams.put("password", w);

        Type type = new TypeToken<List<Student>>(){}.getType();

        GsonRequest<List<Student>> request = new GsonRequest<List<Student>>("http://83.81.251.42/api/login",
                type, mRequestHeader, mRequestParams, new Response.Listener<List<Student>>() {

            @Override
            public void onResponse(List<Student> response) {
                SharedPrefs.getInstance(LoginActivity.this).putBooleanValue("firstRun", false);
                processStudentRequestSuccess(response);
                finish();
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        VolleyHelper.getInstance(this).addToRequestQueue(request);
    }

    private void requestResultaten() {
        Map<String, String> mRequestHeader = new HashMap<String, String>();
        mRequestHeader.put("Content-Type","application/x-www-form-urlencoded");
        mRequestHeader.put("Authorization", "Bearer " + SharedPrefs.getInstance(LoginActivity.this).getStringValue("token"));
        //mRequestHeader.put("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjI1ZjMzYTkzZmJlMGFkMGU4MDQzNzU2MTdjNjE2ZTVkNWJlNmFhMDhlMzYyMGY1NWFjZjIxMWViOTY1ZDcxNTBhM2JlNGVmZjZkYjU1NThkIn0.eyJhdWQiOiIzIiwianRpIjoiMjVmMzNhOTNmYmUwYWQwZTgwNDM3NTYxN2M2MTZlNWQ1YmU2YWEwOGUzNjIwZjU1YWNmMjExZWI5NjVkNzE1MGEzYmU0ZWZmNmRiNTU1OGQiLCJpYXQiOjE0OTc3MzU4MDEsIm5iZiI6MTQ5NzczNTgwMSwiZXhwIjoxNTI5MjcxODAxLCJzdWIiOiIyIiwic2NvcGVzIjpbXX0.KqPsNW5ABtILT0UFgUDgOqE_P-DHl-4mk-Wnq7HHZ1_nLWzbJ2sM8eg4DbQfFz8nsoWBiXUOei2zN1IU4rAUuciC729Xs8LURXkU3LZXFERZgbH-QqWZxLJWKKj8UwJUWH7pqH90Wzntz9UTZjIo6q68zrzWg9WZaF0Qzhj3lSp33crNgROed7SYI02MhfpntFhBBnEaCj9xslQBT4G3N5N24OYmwsjQAJW7lEyUqEj8tsIKKEwrjfLWKykj3NV8jvGilXcG1B96Hzv5QLRSgh2edD9NJdzZy0iLxPTd_bx0PVWnD27EvqPi48QGXOoBEQ8VmWNCgeDw_RnUEs8n0o6tuY1_6FW9BM2lUf1ViRMuf9cDlIFpvEiAzrhQYYy-KGa0ArlwaGdzKiXlF3ivSprDceC_7U4qcdMEoUSxHpdFQI3mk_72jZSYaZ8PtKLYi08PhOL3N7mb_MHSYqCbQf6N8_85J7eACYaPKDCJMDS3s9z9iedeyU7qm4HrxaAJ5DKyIXYYXTH--itIK7ihdKtpygVq82qzYL9GXJFbTSpnKKlBFRcEJrACh19x1Y-xQ8YpKK7K91tMSsjahz0kuMC7bQjoyuiydzP09TIE-N_aVFRMaSXHLIl9aCxV1nNuV9kIjD7uN0JjHW_uEEfJo_i3A7PTGOL0OOIe7qOgF5E");
        mRequestHeader.put("Accept","application/json");

        Map<String, String> mRequestParams = new HashMap<String, String>();

        Type type = new TypeToken<List<Module>>(){}.getType();

        GsonRequest<List<Module>> request = new GsonRequest<List<Module>>("http://83.81.251.42/api/resultaten",
                type, mRequestHeader, mRequestParams, new Response.Listener<List<Module>>() {

            @Override
            public void onResponse(List<Module> response) {
                processResultatenRequestSuccess(response);
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        VolleyHelper.getInstance(this).addToRequestQueue(request);
    }
}
