package com.example.superfit;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
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
    ListView lv_recipes_list, test;
    RecipesAdapter adapter;
    Recipe currentRecipe;
    ArrayList<Recipe> recipes = new ArrayList<>();
    String text;
    String nameL, src, enerc_kcal_valueL, fat_valueL, chocdf_valueL, procnt_valueL, IDL, QL;
    Bitmap bt;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_screen);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        lv_recipes_list = findViewById(R.id.lv_recipes_list);
//        ListView test = findViewById(R.id.test);

        adapter = new RecipesAdapter(ResipesList.this, recipes);
        lv_recipes_list.setAdapter(adapter);

        parser("https://api.edamam.com/search?q=chicken&app_id=4da5a427&app_key=6dd6f99730da1737e964379d886e607d&diet=balanced", recipes);

        lv_recipes_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), RecipeScreenActivity.class);
                //intent.putExtra("recipe", recipes.get(position)));
                //intent.putExtra("recipe", (Serializable) recipes.get(position));
                intent.putExtra("Ql", recipes.get(position).getTv_q());
                TextView textView1 = (TextView) view.findViewById(R.id.recipes_item_name);
                String name = (String) (textView1).getText();
                intent.putExtra("name", name);
                ImageView imageView = (ImageView) view.findViewById(R.id.recipes_item_image);
                bt = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                intent.putExtra("image", bt);
                //intent.putExtra( "name", nameL);
                //intent.putExtra("image", bt);
                TextView textView2 = (TextView) view.findViewById(R.id.recipes_item_text_kcal);
                String kcal = (String) (textView2).getText();
                intent.putExtra( "kcal", kcal);

                TextView textView3 = (TextView) view.findViewById(R.id.recipes_item_text_fat);
                String fat = (String) (textView3).getText();
                intent.putExtra("fat", fat);

                TextView textView4 = (TextView) view.findViewById(R.id.recipes_item_text_carbs);
                String carbs = (String) (textView4).getText();
                intent.putExtra( "carbs", carbs);

                TextView textView5 = (TextView) view.findViewById(R.id.recipes_item_text_protein);
                String protein = (String) (textView5).getText();
                intent.putExtra("protein", protein);

                //TextView textView6 = (TextView) view.findViewById(R.id.test);
                //String ID = (String) (textView5).getText();


                intent.putExtra("ID", recipes.get(position).getTv_recipe_ID());





                startActivity(intent);

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onClick_balanced(View view){
        parser("https://api.edamam.com/search?q=chicken&app_id=4da5a427&app_key=6dd6f99730da1737e964379d886e607d&diet=balanced", recipes);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onClick_highFiber(View view){
        parser("https://api.edamam.com/search?q=chicken&app_id=4da5a427&app_key=6dd6f99730da1737e964379d886e607d&diet=low-carb", recipes);
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

                    QL = ((String) recipe.get("q"));
                    currentRecipe.setTv_q(QL);

                    IDL = ((String) recipe.get("uri"));
                    currentRecipe.setTv_recipe_ID(IDL);

                    nameL = ((String) recipe.get("label"));
                    currentRecipe.setTv_recipe_name(nameL);

                    src = (String) recipe.get("image");
                    bt = getImageBitmap(src);

                    currentRecipe.setIv_recipe(getImageBitmap(src));

                    JSONObject totalNutrients = (JSONObject) recipe.get("totalNutrients");
                    JSONObject ENERC_KCAL = (JSONObject) totalNutrients.get("ENERC_KCAL");
                    Double enerc_kcal_value = (Double) ENERC_KCAL.get("quantity");
                    String enerc_kcal_unit = (String) ENERC_KCAL.get("unit");
                    enerc_kcal_valueL = (Math.round(enerc_kcal_value) + enerc_kcal_unit);
                    currentRecipe.setTv_recipe_value(enerc_kcal_valueL);

                    JSONObject FAT = (JSONObject) totalNutrients.get("FAT");
                    Double fat_value = (Double) FAT.get("quantity");
                    String fat_unit = (String) FAT.get("unit");
                    fat_valueL = (Math.round(fat_value) + fat_unit);
                    currentRecipe.setTv_fat(fat_valueL);

                    JSONObject CHOCDF = (JSONObject) totalNutrients.get("CHOCDF");
                    Double chocdf_value = (Double) CHOCDF.get("quantity");
                    String chocdf_unit = (String) CHOCDF.get("unit");
                    chocdf_valueL = (Math.round(chocdf_value) + chocdf_unit);
                    currentRecipe.setTv_carbs(chocdf_valueL);

                    JSONObject PROCNT = (JSONObject) totalNutrients.get("PROCNT");
                    Double procnt_value = (Double) PROCNT.get("quantity");
                    String procnt_unit = (String) PROCNT.get("unit");
                    procnt_valueL = (Math.round(procnt_value) + procnt_unit);
                    currentRecipe.setTv_protein(procnt_valueL);

                    //JSONObject INGREDIENTLIN = (JSONObject) totalNutrients.get("ingredientLines");
                    //currentRecipe.setTv_ingredient((String) recipe.get("ingredientLines"));

                    //JSONArray INGREDIENTLIN = (JSONArray) recipe.get("ingredient");
                    //currentRecipe.setTv_ingredient((String) INGREDIENTLIN.get("text"));;

                    //JSONArray INGREDIENTLIN = (JSONArray) recipe.get("ingredients");
                    //currentRecipe.setTv_ingredient((String) INGREDIENTLIN.get("text"));

//                    JSONArray ingr = (JSONArray) recipe.get("ingredients");
//                    for (Object o1 : ingr) {
//                        JSONObject j1o = (JSONObject) o1;
//                        currentRecipe.setTv_ingredient((String) j1o.get("text"));
//                    }
                    recipes.add(currentRecipe);
                }
                runOnUiThread(() -> {
                    adapter = new RecipesAdapter(ResipesList.this, recipes);
                    lv_recipes_list.setAdapter(adapter);
                });
                //inputStream.close();

            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }
        });
    }
}
