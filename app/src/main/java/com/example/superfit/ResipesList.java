package com.example.superfit;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class ResipesList extends AppCompatActivity {
    CardView cv_recipes_filters, cv_balanced, cv_high_fiber, cv_high_protein;
    TextView tv_balanced, tv_high_fiber, tv_high_protein;
    ListView lv_recipes_list;
    RecipesAdapter adapter;
    Recipe currentRecipe;
    ArrayList<Recipe> recipes = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_screen);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        lv_recipes_list = findViewById(R.id.lv_recipes_list);

        adapter = new RecipesAdapter(ResipesList.this, recipes);
        lv_recipes_list.setAdapter(adapter);

        parser("https://api.edamam.com/search?q=chicken&app_id=4da5a427&app_key=6dd6f99730da1737e964379d886e607d&diet=balanced", recipes);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onClick_balanced(View view){
        parser("https://api.edamam.com/search?q=chicken&app_id=4da5a427&app_key=6dd6f99730da1737e964379d886e607d&diet=balanced", recipes);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onClick_highFiber(View view){
        parser("https://api.edamam.com/search?q=chicken&app_id=4da5a427&app_key=6dd6f99730da1737e964379d886e607d&diet=high-fiber", recipes);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onClick_highProtein(View view){
        parser("https://api.edamam.com/search?q=chicken&app_id=4da5a427&app_key=6dd6f99730da1737e964379d886e607d&diet=high-protein", recipes);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void parser(String ref, ArrayList<Recipe> recipes){
        AsyncTask.execute(() -> {
            try {
                URL[] url = new URL[1];
                url[0] = new URL(ref);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url[0].openConnection();
                InputStream inputStream = httpsURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

                Object object = new JSONParser().parse(inputStreamReader);
                JSONObject jsonObject = (JSONObject) object;
                JSONArray jsonArray = (JSONArray) jsonObject.get("hits");
                recipes.clear();
                for (Object o : jsonArray) {
                    currentRecipe = new Recipe();
                    JSONObject jo = (JSONObject) o;
                    JSONObject recipe = (JSONObject) jo.get("recipe");
                    currentRecipe.setTv_recipe_name((String) recipe.get("label"));
                    String src = (String) recipe.get("image");
                    currentRecipe.setIv_recipe(getImageBitmap(src));
                    JSONObject totalNutrients = (JSONObject) recipe.get("totalNutrients");
                    JSONObject ENERC_KCAL = (JSONObject) totalNutrients.get("ENERC_KCAL");
                    Double enerc_kcal_value = (Double) ENERC_KCAL.get("quantity");
                    String enerc_kcal_unit = (String) ENERC_KCAL.get("unit");
                    currentRecipe.setTv_recipe_value(Math.round(enerc_kcal_value) + enerc_kcal_unit);
                    JSONObject FAT = (JSONObject) totalNutrients.get("FAT");
                    Double fat_value = (Double) FAT.get("quantity");
                    String fat_unit = (String) FAT.get("unit");
                    currentRecipe.setTv_fat(Math.round(fat_value) + fat_unit);
                    JSONObject CHOCDF = (JSONObject) totalNutrients.get("CHOCDF");
                    Double chocdf_value = (Double) CHOCDF.get("quantity");
                    String chocdf_unit = (String) CHOCDF.get("unit");
                    currentRecipe.setTv_carbs(Math.round(chocdf_value) + chocdf_unit);
                    JSONObject PROCNT = (JSONObject) totalNutrients.get("PROCNT");
                    Double procnt_value = (Double) PROCNT.get("quantity");
                    String procnt_unit = (String) PROCNT.get("unit");
                    currentRecipe.setTv_protein(Math.round(procnt_value) + procnt_unit);
                    recipes.add(currentRecipe);
                }
                runOnUiThread(() -> {
                    adapter = new RecipesAdapter(ResipesList.this, recipes);
                    lv_recipes_list.setAdapter(adapter);
                });
            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }
        });
    }
}
