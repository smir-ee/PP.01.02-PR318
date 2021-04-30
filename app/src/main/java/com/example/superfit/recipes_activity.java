package com.example.superfit;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class recipes_activity extends AppCompatActivity {

    ListView listViewRecipes;
    private int[] textureArray = {R.drawable.flex, R.drawable.chel_image};
    private String[] name = {"Cum", "Balls"};
    private String[] kcal = {"7177", "2900"};
    private String[] protein = {"999", "290"};
    private String[] fat = {"ass is", "325"};
    private String[] carbs = {"746", "33"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_screen);

        ImageView[] imageViews = new ImageView[] {};
        listViewRecipes = (ListView) findViewById(R.id.listViewRecipes);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.id.recipes_item_image, R.id.recipes_item_name, R.id.recipes_item_kcal,
//                R.id.recipes_item_protein, R.id.recipes_item_fat, R.id.recipes_item_carbs);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.recipes_item, textureArray, name, kcal, protein, fat, carbs);
        listViewRecipes.setAdapter(adapter);

    }
    public void onBACK_reciples(View view){
        Intent intent = new Intent (recipes_activity.this, main_activity.class );
        startActivity(intent);
    }

}
