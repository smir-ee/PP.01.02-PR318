package com.example.superfit.recipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.example.superfit.R;
import com.example.superfit.recipe.Recipe;
import com.example.superfit.recipe.RecipeActivity;
import com.example.superfit.adapters.RecipeAdapter;
import com.example.superfit.helperClasses.HideActionBarAndTransparentStatusBar;

import java.util.ArrayList;

public class Recipes extends AppCompatActivity  {
    private ListView view;
    private ArrayList<Recipe> list;
    private RecipeAdapter adapter;
    private androidx.appcompat.widget.SearchView searchView;
    private RadioGroup rgDiets;
    private String diet = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        view = findViewById(R.id.list);
        searchView = findViewById(R.id.searchView);
        rgDiets = findViewById(R.id.rgDiets);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        list = ParsingAPI.parse(query, diet);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new RecipeAdapter(getApplicationContext(), list);

                                view.setAdapter(adapter);
                            }
                        });
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        HideActionBarAndTransparentStatusBar.hide(getWindow());
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
            list = ParsingAPI.parse("chicken", diet);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter = new RecipeAdapter(getApplicationContext(), list);

                    view.setAdapter(adapter);
                    view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getApplicationContext(), RecipeActivity.class);
                            intent.putExtra("Recipe", list.get(position));
                            startActivity(intent);
                        }
                    });
                }
            });
            }
        });

    }

    public void onClickRB(View v){

        switch (v.getId()){
                case R.id.rb1:
                    diet = "balanced";
                    break;
                case R.id.rb2:
                    diet = "high-fiber";
                    break;
                case R.id.rb3:
                    diet = "high-protein";
                    break;
        }
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                list = ParsingAPI.parse("chicken", diet);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new RecipeAdapter(getApplicationContext(), list);

                        view.setAdapter(adapter);
                    }
                });
            }
        });
    }

    public void onClickBack(View view) {
        finish();
    }

}