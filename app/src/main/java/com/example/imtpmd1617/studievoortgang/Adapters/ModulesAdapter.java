package com.example.imtpmd1617.studievoortgang.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.imtpmd1617.studievoortgang.Activities.ModuleActivity;
import com.example.imtpmd1617.studievoortgang.Models.Module;
import com.example.imtpmd1617.studievoortgang.R;

import java.util.ArrayList;

public class ModulesAdapter extends ArrayAdapter<Module> {

    public ModulesAdapter(Context context, ArrayList<Module> modules) {
        super(context, R.layout.listrow_module, modules);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater modulesInflater = LayoutInflater.from(getContext());
            convertView = modulesInflater.inflate(R.layout.listrow_module, parent, false);
            Module module = getItem(position);
            LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.linearLayout);
            TextView afkortingText = (TextView) convertView.findViewById(R.id.afkortingText);
            TextView cijferText = (TextView) convertView.findViewById(R.id.cijferText);

            afkortingText.setText(module.getModule_afkorting());
            if(module.getCijfer() > 0 || module.getCijfer() > 10){
                cijferText.setText(String.valueOf(module.getCijfer()));
            } else {
                cijferText.setText("-");
            }

            if(module.getCijfer() >= 5.5){
                linearLayout.setBackgroundColor(Color.parseColor("#d7ffb2"));
            }
            if(module.getCijfer() > 0 && module.getCijfer() < 5.5){
                linearLayout.setBackgroundColor(Color.parseColor("#ffbaba"));
            } else {
                //linearLayout.setBackgroundColor(0x00000000);
            }

            /*
            convertView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ModuleActivity.class);
                    getContext().startActivity(intent);
                }
            });*/
        }
        return convertView;
    }
}
