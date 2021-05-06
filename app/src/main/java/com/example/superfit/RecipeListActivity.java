//package com.example.superfit;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.ContextThemeWrapper;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.ProgressBar;
//import android.widget.SearchView;
//import android.widget.TextView;
//import android.widget.Toast;
//
////import com.github.ybq.android.spinkit.style.Circle;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//
//import javax.net.ssl.HttpsURLConnection;
//
///**
// * Экран рецептов
// */
//public class RecipeListActivity extends AppCompatActivity {
//
//    // константы: id и key для API
//    private static final String APP_ID = "b0e23358";
//    private static final String APP_KEY = "33972e22ce0dd8f06384d71f8bd3a3f2";
//
//    // переменные для фильтрации API
//    private static String DIET = "balanced";
//    private static String SEARCH = "";
//
//    private ListView lv_recipes;
//    private ArrayList<Recipe> recipeArrayList;
//    private RecipeAdapter recipeAdapter;
//    private SearchView searchView;
//
//    // Элемент для загрузочного кружка во время парсинга рецептов
//    ProgressBar progressBar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recipe_list);
//
//        progressBar = (ProgressBar) findViewById(R.id.spin_kit);
//
//        // методы для парсинга после изменения текста в строке поиска. Повторный парсинг начинается
//        // спустя 4 сек после введенного/удаленного символа
//        searchView = findViewById(R.id.et_search);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        recipeArrayList.clear();
//                        recipeAdapter.notifyDataSetChanged();
//                        SEARCH = query;
//                        Async();
//                    }
//                }, 4000);
////                adapterRecipe.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        recipeArrayList.clear();
//                        recipeAdapter.notifyDataSetChanged();
//                        SEARCH = newText;
//                        Async();
//                    }
//                }, 4000);
//                return false;
//            }
//        });
//
////        Circle wave = new Circle();
////        progressBar.setIndeterminateDrawable(wave);
////        progressBar.setVisibility(View.VISIBLE);
//
//        // обработка нажатия по элементу listview, переход на индивидуальную карточку рецепта
//        lv_recipes = findViewById(R.id.lv_recipes);
//        lv_recipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getApplicationContext(), RecipeScreenActivity.class);
//                intent.putExtra("recipe", recipeArrayList.get(position));
//                startActivity(intent);
//            }
//        });
//
//        recipeArrayList = new ArrayList<>();
//
//        System.out.println("!");
//        Async();
//
//    }
//
//    private void Async(){
//        // появление значка загрузки на экране
//        Circle wave = new Circle();
//        progressBar.setIndeterminateDrawable(wave);
//        progressBar.setVisibility(View.VISIBLE);
//
//        // отправка запроса и парсинг во втором потоке
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                // объявление соединения
//                HttpURLConnection connection = null;
//                try {
//                    //URL url = new URL("https://api.edamam.com/search?q=chicken&app_id=4da5a427&app_key=6dd6f99730da1737e964379d886e607d&diet=high-protein");
//                    URL url = new URL("https://api.edamam.com/search?q=" + SEARCH + "&app_id=" + APP_ID + "&app_key=" + APP_KEY + "&diet=" + DIET);
//
//                    // создание соединения
//                    connection = (HttpURLConnection) url.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.connect();
//
//                    System.out.println("#");
//
//                    // если соединение успешно - начало парсинга
//                    if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()) {
//                        InputStreamReader isr = new InputStreamReader(connection.getInputStream());
//                        System.out.println("code 200");
//
//                        parser(isr);
//
//                        isr.close();
//                        // заполнение listview в основном потоке
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                recipeAdapter = new RecipeAdapter(RecipeListActivity.this, recipeArrayList);
//                                lv_recipes.setAdapter(recipeAdapter);
//                                progressBar.setVisibility(View.GONE);
//                            }
//                        });
//                    }
////                    else {
////                        runOnUiThread(new Runnable() {
////                            @Override
////                            public void run() {
////                                adapterRecipe = new AdapterRecipe(RecipeListActivity.this, recipeArrayList);
////                                lv_recipes.setAdapter(adapterRecipe);
////                            }
////                        });
////                        System.out.println("else");
////                        TextView tv_error = findViewById(R.id.tv_error);
////                        tv_error.setVisibility(View.VISIBLE);
////                        tv_error.setText("Нет рецептов в категории: " + DIET);
//////                        Toast.makeText(getApplicationContext(), "Добро пожаловать", Toast.LENGTH_SHORT).show();
//////                        Toast.makeText(RecipeListActivity.this, "Нет рецептов в категории: " + DIET, Toast.LENGTH_LONG).show();
////                    }
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    if (connection != null) {
//                        connection.disconnect();
//                    }
//                }
//            }
//        });
//    }
//    // метод для парсинга json файла
//    private void parser(InputStreamReader file)  {
//        try {
//            Object object = new JSONParser().parse(file);
//            JSONObject jo = (JSONObject) object;
//
//            JSONArray hits = (JSONArray) jo.get("hits");
//
//            for (Object obj : hits) {
//                JSONObject jsonObject = (JSONObject) obj;
//
//                // получение необходимой информации из файла json
//                JSONObject recipe = (JSONObject) jsonObject.get("recipe");
//                String name = recipe.get("label").toString();
//                String image = recipe.get("image").toString();
////                System.out.println(name);
//
//                ArrayList<String> dietLabels = new ArrayList<String>();
//                JSONArray jsonObject1_dietLabels = (JSONArray) recipe.get("dietLabels");
//                for (int i = 0; i < jsonObject1_dietLabels.size(); i++) {
////                    System.out.println(jsonObject1_dietLabels.get(i).toString());
//                    dietLabels.add(jsonObject1_dietLabels.get(i).toString());
//                }
//
//                ArrayList<Ingredient> ingredient = new ArrayList<>();
//                JSONArray ingredientLines = (JSONArray) recipe.get("ingredientLines");
//                for (int i = 0; i < ingredientLines.size(); i++) {
////                    System.out.println(ingredientLines.get(i).toString());
//                    Ingredient ing = new Ingredient(ingredientLines.get(i).toString());
//                    ingredient.add(ing);
//                }
//
//                JSONObject totalNutrients = (JSONObject) recipe.get("totalNutrients");
//                JSONObject jo_kcal = (JSONObject) totalNutrients.get("ENERC_KCAL");
//                JSONObject jo_fat = (JSONObject) totalNutrients.get("FAT");
//                JSONObject jo_protein = (JSONObject) totalNutrients.get("PROCNT");
//                JSONObject jo_carbs = (JSONObject) totalNutrients.get("CHOCDF");
//                int kcal = (int) Math.round(Double.parseDouble(jo_kcal.get("quantity").toString()));
//                int fat = (int) Math.round(Double.parseDouble(jo_fat.get("quantity").toString()));
//                int protein = (int) Math.round(Double.parseDouble(jo_protein.get("quantity").toString()));
//                int carbs = (int) Math.round(Double.parseDouble(jo_carbs.get("quantity").toString()));
//
////                System.out.println("!" + fat + " " + protein + " " + carbs);
//
//                Recipe curr_recipe = new Recipe(name, image, dietLabels, kcal, protein, fat, carbs, ingredient);
//                recipeArrayList.add(curr_recipe);
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // обработка нажатия по кнопкам смены диеты
//    public void changeDiet(View v) {
//        recipeArrayList.clear();
//        recipeAdapter.notifyDataSetChanged();
//
////        Circle wave = new Circle();
////        progressBar.setIndeterminateDrawable(wave);
////        progressBar.setVisibility(View.VISIBLE);
//
//        LinearLayout diet_balanced = findViewById(R.id.diet_balanced);
//        TextView txt_balanced = findViewById(R.id.btn_balanced);
//        LinearLayout diet_high_fiber = findViewById(R.id.diet_high_fiber);
//        TextView txt_high_fiber = findViewById(R.id.btn_high_fiber);
//        LinearLayout diet_high_protein = findViewById(R.id.diet_high_protein);
//        TextView txt_high_protein = findViewById(R.id.btn_high_protein);
//
//        switch (v.getId()) {
//            case R.id.diet_balanced:
//                DIET = "balanced";
//
//                diet_balanced.setBackgroundResource(R.drawable.block_diet_pressed);
//                txt_balanced.setTextColor(Color.parseColor("#B461F5"));
//
//                diet_high_fiber.setBackgroundResource(R.drawable.block_diet);
//                txt_high_fiber.setTextColor(Color.parseColor("#FFFFFF"));
//                diet_high_protein.setBackgroundResource(R.drawable.block_diet);
//                txt_high_protein.setTextColor(Color.parseColor("#FFFFFF"));
//                break;
//            case R.id.diet_high_fiber:
//                DIET="high-fiber";
//                diet_balanced.setBackgroundResource(R.drawable.block_diet);
//                txt_balanced.setTextColor(Color.parseColor("#FFFFFF"));
//
//                diet_high_fiber.setBackgroundResource(R.drawable.block_diet_pressed);
//                txt_high_fiber.setTextColor(Color.parseColor("#B461F5"));
//
//                diet_high_protein.setBackgroundResource(R.drawable.block_diet);
//                txt_high_protein.setTextColor(Color.parseColor("#FFFFFF"));
//
//                Toast.makeText(RecipeListActivity.this, "Нет рецептов в категории: " + DIET, Toast.LENGTH_LONG).show();
//                break;
//            case R.id.diet_high_protein:
//                DIET="high-protein";
//                diet_balanced.setBackgroundResource(R.drawable.block_diet);
//                txt_balanced.setTextColor(Color.parseColor("#FFFFFF"));
//                diet_high_fiber.setBackgroundResource(R.drawable.block_diet);
//                txt_high_fiber.setTextColor(Color.parseColor("#FFFFFF"));
//
//                diet_high_protein.setBackgroundResource(R.drawable.block_diet_pressed);
//                txt_high_protein.setTextColor(Color.parseColor("#B461F5"));
//                break;
//        }
//        Async();
//    }
//
//
//}