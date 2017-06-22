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

public class ProfileKeuzeModulesFragment extends Fragment implements View.OnClickListener {

    private ArrayList<Module> modules;
    private DatabaseHelper dbHelper;
    private ListAdapter modulesAdapter;
    private ListView modulesListView;
    private TextView periodeEnFaseText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_modules, container, false);

        this.modules = new ArrayList<>();

        dbHelper = getHelper(getActivity());

        //ListAdapter modulesAdapter = new ModulesAdapter(getActivity(), modules);

        modulesAdapter = new ModulesAdapter(getActivity(), modules);

        modulesListView = (ListView)view.findViewById(R.id.modulesListView);
        modulesListView.setAdapter(modulesAdapter);

        Button periode1Button = (Button) view.findViewById(R.id.periode1Button);
        Button periode2Button = (Button) view.findViewById(R.id.periode2Button);
        Button periode3Button = (Button) view.findViewById(R.id.periode3Button);
        Button periode4Button = (Button) view.findViewById(R.id.periode4Button);

        periode1Button.setOnClickListener(this);
        periode2Button.setOnClickListener(this);
        periode3Button.setOnClickListener(this);
        periode4Button.setOnClickListener(this);


        periodeEnFaseText = (TextView) view.findViewById(R.id.periodeEnFaseText);



        //changeModules(1);


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

        changeModules(1);

        return view;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.periode1Button:
                changeModules(1);
                break;
            case R.id.periode2Button:
                changeModules(2);
                break;
            case R.id.periode3Button:
                changeModules(3);
                break;
            case R.id.periode4Button:
                changeModules(4);
                break;
        }
    }

    private void changeModules(int periode){
        this.modules.clear();

        for(Module m : dbHelper.querySqliteModules("SELECT  * FROM MODULES WHERE periode=" + periode + " AND fase=0")){
            this.modules.add(m);
        }
        Log.d("Modules: ", "" + this.modules);
        periodeEnFaseText.setText("Periode " + periode);
        ((BaseAdapter)modulesAdapter).notifyDataSetChanged();
        modulesListView.setAdapter(modulesAdapter);
    }

}
