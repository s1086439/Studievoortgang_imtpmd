package com.example.imtpmd1617.studievoortgang.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imtpmd1617.studievoortgang.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;


/**
 * Created by jimmyvanviersen on 16/06/2017.
 */

public class GraphFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph, container, false);

        // Retrieve view for chart, If it is in a fragment, then use this in onCreateView() method
        BarChart chart = (BarChart) view.findViewById(R.id.bar_chart);

        // Adding data
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(2f, 0));
        entries.add(new BarEntry(4f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(8f, 3));
        entries.add(new BarEntry(7f, 4));
        entries.add(new BarEntry(3f, 5));

        BarDataSet set = new BarDataSet(entries, "BarDataSet");

        // Create dataset
        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width
        chart.setData(data);
        chart.setFitBars(true); // make the X-axis fit exactly all bars
        chart.invalidate(); // refresh

        return view;
    }
}
