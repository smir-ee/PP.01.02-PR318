package com.example.superfit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//import com.squareup.picasso.Picasso;

/**
 * Экран рецепта
 */
public class RecipeScreenActivity extends AppCompatActivity {

    ListView tv_ingredients;
    //TextView tv_ingredients;
    ImageView iv_image;
    TextView tv_name_on_scroll;
    TextView tv_kcal;
    TextView tv_protein;
    TextView tv_fat;
    TextView tv_carbs;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_item_screen);

        //lv_ingredients = findViewById(R.id.recipe_ls_item);
        //tv_ingredients = findViewById(R.id.tvIngredientText);
        tv_ingredients = findViewById(R.id.recipe_ls_item);
        iv_image = findViewById(R.id.recipe_bg_item);
        tv_name_on_scroll = findViewById(R.id.recipe_name_item);
        tv_kcal = findViewById(R.id.recipe_text_kcal_item);
        tv_protein = findViewById(R.id.recipe_text_protein_item);
        tv_fat = findViewById(R.id.recipe_text_fat_item);
        tv_carbs = findViewById(R.id.recipe_text_carbs_item);

//        parser(("https://api.edamam.com/search?q=chicken&app_id=4da5a427&app_key=6dd6f99730da1737e964379d886e607d&label="+ text), recipes);

        // обработка нажатия по кнопке назад
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


        }

    }

}
