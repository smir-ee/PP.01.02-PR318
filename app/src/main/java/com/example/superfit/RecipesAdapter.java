package com.example.superfit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipesAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater li;
    ArrayList<Recipe> recipes;

    RecipesAdapter(Context ctx, ArrayList<Recipe> recipes){
        this.ctx = ctx;
        this.recipes = recipes;
        li = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public Object getItem(int position) {
        return recipes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null){
            view = li.inflate(R.layout.recipes_item, parent, false);
        }

        Recipe recipe = (Recipe) getItem(position);
       // RecipesAdapter_Adapter adapter = new RecipesAdapter_Adapter(ctx, recipes);
        ((ImageView) view.findViewById(R.id.recipes_item_image)).setImageBitmap(recipe.getIv_recipe());
        ((TextView) view.findViewById(R.id.recipes_item_name)).setText(recipe.getTv_recipe_name());
        ((TextView) view.findViewById(R.id.recipes_item_text_kcal)).setText(recipe.getTv_recipe_value());
        ((TextView) view.findViewById(R.id.recipes_item_text_protein)).setText(recipe.getTv_protein());
        ((TextView) view.findViewById(R.id.recipes_item_text_fat)).setText(recipe.getTv_fat());
        ((TextView) view.findViewById(R.id.recipes_item_text_carbs)).setText(recipe.getTv_carbs());
        //((TextView) view.findViewById(R.id.test)).setText(recipe.getTv_recipe_ID());


        //((ListView) view.findViewById(R.id.test)).setText(recipe.getTv_ingredient());
//        ((ListView) view.findViewById(R.id.test)).setAdapter(adapter);
        return view;
    }
}
