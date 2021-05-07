package com.example.superfit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class RecipeScreenActivity extends AppCompatActivity {

    Recipe_Item currentRecipe;
    //ListView tv_ingredients;
    //TextView tv_ingredients;
    ListView lv_recipes_list;
    ArrayList<Recipe_Item> recipes = new ArrayList<>();
    RecipesAdapter_Adapter adapter;
    ImageView iv_image;
    TextView tv_name_on_scroll;
    TextView tv_kcal;
    TextView tv_protein;
    TextView tv_fat;
    TextView tv_carbs;
    String text;
    String Q;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_item_screen);

        //lv_ingredients = findViewById(R.id.recipe_ls_item);
        //tv_ingredients = findViewById(R.id.tvIngredientText);
        //tv_ingredients = findViewById(R.id.recipe_ls_item);
        iv_image = findViewById(R.id.recipe_bg_item);
        tv_name_on_scroll = findViewById(R.id.recipe_name_item);
        tv_kcal = findViewById(R.id.recipe_text_kcal_item);
        tv_protein = findViewById(R.id.recipe_text_protein_item);
        tv_fat = findViewById(R.id.recipe_text_fat_item);
        tv_carbs = findViewById(R.id.recipe_text_carbs_item);

        lv_recipes_list = findViewById(R.id.recipe_ls_item);
        adapter = new RecipesAdapter_Adapter(RecipeScreenActivity.this, recipes);
        lv_recipes_list.setAdapter(adapter);

        String ID = "http://www.edamam.com/ontologies/edamam.owl#recipe_b79327d05b8e5b838ad6cfd9576b30b6";

//        parser(("https://api.edamam.com/search?q=chicken&app_id=4da5a427&app_key=6dd6f99730da1737e964379d886e607d&label="+ text), recipes);


        ImageView btn_back_on_scroll = findViewById(R.id.btnReturn);
        btn_back_on_scroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResipesList.class);
                startActivity(intent);
            }
        });

        // считывание передаваемой информации о выбранном рецете и заполнение экрана необходисмой информацией
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            //Recipe_Item recipe = (Recipe_Item) bundle.getSerializable("recipe");

            //Bitmap image = recipe.getIv_recipe();
            //Bitmap image = bundle.get("image").Bi;

            //Picasso.get().load(image).into(iv_image);
            Bitmap image = (Bitmap) bundle.get("image");
            iv_image.setImageBitmap(Bitmap.createBitmap(image));//?

            //String name = recipe.getTv_recipe_name();
            String name = bundle.get("name").toString();
            tv_name_on_scroll.setText(String.valueOf(name));

            //String kcal = recipe.getTv_recipe_value();
            String kcal = bundle.get("kcal").toString();
            tv_kcal.setText(String.valueOf(kcal));

            //String protein = recipe.getTv_protein();
            String protein = bundle.get("protein").toString();
            tv_protein.setText(String.valueOf(protein));

            //String fat = recipe.getTv_fat();
            String fat = bundle.get("fat").toString();
            tv_fat.setText(String.valueOf(fat));

            //String carbs = recipe.getTv_carbs();
            String carbs = bundle.get("carbs").toString();
            tv_carbs.setText(String.valueOf(carbs));

            ID = bundle.get("ID").toString();

            Q = bundle.getString("Ql");//.toString();



        }

        //parser("https://api.edamam.com/search?&app_id=4da5a427&app_key=6dd6f99730da1737e964379d886e607d&r=" + ID, recipes);
        parser("https://api.edamam.com/search?q=" + Q + "&app_id=4da5a427&app_key=6dd6f99730da1737e964379d886e607d&diet=balanced", recipes);
        //https://api.edamam.com/search?q=" + SEARCH + "&app_id=" + APP_ID + "&app_key=" + APP_KEY + "&diet=" + DIET


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void parser (String ref, ArrayList< Recipe_Item > recipes){
        AsyncTask.execute(() -> {
            try {
                URL[] url = new URL[1];
                url[0] = new URL(ref);
//                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url[0].openConnection();
//                InputStream inputStream = httpsURLConnection.getInputStream();
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url[0].openConnection();
                //HttpURLConnection httpsURLConnection = (HttpURLConnection) url[0].openConnection();
                InputStream inputStream = httpsURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

                Object object = new JSONParser().parse(inputStreamReader);
                JSONObject jsonObject = (JSONObject) object;
                JSONArray ingr = (JSONArray) jsonObject.get("ingredients");
                recipes.clear();
                for (Object o : ingr) {
                    currentRecipe = new Recipe_Item();
                    JSONObject jo = (JSONObject) o;
                    //JSONObject recipe = (JSONObject) jo.get("recipe");
                    currentRecipe.setTv_ingredient((String) jo.get("text"));

                    //JSONObject INGREDIENTLIN = (JSONObject) totalNutrients.get("ingredientLines");
                    //currentRecipe.setTv_ingredient((String) recipe.get("ingredientLines"));

                    //JSONArray INGREDIENTLIN = (JSONArray) recipe.get("ingredient");
                    //currentRecipe.setTv_ingredient((String) INGREDIENTLIN.get("text"));;

                    //JSONArray INGREDIENTLIN = (JSONArray) recipe.get("ingredients");
                    //currentRecipe.setTv_ingredient((String) INGREDIENTLIN.get("text"));


                    recipes.add(currentRecipe);
                }
                runOnUiThread(() -> {
                    adapter = new RecipesAdapter_Adapter(RecipeScreenActivity.this, recipes);
                    lv_recipes_list.setAdapter(adapter);
                });

            } catch (IOException | org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        });
    }
}
