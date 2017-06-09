package com.example.imtpmd1617.studievoortgang.Fragment;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.imtpmd1617.studievoortgang.Adapters.ModulesAdapter;
import com.example.imtpmd1617.studievoortgang.R;

public class ProfileHoofdfaseModulesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_modules, container, false);
        String[] modules = {"ISEC", "IETH"};

        ListAdapter modulesAdapter = new ModulesAdapter(getActivity(), modules);

        ListView modulesListView = (ListView)view.findViewById(R.id.modulesListView);
        modulesListView.setAdapter(modulesAdapter);

        modulesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
            }
        });

        return view;
    }
}
