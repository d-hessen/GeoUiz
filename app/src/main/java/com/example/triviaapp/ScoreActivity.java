package com.example.triviaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {


    SQLiteDatabase db;

    ArrayList<Score> scoreArrayList;

    ScoreAdapter adapter;

    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        db = openOrCreateDatabase("GeoUiz", Context.MODE_PRIVATE, null);


        // Result(username TEXT, subjCode INT, time INT, date TEXT, result INT)");

        listView=findViewById(R.id.lstScore);

        scoreArrayList= new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM Result WHERE username='"+MainActivity.user_+"'",null);


       // Subject(code INT, name TEXT)");

        while(cursor.moveToNext())
        {
            Cursor cursor1 = db.rawQuery("SELECT * FROM Subject WHERE code = "+cursor.getInt(1) ,null);
            cursor1.moveToFirst();

            scoreArrayList.add(new Score(cursor1.getString(1),cursor.getString(3),cursor.getInt(2),cursor.getInt(4)));
        }

        adapter = new ScoreAdapter(scoreArrayList,this);

        listView.setAdapter(adapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.order_score_layout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int num=item.getItemId();


        if(num==R.id.menuOrderScore)
        {
            scoreArrayList= new ArrayList<>();

            Cursor cursor = db.rawQuery("SELECT * FROM Result WHERE username='"+MainActivity.user_+"' ORDER BY result DESC",null);


            // Subject(code INT, name TEXT)");

            while(cursor.moveToNext())
            {
                Cursor cursor1 = db.rawQuery("SELECT * FROM Subject WHERE code = "+cursor.getInt(1) ,null);
                cursor1.moveToFirst();

                scoreArrayList.add(new Score(cursor1.getString(1),cursor.getString(3),cursor.getInt(2),cursor.getInt(4)));
            }

            adapter = new ScoreAdapter(scoreArrayList,this);

            listView.setAdapter(adapter);

        }
        if(num==R.id.menuOrderDate)
        {
            scoreArrayList= new ArrayList<>();

            Cursor cursor = db.rawQuery("SELECT * FROM Result WHERE username='"+MainActivity.user_+"' ORDER BY date DESC",null);


            // Subject(code INT, name TEXT)");

            while(cursor.moveToNext())
            {
                Cursor cursor1 = db.rawQuery("SELECT * FROM Subject WHERE code = "+cursor.getInt(1) ,null);
                cursor1.moveToFirst();

                scoreArrayList.add(new Score(cursor1.getString(1),cursor.getString(3),cursor.getInt(2),cursor.getInt(4)));
            }

            adapter = new ScoreAdapter(scoreArrayList,this);

            listView.setAdapter(adapter);

        }
        if(num==R.id.menuOrderSubject)
        {
            scoreArrayList= new ArrayList<>();

            Cursor cursor = db.rawQuery("SELECT * FROM Result WHERE username='"+MainActivity.user_+"' ORDER BY subjCode ASC",null);


            // Subject(code INT, name TEXT)");

            while(cursor.moveToNext())
            {
                Cursor cursor1 = db.rawQuery("SELECT * FROM Subject WHERE code = "+cursor.getInt(1) ,null);
                cursor1.moveToFirst();

                scoreArrayList.add(new Score(cursor1.getString(1),cursor.getString(3),cursor.getInt(2),cursor.getInt(4)));
            }

            adapter = new ScoreAdapter(scoreArrayList,this);

            listView.setAdapter(adapter);

        }
        return true;
    }
}