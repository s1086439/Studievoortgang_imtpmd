package com.example.imtpmd1617.studievoortgang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private final String PREFS_NAME = "imtpmd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        settings.edit().putBoolean("firstrun", true).commit();

        if (settings.getBoolean("firstrun", true)) {
            setContentView(R.layout.activity_login);

            Button loginButton = (Button) findViewById(R.id.loginButton);

            loginButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(isOnline()) {
                        startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
                        settings.edit().putBoolean("firstrun", false).commit();
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Geen internetverbinding, probeer het later opnieuw.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return true;
        //return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
