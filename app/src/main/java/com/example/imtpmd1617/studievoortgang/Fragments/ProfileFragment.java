package com.example.imtpmd1617.studievoortgang.Fragments;

import android.database.Cursor;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.imtpmd1617.studievoortgang.DatabaseHelper;
import com.example.imtpmd1617.studievoortgang.R;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private TextView naamText, studentnummerText, studierichtingText;
    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_info, container, false);

        dbHelper = DatabaseHelper.getHelper(getContext());

        //queryDb("STUDENTEN");
        naamText = (TextView) view.findViewById(R.id.naamText);
        studentnummerText = (TextView) view.findViewById(R.id.studentnummerText);
        studierichtingText = (TextView) view.findViewById(R.id.studierichtingText);

        //naamText.setText("Naam: " + queryDb("STUDENTEN"));

        for(ArrayList s : dbHelper.queryDb("STUDENTEN", null, null, null, null, null, null)){
            naamText.setText("Naam: " + s.get(1) + " " + s.get(2));
            studentnummerText.setText("Studentnummer: " + s.get(0));
            studierichtingText.setText("Studiericthing: " + s.get(3));
        }

        return view;
    }

}
