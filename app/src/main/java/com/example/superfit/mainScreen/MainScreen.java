package com.example.superfit.mainScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.superfit.helperClasses.HideActionBarAndTransparentStatusBar;
import com.example.superfit.R;
import com.example.superfit.recipes.Recipes;
import com.example.superfit.loadScreens.MainActivity;

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
    public void onClickSignOut(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}