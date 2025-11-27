package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraphScoreActivity extends AppCompatActivity {

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;

    ArrayList barEntries;

    ArrayList<Score> scoreArrayList;


    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_score);

        db = openOrCreateDatabase("GeoUiz", Context.MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS Result(username TEXT, subjCode INT, time INT, date TEXT, result INT)");

        barChart = findViewById(R.id.barChart);

        barEntries = new ArrayList();


        scoreArrayList= new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT avg(result) FROM Result WHERE username='"+MainActivity.user_+"' GROUP BY subjCode",null);


        // Subject(code INT, name TEXT)");

        float x;
        x=0;
        while(cursor.moveToNext())
        {

            barEntries.add(new BarEntry(x,cursor.getFloat(0)));
            x++;

        }

        barDataSet = new BarDataSet(barEntries,"Score By Subject");
        barData = new BarData(barDataSet);
        barChart.setData(barData);

        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(25f);

    }
}