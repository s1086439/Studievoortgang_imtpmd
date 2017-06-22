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
import com.example.imtpmd1617.studievoortgang.Models.Module;
import com.example.imtpmd1617.studievoortgang.Models.Student;
import com.example.imtpmd1617.studievoortgang.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private TextView naamText, studentnummerText, studierichtingText;
    private DatabaseHelper dbHelper;
    private List entries;
    private GraphView graph;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile_info, container, false);

        dbHelper = DatabaseHelper.getHelper(getContext());
        naamText = (TextView) view.findViewById(R.id.naamText);
        studentnummerText = (TextView) view.findViewById(R.id.studentnummerText);
        studierichtingText = (TextView) view.findViewById(R.id.studierichtingText);
        setupGraph();
        fillTextEntries();
        return view;
    }

    private void setupGraph(){
        graph = (GraphView) view.findViewById(R.id.studiepuntenGraph);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(4);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(60);
        LineGraphSeries<DataPoint> seriesPropedeuse = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 0),
                new DataPoint(1, 10),
                new DataPoint(2, 23)
        });

        LineGraphSeries<DataPoint> seriesHoofdfase = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 0)
        });

        seriesPropedeuse.setColor(R.attr.colorPrimary);
        seriesPropedeuse.setDrawDataPoints(true);
        seriesPropedeuse.setDataPointsRadius(10);

        seriesHoofdfase.setColor(Color.parseColor("#cdc2f9"));
        seriesHoofdfase.setDrawDataPoints(true);
        seriesHoofdfase.setDataPointsRadius(10);

        seriesPropedeuse.setTitle("Studiepunten propedeuse");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.BOTTOM);
        graph.getLegendRenderer().setBackgroundColor(Color.TRANSPARENT);
        graph.addSeries(seriesPropedeuse);

        seriesHoofdfase.setTitle("Studiepunten hoofdfase");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.BOTTOM);
        graph.getLegendRenderer().setBackgroundColor(Color.TRANSPARENT);
        graph.addSeries(seriesHoofdfase);
    }

    public void fillTextEntries(){
        for(Student s : dbHelper.querySqliteStudent("SELECT  * FROM STUDENTEN")){
            naamText.setText("Naam: " + s.getVoornaam() + " " + s.getAchternaam());
            studentnummerText.setText("Studentnummer: " + String.valueOf(s.getStudentId()));
            studierichtingText.setText("Studiericthing: " + s.getStudierichting());
        }
    }

}
