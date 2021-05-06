package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        HideActionBarAndTransparentStatusBar.hide(getWindow());
    }
    public void onClickRecipes(View v){
        Intent intent = new Intent(this, Recipes.class);
        startActivity(intent);
    }
}