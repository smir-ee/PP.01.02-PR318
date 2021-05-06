package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import java.io.Serializable;
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
//        rgDiets.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                System.out.println(checkedId);
//                switch (checkedId){
//                    case 1:
//                        diet = "Balanced";
//                        break;
//                    case 2:
//                        diet = "High-Fiber";
//                        break;
//                    case 3:
//                        diet = "High-Protein";
//                        break;
//                }
//                AsyncTask.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        list = ParsingAPI.parse("chicken", diet);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                adapter = new RecipeAdapter(getApplicationContext(), list);
//
//                                view.setAdapter(adapter);
//                            }
//                        });
//                    }
//                });
//
//            }
//        });

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