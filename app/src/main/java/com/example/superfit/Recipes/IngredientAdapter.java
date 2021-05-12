package com.example.superfit.Recipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.superfit.MainScreen.ExerciseItem;
import com.example.superfit.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends ArrayAdapter<Ingredient> {

    private final int resourceLayout;

    public IngredientAdapter(Context context, int resource, ArrayList<Ingredient> recipes){
        super(context, resource, recipes);
        this.resourceLayout = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;
        Ingredient recipe = getItem(position);

        if(v == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            v = layoutInflater.inflate(resourceLayout, null);
        }

        if(recipe != null){
            TextView name = v.findViewById(R.id.txtIng);

            name.setText(recipe.getIngredient());
        }
        return v;
    }
}
