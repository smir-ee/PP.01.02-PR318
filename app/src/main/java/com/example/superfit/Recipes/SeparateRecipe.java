package com.example.superfit.Recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_separate_recipe);
        lv = findViewById(R.id.ingredients);
        getSupportActionBar().hide();

        ingredients = new ArrayList<>();
        data = new ArrayList<>();
        ingredients = getIntent().getStringArrayListExtra("ing");
        names = ingredients.toArray(new String[0]);
        txtName = findViewById(R.id.txtName);
        txtCalories = findViewById(R.id.txtCalories);
        txtPFC = findViewById(R.id.txtPFC);

        for (int i = 0; i< names.length; i++){
            data.add(new Ingredient(names[i]));
        }

        adapter = new IngredientAdapter(this, R.layout.ingredient_item, data);
        lv.setAdapter(adapter);
        txtName.setText(getIntent().getStringExtra("Name"));
        txtCalories.setText(getIntent().getStringExtra("Calories"));
        txtPFC.setText(getIntent().getStringExtra("PFC"));
    }
}