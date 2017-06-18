package com.example.imtpmd1617.studievoortgang;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

/**
 * Created by jimmyvanviersen on 18/06/2017.
 */

public class GraphActivity extends AppCompatActivity {

    private PieChart mChart;
    public static final int MAX_ECTS_PROP = 60;
    public static final int MAX_ECTS_BACH = 120;
    public static int currentEctsProp = 0;
    public static int currentEctsBach = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        mChart = (PieChart) findViewById(R.id.chart);
        mChart.setDescription("Overzicht");
        mChart.setTouchEnabled(false);
        mChart.setDrawSliceText(true);
        mChart.getLegend().setEnabled(false);
        mChart.setTransparentCircleColor(Color.rgb(130, 130, 130));
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        setData(0);

        Button button = (Button) findViewById(R.id.plusTweeTest);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            { if (currentEctsProp < MAX_ECTS_PROP) {
                setData(currentEctsProp += 2);
            } else {
                setData(currentEctsProp = 0);
                }
            }
        });
    }

    private void setData(int aantal) {
        currentEctsProp = aantal;
        ArrayList<Entry> yValues = new ArrayList<>();
        ArrayList<String> xValues = new ArrayList<>();
        yValues.add(new Entry(aantal, 0));
        xValues.add("Behaalde ECTS Propedeuse");
        yValues.add(new Entry(60 - currentEctsProp, 1));
        xValues.add("Resterende ECTS Propedeuse");
//        yValues.add(new Entry(aantal, 0));
//        xValues.add("Behaalde ECTS Bachelor");
//        yValues.add(new Entry(120 - currentEctsBach, 1));
//        xValues.add("Restrerende ECTS Bachelor");

        // http://www.materialui.co/colors
        ArrayList<Integer> colors = new ArrayList<>();
        if (currentEctsProp <10) {
            colors.add(Color.rgb(244,81,30));
        } else if (currentEctsProp < 40){
            colors.add(Color.rgb(235,0,0));
        } else if (currentEctsProp < 50) {
            colors.add(Color.rgb(253,216,53));
        } else {
            colors.add(Color.rgb(67,160,71));
        }

        colors.add(Color.rgb(255,0,0));
        PieDataSet dataSet = new PieDataSet(yValues, "ECTS");
        dataSet.setColors(colors);
        PieData data = new PieData(xValues, dataSet); mChart.setData(data);
        // bind dataset aan chart. mChart.invalidate();
    }
}
