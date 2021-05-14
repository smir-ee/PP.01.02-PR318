package com.example.superfit.Recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superfit.R;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class SeparateRecipe extends AppCompatActivity {

    ArrayList<String> ingredients;
    ArrayList<Ingredient> data;
    String[] names;
    IngredientAdapter adapter;
    ListView lv;
    TextView txtName, txtCalories, txtPFC;
    LinearLayout linLayout;
    Bitmap bt;
    Drawable back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_separate_recipe);
        lv = findViewById(R.id.ingredients);
        getSupportActionBar().hide();
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        ingredients = new ArrayList<>();
        data = new ArrayList<>();
        ingredients = getIntent().getStringArrayListExtra("ing");
        names = ingredients.toArray(new String[0]);
        txtName = findViewById(R.id.txtName);
        txtCalories = findViewById(R.id.txtCalories);
        txtPFC = findViewById(R.id.txtPFC);
        linLayout = findViewById(R.id.linLayout);
        bt = getIntent().getParcelableExtra("Image");
        back = new BitmapDrawable(getResources(), bt);

        for (int i = 0; i< names.length; i++){
            data.add(new Ingredient(names[i]));
        }

        adapter = new IngredientAdapter(this, R.layout.ingredient_item, data);
        lv.setAdapter(adapter);
        txtName.setText(getIntent().getStringExtra("Name"));
        txtCalories.setText(getIntent().getStringExtra("Calories"));
        txtPFC.setText(getIntent().getStringExtra("PFC"));
        linLayout.setBackground(back);
    }

    public void onBackClick(View v){
        Intent intent = new Intent(this, Recipes.class);
        startActivity(intent);
    }
}