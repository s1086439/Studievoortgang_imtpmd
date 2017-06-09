package com.example.imtpmd1617.studievoortgang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.imtpmd1617.studievoortgang.Adapters.ModulesAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] modules = {"IARCH", "INET", "IOPR1", "IOPR2"};

        ListAdapter modulesAdapter = new ModulesAdapter(this, modules);

        ListView modulesListView = (ListView) findViewById(R.id.modulesListView);
        modulesListView.setAdapter(modulesAdapter);

        modulesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;

                Toast.makeText(getApplicationContext(), "Position :"+itemPosition+"" , Toast.LENGTH_SHORT).show();

            }
        });


    }

}
