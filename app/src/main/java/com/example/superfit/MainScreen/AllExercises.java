package com.example.superfit.MainScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.superfit.Exercises.Crunch;
import com.example.superfit.Exercises.Plank;
import com.example.superfit.Exercises.PushUp;
import com.example.superfit.Exercises.Running;
import com.example.superfit.Exercises.Squats;
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
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_exercises);
        getSupportActionBar().hide();

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        listView = findViewById(R.id.all_ex);
        data = new ArrayList<>();

        for (int i = 0; i < exercises.length; i++) {
            data.add(new ExerciseItem(exercises[i], descriptions[i], images[i]));
        }

        exerciseAdapter = new ExerciseAdapter(this, R.layout.exercises_item, data);
        listView.setAdapter(exerciseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        intent = new Intent(AllExercises.this, PushUp.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(AllExercises.this, Plank.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(AllExercises.this, Squats.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(AllExercises.this, Crunch.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(AllExercises.this, Running.class);
                        startActivity(intent);
                        break;
                }
            }
        });

    }

    public void onBackClick(View v){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }
}