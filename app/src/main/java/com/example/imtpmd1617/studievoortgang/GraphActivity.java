package com.example.imtpmd1617.studievoortgang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

/**
 * Created by jimmyvanviersen on 18/06/2017.
 */

public class GraphActivity extends AppCompatActivity {

    private BarChart mChart;
    public static final int MAX_ECTS_PROP = 60;
    public static final int MAX_ECTS_BACH = 120;
    public static int currentEctsProp = 0;
    public static int currentEctsBach = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        // aanmaken graph object, koppelen aan de activity_graph.xml
        BarChart graph = (BarChart) findViewById(R.id.chart);
        graph.setDescription("Propedeuse");
        graph.animateY(1500);

        // Nieuwe BarData aanmaken en vullen met entries
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            barEntries.add(new BarEntry((float) Math.random() * 15, i));
        }
        BarDataSet set = new BarDataSet(barEntries, "Bar DataSet");

        ArrayList<String> labels = new ArrayList<>();
            labels.add("x1");
            labels.add("x2");
            labels.add("x3");
            labels.add("x4");
            labels.add("x5");
            labels.add("x6");
            labels.add("x7");
            labels.add("x8");
            labels.add("x9");
            labels.add("x10");

        BarData data = new BarData(labels, set);
        graph.setData(data);
    }
}
