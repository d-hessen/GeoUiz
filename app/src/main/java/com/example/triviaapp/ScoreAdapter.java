package com.example.triviaapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreAdapter extends BaseAdapter {

    ArrayList<Score> scores;
    Context context;


    public ScoreAdapter(ArrayList<Score> scores, Context context) {
        this.scores = scores;
        this.context = context;
    }

    @Override
    public int getCount() {
        return scores.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Score score = scores.get(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.score_layout,null);

        TextView tv1 = convertView.findViewById(R.id.tvScoreSubject);
        TextView tv2 = convertView.findViewById(R.id.tvScoreDate);
        TextView tv3 = convertView.findViewById(R.id.tvScoreTime);
        TextView tv4 = convertView.findViewById(R.id.tvScoreScore);


        tv1.setText(score.getSubject());
        tv2.setText(score.getDate());
        tv3.setText(""+score.getTime());
        tv4.setText(""+score.getScore());

        return convertView;
    }
}
