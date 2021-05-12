package recipes;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.superfit.R;

import org.json.JSONException;

public class RecipeScreen extends AppCompatActivity {

    CustomAdapterIngridients adapter;
    ListView ingredientListView;
    ImageView backImg;
    RecipeObject recipe;
    TextView label;
    TextView calories;
    TextView protein;
    TextView fat;
    TextView cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_item_screen);


        ingredientListView = findViewById(R.id.recipe_ls_item);
        backImg = findViewById(R.id.recipe_bg_item);
        label = findViewById(R.id.recipe_name_item);
        calories = findViewById(R.id.recipe_text_kcal_item);
        protein = findViewById(R.id.recipe_text_protein_item);
        fat = findViewById(R.id.recipe_text_fat_item);
        cards = findViewById(R.id.recipe_text_carbs_item);

        recipe = (RecipeObject) getIntent().getParcelableExtra("Recipe");

        Drawable draw = new BitmapDrawable(getResources(), recipe.getImage());
        //backImg.setBackground(draw);
        //backImg.setImageBitmap(draw);
        backImg.setImageDrawable(draw);

        label.setText(recipe.getLabel());
        calories.setText(recipe.getCalories());
        protein.setText(recipe.getDigest()[0]);
        fat.setText(recipe.getDigest()[1]);
        cards.setText(recipe.getDigest()[2]);
        try {
            adapter = new CustomAdapterIngridients(this, recipe.getIngridientLines());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ingredientListView.setAdapter(adapter);
    }
    public void goBack(View view){
        finish();
        //onBackPressed();
        //Intent intent = new Intent(this, Recipes.class);
        //startActivity(intent);
    }

}