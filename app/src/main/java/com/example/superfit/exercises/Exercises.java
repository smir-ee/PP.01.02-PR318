package com.example.superfit.exercises;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.superfit.R;
import com.example.superfit.adapters.ExerciseAdapter;
import com.example.superfit.helperClasses.HideActionBarAndTransparentStatusBar;

import java.util.ArrayList;


public class Exercises extends AppCompatActivity {
    private ExerciseAdapter exerciseAdapter;
    private ArrayList<Exercise> exercises = new ArrayList<>();
    private ListView lvExercises;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        lvExercises = findViewById(R.id.exercises);

        exercises.add(new Exercise(R.drawable.push_ups, "Push-Ups", "Push-ups exercise the pectoral muscles, triceps, and anterior deltoids."));
        exercises.add(new Exercise(R.drawable.plank, "Plank", "The plank strengthens the abdominals, back and shoulders."));
        exercises.add(new Exercise(R.drawable.squats, "Squats", "Сonsidered a vital exercise for increasing the strength and size of the lower body."));
        exercises.add(new Exercise(R.drawable.crunch, "Crunch", "It involves the entire abs, but primarily it works the rectus abdominis muscle."));
        exercises.add(new Exercise(R.drawable.running, "Running", "It develops endurance, strengthens the legs and the cardiovascular system."));
        exerciseAdapter = new ExerciseAdapter(this, exercises);

        lvExercises.setAdapter(exerciseAdapter);

        HideActionBarAndTransparentStatusBar.hide(getWindow());
    }

    public void onClickBack(View view) {
        finish();
    }
}