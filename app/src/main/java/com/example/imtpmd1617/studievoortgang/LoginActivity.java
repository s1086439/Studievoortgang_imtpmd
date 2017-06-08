package com.example.imtpmd1617.studievoortgang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private final String PREFS_NAME = "imtpmd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getBoolean("firstrun", true)) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            Button loginButton = (Button) findViewById(R.id.loginButton);

            loginButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            });
        } else {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }
}
