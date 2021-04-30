package com.example.superfit.MainScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.superfit.R;

import java.util.ArrayList;

public class AllExercises extends AppCompatActivity {

    public String[] exercises = {"Push-Ups", "Plank", "Squats", "Crunch", "Running"};
    public String[] descriptions = {
            "Push-ups exercise the pectoral muscles, triceps, and anterior deltoids.",
            "The plank strengthens the abdominals, back and shoulders.",
            "Considered a vital exercise for increasing the strength and size of the lower body.",
            "It involves the entire abs, but primarily it works the rectus abdominis muscle.",
            "It develops endurance, strengthens the legs and the cardiovascular system."
    };
    public int[] images = {R.drawable.pu, R.drawable.plank, R.drawable.pu, R.drawable.plank, R.drawable.pu};

    ExerciseAdapter exerciseAdapter;
    ArrayList<ExerciseItem> data;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_exercises);
        getSupportActionBar().hide();

        listView = findViewById(R.id.all_ex);
        data = new ArrayList<>();

        for (int i = 0; i < exercises.length; i++) {
            data.add(new ExerciseItem(exercises[i], descriptions[i], images[i]));
        }


        exerciseAdapter = new ExerciseAdapter(this, R.layout.exercises_item, data);
        listView.setAdapter(exerciseAdapter);
    }

    public void onBackClick(View v){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }
}