package com.example.triviaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SubjectAdapter extends BaseAdapter {

    ArrayList<String> stringArrayList;
    Context context;

    public SubjectAdapter(ArrayList<String> stringArrayList, Context context) {
        this.stringArrayList = stringArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return stringArrayList.size();
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
        String  st = stringArrayList.get(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.subject_layout,null);
        TextView textView = convertView.findViewById(R.id.tvSubjectName);

        textView.setText(st);
        return convertView;
    }
}
