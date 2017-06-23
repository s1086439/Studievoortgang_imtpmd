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

        // Eerste bepalen of de waarde True voor "firstRun" in SharedPreferences staat

        if (SharedPrefs.getInstance(LoginActivity.this).getBooleanValue("firstRun") == true) {
            setContentView(R.layout.activity_login);

            studentnummerInput = (EditText)findViewById(R.id.studentnummerInput);
            wachtwoordInput = (EditText)findViewById(R.id.wachtwoordInput);

            Button loginButton = (Button) findViewById(R.id.loginButton);

            loginButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(isOnline()) { // Bapelen of de gebruiker online is, zo ja vraag een token aan
                        requestToken(studentnummerInput.getText().toString(), wachtwoordInput.getText().toString());
                    } else { // Geen internetverbinding
                        Toast.makeText(LoginActivity.this, "Geen internetverbinding. Probeer het later opnieuw.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else { // "FirstRun" in SharedPreferences is False, start gelijk ProfileActivity
            startActivity(new Intent(this, ProfileActivity.class));
            finish();
        }
    }

    // Functie om te kijken of de gebruiker een verbinding met het internet heeft

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    // Functie om de studentgegevens van het JSON-object in de lokale database te plaatsen
    // De verkregen token wordt niet in de database geplaatst, maar wel in SharedPreferences

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

    // Functie om de modules van het JSON-object in de lokale database te plaatsen

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

    // Opvragen token

    private void requestToken(String sn, String w) {
        Map<String, String> mRequestHeader = new HashMap<String, String>(); // Nieuwe HTTP-header map

        // Toevoegen waarden aan HTTP-header
        mRequestHeader.put("Content-Type","application/x-www-form-urlencoded");

        // Toevoegen naam en wachtwoord aan HTTP-header
        Map<String, String> mRequestParams = new HashMap<String, String>();
        mRequestParams.put("name", sn);
        mRequestParams.put("password", w);

        Type type = new TypeToken<List<Student>>(){}.getType();

        // Opvragen studentgegevens eerste keer nadat hij is succesvol binnen is

        GsonRequest<List<Student>> request = new GsonRequest<List<Student>>("http://83.81.251.42/api/login",
                type, mRequestHeader, mRequestParams, new Response.Listener<List<Student>>() {

            @Override
            public void onResponse(List<Student> response) {

                // Zet "FirstRun" op false
                SharedPrefs.getInstance(LoginActivity.this).putBooleanValue("firstRun", false);
                processStudentRequestSuccess(response);
                finish();
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){ // Fout met inloggen bijv. 401
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        // Toevoegen voor HTTP request
        VolleyHelper.getInstance(this).addToRequestQueue(request);
    }

    // Ophalen studieresultaten met behulp van de opgeslagen token in SharedPreferences

    private void requestResultaten() {
        Map<String, String> mRequestHeader = new HashMap<String, String>();

        // Toevoegen waarden header
        mRequestHeader.put("Content-Type","application/x-www-form-urlencoded");
        mRequestHeader.put("Authorization", "Bearer " + SharedPrefs.getInstance(LoginActivity.this).getStringValue("token"));
        mRequestHeader.put("Accept","application/json");

        Map<String, String> mRequestParams = new HashMap<String, String>();

        Type type = new TypeToken<List<Module>>(){}.getType();

        // Request om JSON-bestand op te halen
        GsonRequest<List<Module>> request = new GsonRequest<List<Module>>("http://83.81.251.42/api/resultaten",
                type, mRequestHeader, mRequestParams, new Response.Listener<List<Module>>() {

            @Override
            public void onResponse(List<Module> response) {
                processResultatenRequestSuccess(response);
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){ // Fout met het ophalen van het JSON-bestand, laat het zien in een toast
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        // Toevoegen voor HTTP-request
        VolleyHelper.getInstance(this).addToRequestQueue(request);
    }
}
