package com.example.imtpmd1617.studievoortgang.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imtpmd1617.studievoortgang.R;

public class ModuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        TextView naamTextView = (TextView) findViewById(R.id.naamText);
        TextView afkortingText = (TextView) findViewById(R.id.afkortingText);
        TextView cijferText = (TextView) findViewById(R.id.cijferText);
        TextView ectText = (TextView) findViewById(R.id.ectText);

        naamTextView.setText(getIntent().getStringExtra("naam"));
        afkortingText.setText(getIntent().getStringExtra("afkorting"));
        cijferText.setText("Cijfer: " + getIntent().getStringExtra("cijfer"));
        ectText.setText(getIntent().getStringExtra("ect"));

        Button inschrijfButton = (Button) findViewById(R.id.inschrijfButton);

        inschrijfButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(ModuleActivity.this, "Inschrijving gesloten", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
