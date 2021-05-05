package com.example.superfit.Recipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.superfit.MainScreen.ExerciseItem;
import com.example.superfit.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecipeAdapter extends ArrayAdapter<Recipe> {

    private final int resourceLayout;

    public RecipeAdapter(Context context, int resource, ArrayList<Recipe> recipes){
        super(context, resource, recipes);
        this.resourceLayout = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;
        Recipe recipe = getItem(position);

        if(v == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            v = layoutInflater.inflate(resourceLayout, null);
        }

        if(recipe != null){
            TextView name = v.findViewById(R.id.dishName);
            TextView cal = v.findViewById(R.id.tvCal);
            TextView prot = v.findViewById(R.id.tvProt);
            TextView fat = v.findViewById(R.id.tvFat);
            TextView carbs = v.findViewById(R.id.tvCarbs);

            name.setText(recipe.getName());
            cal.setText(recipe.getCalories());
            prot.setText(recipe.getProtein());
            fat.setText(recipe.getFat());
            carbs.setText(recipe.getCarbs());
        }
        return v;
    }
}
