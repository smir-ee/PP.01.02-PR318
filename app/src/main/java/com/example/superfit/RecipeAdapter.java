package com.example.superfit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<Recipe> recipes = new ArrayList<>();

    public RecipeAdapter(Context mContext, ArrayList<Recipe> recipes) {
        this.mContext = mContext;
        this.recipes = recipes;
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
        if (view == null)
            view = LayoutInflater.from(mContext).inflate(R.layout.recipe_row, parent, false);
        Recipe recipe = recipes.get(position);

        ImageView image = view.findViewById(R.id.image);
        TextView label = view.findViewById(R.id.txtLabel);
        TextView kcal = view.findViewById(R.id.txtkcal);
        TextView proteinFC = view.findViewById(R.id.txtproteinFatCarbs);

        Picasso.with(mContext).load(recipe.getImage()).into(image);
        label.setText(recipe.getLabel());
        kcal.setText(recipe.getKcal());
        proteinFC.setText(recipe.getPFC());
        return view;

    }
}
