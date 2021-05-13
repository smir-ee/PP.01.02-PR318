package com.example.superfit.mainScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import com.example.superfit.MyBodyActivity;
import com.example.superfit.exercises.Exercises;
import com.example.superfit.helperClasses.HideActionBarAndTransparentStatusBar;
import com.example.superfit.R;
import com.example.superfit.recipes.Recipes;
import com.example.superfit.loadScreens.MainActivity;
import com.google.gson.Gson;

public class MainScreen extends AppCompatActivity {
    String userName;
    SharedPreferences sPrefs;
    User user;
    TextView weight, height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        HideActionBarAndTransparentStatusBar.hide(getWindow());
        sPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        userName = sPrefs.getString("lastUser", "NoOne");

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        Gson gson = new Gson();
        String json = sPrefs.getString(userName, "Fail");
        user = gson.fromJson(json, User.class);
        weight.setText(user.getWeight());
        height.setText(user.getHeight());
    }

    public void onClickRecipes(View v){
        Intent intent = new Intent(this, Recipes.class);
        startActivity(intent);
    }

    public void onClickSignOut(View v){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickExercises(View v){
        Intent intent = new Intent(this, Exercises.class);
        startActivity(intent);
    }
    public void onClickDetails(View v){
        Intent intent = new Intent(this, MyBodyActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Gson gson = new Gson();
        String json = sPrefs.getString(userName, "Fail");
        userName = sPrefs.getString("lastUser", "NoOne");
        user = gson.fromJson(json, User.class);
        weight.setText(user.getWeight());
        height.setText(user.getHeight());

    }
}