package com.example.imtpmd1617.studievoortgang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.imtpmd1617.studievoortgang.Adapters.ModulesAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] modules = {"test", "test"};

        ListAdapter modulesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, modules);
        ListView modulesListView = (ListView) findViewById(R.id.modulesListView);
        modulesListView.setAdapter(modulesAdapter);

    }
}
