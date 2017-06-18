package com.example.imtpmd1617.studievoortgang.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.imtpmd1617.studievoortgang.Models.Module;
import com.example.imtpmd1617.studievoortgang.R;

import java.util.ArrayList;

public class ModulesAdapter extends ArrayAdapter<String> {

    public ModulesAdapter(Context context, ArrayList<String> modules) {
        super(context, R.layout.listrow_module, modules);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater modulesInflater = LayoutInflater.from(getContext());
            convertView = modulesInflater.inflate(R.layout.listrow_module, parent, false);

            String module = getItem(position);
            TextView moduleText = (TextView) convertView.findViewById(R.id.moduleText);

            moduleText.setText(position);

            moduleText.setBackgroundColor(Color.parseColor("#d7ffb2"));
        }
        return convertView;
    }
}
