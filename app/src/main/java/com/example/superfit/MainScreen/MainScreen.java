package com.example.superfit.MainScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.superfit.R;
import com.example.superfit.Recipes.Recipes;

public class MainScreen extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        getSupportActionBar().hide();

        listView = findViewById(R.id.ex_last);
    }

    public void onSeeAllClick(View v){
        Intent intent = new Intent(this, AllExercises.class);
        startActivity(intent);
    }

    public void onRecipesClick(View v){
        Intent intent = new Intent(this, Recipes.class);
        startActivity(intent);
    }
}