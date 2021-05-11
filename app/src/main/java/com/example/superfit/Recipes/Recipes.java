package com.example.superfit.Recipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superfit.MainScreen.MainScreen;
import com.example.superfit.R;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class Recipes extends AppCompatActivity {

    RecipeAdapter adapter;
    ArrayList<Recipe> data;
    ListView listView;
    Recipe currentRecipe;
    final URL[] url = new URL[3];
    TextView errorText;
    ArrayList<String> ingredients;
    Button balanced_bt, high_fiber_bt, high_protein_bt;
    EditText search;
    String search_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        getSupportActionBar().hide();
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        data = new ArrayList<>();
        listView = findViewById(R.id.list);
        errorText = findViewById(R.id.errorText);
        balanced_bt = findViewById(R.id.bt_balanced);
        high_fiber_bt = findViewById(R.id.bt_highFiber);
        high_protein_bt = findViewById(R.id.bt_highProtein);
        search = findViewById(R.id.Search);
        ingredients = new ArrayList<>();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Recipes.this, SeparateRecipe.class);
                intent.putStringArrayListExtra("ing", ingredients);
                startActivity(intent);
            }
        });
    }

    public void onBalancedClick(View v){
        errorText.setText("");
        balanced_bt.setBackgroundResource(R.drawable.recipe_button_inverted);
        balanced_bt.setTextColor(ContextCompat.getColor(this, R.color.someColor));
        high_fiber_bt.setBackgroundResource(R.drawable.recipe_button);
        high_fiber_bt.setTextColor(ContextCompat.getColor(this, R.color.white));
        high_protein_bt.setBackgroundResource(R.drawable.recipe_button);
        high_protein_bt.setTextColor(ContextCompat.getColor(this, R.color.white));
        if (adapter != null){
            adapter.clear();
        }
        if (search.getText().toString() != null){
            search_request = search.getText().toString();
        }
        else {
            search_request = "chicken";
        }
        Parse(0);
    }

    public void onHighFiberClick(View v){
        high_fiber_bt.setBackgroundResource(R.drawable.recipe_button_inverted);
        high_fiber_bt.setTextColor(ContextCompat.getColor(this, R.color.someColor));
        balanced_bt.setBackgroundResource(R.drawable.recipe_button);
        balanced_bt.setTextColor(ContextCompat.getColor(this, R.color.white));
        high_protein_bt.setBackgroundResource(R.drawable.recipe_button);
        high_protein_bt.setTextColor(ContextCompat.getColor(this, R.color.white));
        if (adapter != null){
            adapter.clear();
        }
        if (search.getText().toString() != null){
            search_request = search.getText().toString();
        }
        else {
            search_request = "chicken";
        }
        Parse(2);
    }

    public void onHighProteinClick(View v){
        high_protein_bt.setBackgroundResource(R.drawable.recipe_button_inverted);
        high_protein_bt.setTextColor(ContextCompat.getColor(this, R.color.someColor));
        high_fiber_bt.setBackgroundResource(R.drawable.recipe_button);
        high_fiber_bt.setTextColor(ContextCompat.getColor(this, R.color.white));
        balanced_bt.setBackgroundResource(R.drawable.recipe_button);
        balanced_bt.setTextColor(ContextCompat.getColor(this, R.color.white));
        errorText.setText("");
        if (adapter != null){
            adapter.clear();
        }
        if (search.getText().toString() != null){
            search_request = search.getText().toString();
        }
        else {
            search_request = "chicken";
        }
        Parse(1);
    }

    private void Parse(final int i){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    switch (i){
                        case 0:
                            url[0] = new URL("https://api.edamam.com/search?q=" + search_request +"&app_id=4da5a427&app_key=6dd6f99730da1737e964379d886e607d&diet=balanced");
                            break;
                        case 1:
                            url[1] = new URL("https://api.edamam.com/search?q=" + search_request + "&app_id=4da5a427&app_key=6dd6f99730da1737e964379d886e607d&diet=high-protein");
                            break;
                        case 2:
                            url[2] = new URL("https://api.edamam.com/search?q=" + search_request + "&app_id=4da5a427&app_key=6dd6f99730da1737e964379d886e607d&diet=high-fiber");
                            break;
                    }

                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url[i].openConnection();
                    InputStream inputStream = httpsURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");

                    Object object = new JSONParser().parse(inputStreamReader);
                    JSONObject jsonObject = (JSONObject) object;
                    JSONArray jsonArray = (JSONArray) jsonObject.get("hits");
                    Iterator iterator = jsonArray.iterator();

                    while (iterator.hasNext()) {
                        currentRecipe = new Recipe();
                        JSONObject jo = (JSONObject) iterator.next();
                        JSONObject recipe = (JSONObject) jo.get("recipe");
                        currentRecipe.setName((String) recipe.get("label"));
                        String src = (String) recipe.get("image");
                        currentRecipe.setImage(getImageBitmap(src));
                        JSONObject totalNutrients = (JSONObject) recipe.get("totalNutrients");
                        JSONObject ENERC_KCAL = (JSONObject) totalNutrients.get("ENERC_KCAL");
                        Double enerc_kcal_value = (Double) ENERC_KCAL.get("quantity");
                        String enerc_kcal_unit = (String) ENERC_KCAL.get("unit");
                        currentRecipe.setCalories(String.valueOf(Math.round(enerc_kcal_value)) + " " + enerc_kcal_unit);
                        JSONObject FAT = (JSONObject) totalNutrients.get("FAT");
                        Double fat_value = (Double) FAT.get("quantity");
                        String fat_unit = (String) FAT.get("unit");
                        currentRecipe.setFat(String.valueOf(" • " + Math.round(fat_value)) + fat_unit + " fat • ");
                        JSONObject CHOCDF = (JSONObject) totalNutrients.get("CHOCDF");
                        Double chocdf_value = (Double) CHOCDF.get("quantity");
                        String chocdf_unit = (String) CHOCDF.get("unit");
                        currentRecipe.setCarbs(String.valueOf(Math.round(chocdf_value)) + chocdf_unit + " carbs");
                        JSONObject PROCNT = (JSONObject) totalNutrients.get("PROCNT");
                        Double procnt_value = (Double) PROCNT.get("quantity");
                        String procnt_unit = (String) PROCNT.get("unit");
                        currentRecipe.setProtein(String.valueOf(Math.round(procnt_value)) + procnt_unit + " protein");
                        data.add(currentRecipe);

                        JSONObject ingredient = (JSONObject)recipe.get("ingredients");
                        String aboba = (String) ingredient.get("text");
                        ingredients.add(aboba);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter = new RecipeAdapter(Recipes.this,R.layout.recipes_item, data);
                            adapter.notifyDataSetChanged();
                            listView.setAdapter(adapter);
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Bitmap getImageBitmap(String url){
        Bitmap bitmap = null;
        try{
            URL url1 = new URL(url);
            URLConnection urlConnection = url1.openConnection();
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            bitmap = BitmapFactory.decodeStream(bufferedInputStream);
            bufferedInputStream.close();
            inputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public void return_click(View v){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }
}