package com.example.imtpmd1617.studievoortgang.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.imtpmd1617.studievoortgang.Adapters.ModulesAdapter;
import com.example.imtpmd1617.studievoortgang.Activities.ModuleActivity;
import com.example.imtpmd1617.studievoortgang.DatabaseHelper;
import com.example.imtpmd1617.studievoortgang.Models.Module;
import com.example.imtpmd1617.studievoortgang.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProfilePropedeuseModulesFragment extends Fragment {

    ArrayList<String> modules;
    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_modules, container, false);
        //this.modules = new String[] {"IARCH", "INET", "IOPR1", "IOPR2"};
        this.modules = new ArrayList<String>();

        dbHelper = DatabaseHelper.getHelper(getContext());

        for(ArrayList m : dbHelper.queryDb("MODULES", null, null, null, null, null, null)){
            //this.modules.add(m.get(2).toString() + " - "+ m.get(3).toString());
        }

        ListAdapter modulesAdapter = new ModulesAdapter(getActivity(), modules);

        ListView modulesListView = (ListView)view.findViewById(R.id.modulesListView);
        modulesListView.setAdapter(modulesAdapter);

        modulesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ModuleActivity.class);
                intent.putExtra("module", modules.get(position));
                startActivity(intent);
            }
        });

        return view;
    }
}
