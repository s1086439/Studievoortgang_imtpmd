package com.example.imtpmd1617.studievoortgang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ModuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        TextView courseNameTextView = (TextView) findViewById(R.id.courseName);
        courseNameTextView.setText(getIntent().getStringExtra("module"));
    }
}
