package com.example.imtpmd1617.studievoortgang.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.imtpmd1617.studievoortgang.R;

public class ModuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        TextView courseNameTextView = (TextView) findViewById(R.id.courseName);
        courseNameTextView.setText(getIntent().getStringExtra("module"));
    }
}
