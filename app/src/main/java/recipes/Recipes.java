package recipes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.superfit.R;
import com.example.superfit.main_activity;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Recipes extends AppCompatActivity {

    ArrayList<RecipeObject> recipesBalanced;
    ArrayList<RecipeObject> recipesFiber;
    ArrayList<RecipeObject> recipesProtein;
    ArrayList<RecipeObject> recipes;
    ListView listView;
    CustomAdapterRecipes adapter;
    String dietType = "balanced";

    RadioButton balanced;
    RadioButton high_Protein;
    RadioButton high_Fiber;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_screen);

        listView = findViewById(R.id.lv_recipes_list);
        balanced = findViewById(R.id.Balanced);
        high_Fiber = findViewById(R.id.High_Fiber);
        high_Protein = findViewById(R.id.High_Protein);
        progressBar = findViewById(R.id.progressBar);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Recipes.this, RecipeScreen.class);
                intent.putExtra("Recipe", recipes.get(position));
                Log.i("", recipes.get(position).getLabel());
                startActivity(intent);
            }
        });

        RadioButton.OnCheckedChangeListener radio = new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if(buttonView.getId() == R.id.High_Protein && buttonView.isChecked()){
                  if(recipesProtein.size() == 0){
                      dietType="high-protein";
                      new getRecipes().execute();
                  }else{
                      adapter = new CustomAdapterRecipes(Recipes.this, recipesProtein);
                      listView.setAdapter(adapter);
                      adapter.notifyDataSetChanged();
                  }
              }
              if(buttonView.getId() == R.id.Balanced && buttonView.isChecked()){
                  if(recipesBalanced.size() == 0){
                      dietType="balanced";
                      new getRecipes().execute();
                  }else{
                      adapter = new CustomAdapterRecipes(Recipes.this, recipesBalanced);
                      listView.setAdapter(adapter);
                      adapter.notifyDataSetChanged();
                  }
              }
              if(buttonView.getId() == R.id.High_Fiber && buttonView.isChecked()){
                  if(recipesFiber.size() == 0){
                      dietType="low-carb";
                      new getRecipes().execute();
                  }else{
                      adapter = new CustomAdapterRecipes(Recipes.this, recipesFiber);
                      listView.setAdapter(adapter);
                      adapter.notifyDataSetChanged();
                  }
              }
            }
        };
        high_Protein.setOnCheckedChangeListener(radio);
        balanced.setOnCheckedChangeListener(radio);
        high_Fiber.setOnCheckedChangeListener(radio);

        new getRecipes().execute();
        recipesBalanced = new ArrayList<>();
        recipesProtein = new ArrayList<>();
        recipesFiber = new ArrayList<>();
        recipes = new ArrayList<>();
    }

    public void goHome(View view){
        Intent intent = new Intent(this, main_activity.class);
        startActivity(intent);
    }
    private class getRecipes extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("Connection", "preExec");
            listView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://api.edamam.com/search?q=chicken&app_id=4da5a427&app_key=6dd6f99730da1737e964379d886e607d&diet="+dietType).openConnection();
                httpURLConnection.connect();
                if(httpURLConnection.getResponseCode() == 200){
                    Log.i("Connection", "ok");
                }else{
                    Log.e("COnnectoin", " " + httpURLConnection.getResponseCode());
                }
                InputStream stream = httpURLConnection.getInputStream();
                StringBuilder jsonText = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String answer= "";
                while((answer = reader.readLine()) != null){
                    jsonText.append(answer);
                }
                Log.i("Parser", jsonText.toString());
                JSONObject jsonObject = new JSONObject(jsonText.toString());
                JSONArray hits = jsonObject.getJSONArray("hits");
                for(int i =0; i<hits.length(); i++){
                    JSONObject recipeobj = hits.getJSONObject(i);
                    JSONObject recipe = recipeobj.getJSONObject("recipe");
                    JSONArray digestJ = recipe.getJSONArray("digest");
                    String [] digest = new String[3];
                    digest[0] = digestJ.getJSONObject(0).get("total").toString().substring(0, digestJ.getJSONObject(0).get("total").toString().indexOf('.')) + "g protein";
                    digest[1] = digestJ.getJSONObject(1).get("total").toString().substring(0, digestJ.getJSONObject(1).get("total").toString().indexOf('.'))+ "g fat";
                    digest[2] = digestJ.getJSONObject(2).get("total").toString().substring(0, digestJ.getJSONObject(2).get("total").toString().indexOf('.')) + "g carbs";
                    switch (dietType) {
                        case "balanced":
                            recipesBalanced.add(new RecipeObject(recipe.getString("label"), recipe.getString("calories").substring(0, recipe.getString("calories").indexOf('.')) + " kcal", getImageBitmap(recipe.getString("image")), recipe.getString("source"), digest, recipe.getJSONArray("ingredientLines")));
                            break;
                        case "high-protein":
                            recipesProtein.add(new RecipeObject(recipe.getString("label"), recipe.getString("calories").substring(0, recipe.getString("calories").indexOf('.')) + " kcal", getImageBitmap(recipe.getString("image")), recipe.getString("source"), digest, recipe.getJSONArray("ingredientLines")));
                            break;
                        case "low-carb":
                            recipesFiber.add(new RecipeObject(recipe.getString("label"), recipe.getString("calories").substring(0, recipe.getString("calories").indexOf('.')) + " kcal", getImageBitmap(recipe.getString("image")), recipe.getString("source"), digest, recipe.getJSONArray("ingredientLines")));
                            break;
                    }

                }
                httpURLConnection.disconnect();
                jsonText = null;

                Log.i("Connection", jsonText.toString());
            } catch(Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            Log.i("PostExecupe", "PostExecupe");
            switch (dietType) {
                case "balanced":
                    recipes = recipesBalanced;
                    break;
                case "high-fiber":
                    recipes = recipesProtein;
                    break;
                case "high-protein":
                    recipes = recipesFiber;
                    break;
            }
            adapter = new CustomAdapterRecipes(Recipes.this, recipes);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        private Bitmap getImageBitmap(String url) {
            Bitmap bm = null;
            try {
                URL aURL = new URL(url);
                URLConnection conn = aURL.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                bm = BitmapFactory.decodeStream(bis);
                bis.close();
                is.close();
            } catch (IOException e) {
                Log.e("TAG", "Error getting bitmap", e);
            }
            return bm;
        }
    }
}