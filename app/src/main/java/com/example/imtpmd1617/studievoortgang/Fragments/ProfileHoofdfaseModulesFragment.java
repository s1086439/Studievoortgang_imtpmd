package com.example.imtpmd1617.studievoortgang.Fragments;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.imtpmd1617.studievoortgang.Activities.ModuleActivity;
import com.example.imtpmd1617.studievoortgang.Adapters.ModulesAdapter;
import com.example.imtpmd1617.studievoortgang.DatabaseHelper;
import com.example.imtpmd1617.studievoortgang.Models.Module;
import com.example.imtpmd1617.studievoortgang.R;

import java.util.ArrayList;

import static com.example.imtpmd1617.studievoortgang.DatabaseHelper.getHelper;
import static com.example.imtpmd1617.studievoortgang.R.id.modulesListView;

public class ProfileHoofdfaseModulesFragment extends Fragment implements View.OnClickListener {

    // Klasse had beter over andere klasse kunnen implementeren i.v.m. dubbele code

    private ArrayList<Module> modules;
    private DatabaseHelper dbHelper;
    private ListAdapter modulesAdapter;
    private ListView modulesListView;
    private int periode, fase;
    private TextView periodeEnFaseText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_modules, container, false);

        this.modules = new ArrayList<>();

        dbHelper = getHelper(getActivity());

        fase = 2;

        setHasOptionsMenu(true);

        Button periode1Button = (Button) view.findViewById(R.id.periode1Button);
        Button periode2Button = (Button) view.findViewById(R.id.periode2Button);
        Button periode3Button = (Button) view.findViewById(R.id.periode3Button);
        Button periode4Button = (Button) view.findViewById(R.id.periode4Button);

        periode1Button.setOnClickListener(this);
        periode2Button.setOnClickListener(this);
        periode3Button.setOnClickListener(this);
        periode4Button.setOnClickListener(this);

        //changeModules(1, 1);

        //ListAdapter modulesAdapter = new ModulesAdapter(getActivity(), modules);

        periodeEnFaseText = (TextView) view.findViewById(R.id.periodeEnFaseText);


        modulesAdapter = new ModulesAdapter(getActivity(), modules);

        modulesListView = (ListView)view.findViewById(R.id.modulesListView);
        modulesListView.setAdapter(modulesAdapter);

        /* ClickListener voor individuele listrows om zo een ModuleActivity op te starten.
            Geeft extra waarden aan de activity mee door putExtra.
            Verkrijgt deze waarden door de positie van de listrow (module) te bepalen van de klik
         */

        modulesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ModuleActivity.class);
                intent.putExtra("naam", modules.get(position).getModule_naam());
                intent.putExtra("afkorting", modules.get(position).getModule_afkorting());
                intent.putExtra("cijfer", String.valueOf((modules.get(position).getCijfer())));
                intent.putExtra("ect", modules.get(position).getEct());
                intent.putExtra("periode", modules.get(position).getPeriode());
                startActivity(intent);
            }
        });

        changeModules(1, 2);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //menu.clear();
        inflater.inflate(R.menu.menu_profile_hoofdfase, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    // Opvangen klik op de knoppen van de periodes en daarna de methode changeModules oproepen

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.periode1Button:
                periode = 1;
                changeModules(1, fase);
                break;
            case R.id.periode2Button:
                periode = 2;
                changeModules(2, fase);
                break;
            case R.id.periode3Button:
                periode = 3;
                changeModules(3, fase);
                break;
            case R.id.periode4Button:
                periode = 4;
                changeModules(4, fase);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.modules.clear();
        switch (item.getItemId()) {
            case R.id.action_jaar_2:
                fase = 2;
                changeModules(periode, 2);
                return true;
            case R.id.action_jaar_3:
                fase = 3;
                changeModules(periode, 3);
                return true;
            case R.id.action_jaar_4:
                fase = 4;
                changeModules(periode, 4);
                return true;
        }
        return false;
    }

    private void changeModules(int periode, int fase){
        this.modules.clear();

        for(Module m : dbHelper.querySqliteModules("SELECT  * FROM MODULES WHERE periode=" + periode + " AND fase=" + fase)){
            this.modules.add(m);
        }
        Log.d("Modules: ", "" + this.modules);
        periodeEnFaseText.setText("Periode " + periode +"," + " Jaar " + fase);
        ((BaseAdapter)modulesAdapter).notifyDataSetChanged();
        modulesListView.setAdapter(modulesAdapter);
    }

}
