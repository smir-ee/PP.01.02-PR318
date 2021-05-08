package com.example.superfit.Recipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.superfit.R;

import java.util.ArrayList;

public class IngredientAdapter extends ArrayAdapter<String> {

    private final int resourceLayout;

    public IngredientAdapter(Context context, int resource, ArrayList<String> ing){
        super(context, resource, ing);
        this.resourceLayout = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        String ingredient = getItem(position);
        Ingredient ingredient1 = new Ingredient();

        if(v == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            v = layoutInflater.inflate(resourceLayout, null);
        }

        if(ingredient != null){
            TextView ing = v.findViewById(R.id.txtIng);

            ing.setText(ingredient1.getIngredient());
        }
        return v;
    }
}
