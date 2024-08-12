package com.example.ecorecicla;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.Arrays;

public class BarChart {

    private final Context context;

    public BarChart(Context context){this.context = context;}

    public void bar(double totalSum) {
        com.github.mikephil.charting.charts.BarChart chart = ((Activity) context).findViewById(R.id.chart);
        ArrayList<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(0f, (float)totalSum));
        values.add(new BarEntry(1f, 80f));

        BarDataSet set = new BarDataSet(values, "defectValues");
        set.setValueTextSize(14f);
        set.setColors(Color.BLUE, Color.WHITE);
        set.setValueTextColor(Color.WHITE);
        XAxis xAxis = chart.getXAxis();
        xAxis.setTextColor(Color.WHITE);
        YAxis yAxis = chart.getAxisLeft();
        yAxis.setTextColor(Color.WHITE);
        YAxis yAxis1 = chart.getAxisRight();
        yAxis1.setTextColor(Color.WHITE);
        Legend legend = chart.getLegend();
        legend.setTextColor(Color.WHITE);
        legend.setTextSize(14f);
        legend.setCustom(Arrays.asList(new LegendEntry("Mes Anterior", Legend.LegendForm.SQUARE, 10f, 2f, null, Color.WHITE),
                new LegendEntry("Actual", Legend.LegendForm.SQUARE, 10f, 2f, null, Color.BLUE)));

        ArrayList<IBarDataSet> dates = new ArrayList<>();
        dates.add(set);

        BarData data = new BarData(dates);
        chart.setData(data);
        chart.invalidate(); //refresh
    }
}