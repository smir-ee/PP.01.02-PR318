package com.example.superfit.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.superfit.R;
import com.example.superfit.exercises.Exercise;

import java.util.ArrayList;

public class ExerciseAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Exercise> exercises = new ArrayList<>();

    public ExerciseAdapter(Context mContext, ArrayList<Exercise> exercises) {
        this.mContext = mContext;
        this.exercises = exercises;
    }

    @Override
    public int getCount() {
        return exercises.size();
    }

    @Override
    public Object getItem(int position) {
        return exercises.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = LayoutInflater.from(mContext).inflate(R.layout.exercise_row, parent, false);
        Exercise exercise = exercises.get(position);
        ImageView image = view.findViewById(R.id.image);
        TextView label = view.findViewById(R.id.label);
        TextView text = view.findViewById(R.id.text);

        image.setImageResource(exercise.getImage());
        label.setText(exercise.getLabel());
        text.setText(exercise.getText());

        return view;
    }
}
