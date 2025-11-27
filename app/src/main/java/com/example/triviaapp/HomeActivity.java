package com.example.triviaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    SQLiteDatabase db;

    ListView listView;

    ArrayList<String> list;

    SubjectAdapter  adapter;

    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        db = openOrCreateDatabase("GeoUiz", Context.MODE_PRIVATE, null);

        list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM Subject",null);

        while(cursor.moveToNext())
            list.add(cursor.getString(1));

        adapter= new SubjectAdapter(list,this);

        listView = findViewById(R.id.lstSubject);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String st = list.get((position));
                    Intent intent = new Intent(HomeActivity.this, GameActivity.class);
                    intent.putExtra("subj", st);
                    startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_score,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.menuScore)
        {
            Intent intent = new Intent(this,ScoreActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.menuGraphScore)
        {
            Intent intent = new Intent(this,GraphScoreActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}