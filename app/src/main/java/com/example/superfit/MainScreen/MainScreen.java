package com.example.superfit.MainScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.superfit.R;
import com.example.superfit.Recipes.Recipes;
import com.example.superfit.SignInActivity;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    ListView listView;
    ExerciseAdapter adapter;
    ArrayList<ExerciseItem> data;

    public String[] exercises = {"Push-Ups", "Plank"};
    public String[] descriptions = {
            "Push-ups exercise the pectoral muscles, triceps, and anterior deltoids.",
            "The plank strengthens the abdominals, back and shoulders.",

    };
    public int[] images = {R.drawable.pu, R.drawable.plank};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        getSupportActionBar().hide();

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        listView = findViewById(R.id.ex_last);
        data = new ArrayList<>();

        for (int i=0;i<exercises.length;i++){
            data.add(new ExerciseItem(exercises[i], descriptions[i], images[i]));
        }
        adapter = new ExerciseAdapter(this, R.layout.exercises_item, data);
        listView.setAdapter(adapter);
    }

    public void onSeeAllClick(View v){
        Intent intent = new Intent(this, AllExercises.class);
        startActivity(intent);
    }

    public void onRecipesClick(View v){
        Intent intent = new Intent(this, Recipes.class);
        startActivity(intent);
    }

    public void onLogOutClick(View v){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
}