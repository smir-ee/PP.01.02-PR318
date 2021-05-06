package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {
    TextView label;
    TextView kcal;
    TextView pfc;
    ImageView image;
    ListView lvIngredients;
    ArrayList<String> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        lvIngredients = findViewById(R.id.list);

        label = findViewById(R.id.txtLabel);
        kcal = findViewById(R.id.txtkcal);
        pfc = findViewById(R.id.txtproteinFatCarbs);
        image = findViewById(R.id.image);

        Bundle bundle = getIntent().getExtras();
        Recipe recipe = (Recipe) bundle.getSerializable("Recipe");
        label.setText(recipe.getLabel());
        kcal.setText(recipe.getKcal());
        pfc.setText(recipe.getPFC());
        Picasso.with(this).load(recipe.getImage()).into(image);
        ingredients = recipe.getIngredients();
        IngredientAdapter adapter = new IngredientAdapter(this, ingredients);
        lvIngredients.setAdapter(adapter);
        HideActionBarAndTransparentStatusBar.hide(getWindow());
    }

    public void onClickBack(View v){
        finish();

    }

}