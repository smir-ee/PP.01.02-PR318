package com.example.superfit.Recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.example.superfit.R;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class SeparateRecipe extends AppCompatActivity {

    ArrayList<String> ingredients;
    IngredientAdapter adapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_separate_recipe);
        lv = findViewById(R.id.ingredients);

        ingredients = new ArrayList<>();
        ingredients = getIntent().getStringArrayListExtra("ing");
        adapter = new IngredientAdapter(this, R.layout.ingredient_item, ingredients);
        lv.setAdapter(adapter);
    }
}