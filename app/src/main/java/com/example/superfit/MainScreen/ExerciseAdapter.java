package com.example.superfit.MainScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.example.superfit.R;

public class ExerciseAdapter extends ArrayAdapter<ExerciseItem> {

    private final int resourceLayout;

    public ExerciseAdapter(Context context, int resource, ArrayList<ExerciseItem> exercises){
        super(context, resource, exercises);
        this.resourceLayout = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;
        ExerciseItem exercise = getItem(position);

        if(v == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            v = layoutInflater.inflate(resourceLayout, null);
        }

        if(exercise != null){
            TextView name = v.findViewById(R.id.ex_name);
            TextView desc = v.findViewById(R.id.ex_desc);
            ImageView image = v.findViewById(R.id.ex_image);

            name.setText(exercise.getName());
            desc.setText(exercise.getDescription());
            image.setImageResource(exercise.getImage());
        }
        return v;
    }
}
