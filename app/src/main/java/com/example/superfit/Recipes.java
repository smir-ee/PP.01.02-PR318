package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.ToggleButton;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipes extends AppCompatActivity  {
    ListView view;
    ArrayList<Recipe> list;
    RecipeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        view = findViewById(R.id.list);
        HideActionBarAndTransparentStatusBar.hide(getWindow());
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
            list = ParsingAPI.parse();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter = new RecipeAdapter(getApplicationContext(), list);

                    view.setAdapter(adapter);
                    view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getApplicationContext(), RecipeActivity.class);
                            System.out.println(list.get(position).label);
                            intent.putExtra("Recipe", list.get(position));
                            startActivity(intent);
                        }
                    });
                }
            });
            }
        });

    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
        finish();
    }
}