package com.example.imtpmd1617.studievoortgang.Fragments;

import android.database.Cursor;
import android.graphics.Color;
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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private TextView naamText, studentnummerText, studierichtingText;
    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_info, container, false);

        dbHelper = DatabaseHelper.getHelper(getContext());

        LineChart chart = (LineChart) view.findViewById(R.id.chart);

        Integer[] yAxis = {1,2,3,4};
        Integer[] xAxis = {1,20,30,40};

        List<Entry> entries = new ArrayList<Entry>();

        for(int i = 0; i < xAxis.length; i++){
            entries.add(new Entry(yAxis[i], xAxis[i]));
        }

        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);

        chart.getAxisRight().setEnabled(false);
        chart.setTouchEnabled(false);

        LineDataSet dataSet = new LineDataSet(entries, "Studiepunten (ect)");

        LineData lineData = new LineData(dataSet);

        chart.getAxisLeft().setAxisMinimum(0);
        chart.getAxisLeft().setAxisMaximum(60);

        //chart.getXAxis().setAxisMinimum(1);
        //chart.getXAxis().setAxisMaximum(4);

        chart.getAxisLeft().setSpaceTop(0);
        chart.getAxisLeft().setSpaceBottom(0);

        chart.setDescription(null);
        chart.getLegend().setEnabled(false);

        dataSet.setColor(Color.parseColor("#21174c"));
        dataSet.setCircleColor(Color.parseColor("#21174c"));
        dataSet.setLineWidth(3f);
        dataSet.setCircleRadius(4f);

        chart.setData(lineData);
        chart.invalidate();


        //queryDb("STUDENTEN");
        naamText = (TextView) view.findViewById(R.id.naamText);
        studentnummerText = (TextView) view.findViewById(R.id.studentnummerText);
        studierichtingText = (TextView) view.findViewById(R.id.studierichtingText);

        //naamText.setText("Naam: " + queryDb("STUDENTEN"));

        /*
        for(ArrayList s : dbHelper.queryDb("STUDENTEN", null, null, null, null, null, null)){
            naamText.setText("Naam: " + s.get(1) + " " + s.get(2));
            studentnummerText.setText("Studentnummer: " + s.get(0));
            studierichtingText.setText("Studiericthing: " + s.get(3));
        }*/

        return view;
    }

}
