package com.example.superfit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipesAdapter_Adapter extends BaseAdapter {
    Context ctx;
    LayoutInflater li;
    ArrayList<Recipe_Item> recipes;

    RecipesAdapter_Adapter(Context ctx, ArrayList<Recipe_Item> recipes){
        this.ctx = ctx;
        this.recipes = recipes;
        li = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public Object getItem(int position) { return recipes.get(position); }

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

        Recipe_Item recipe = (Recipe_Item) getItem(position);
       ((TextView) view.findViewById(R.id.tvIngredientText)).setText(recipe.getTv_ingredient());
        return view;
    }
}
