package com.example.superfit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class IngredientAdapter extends BaseAdapter{
    Context mContext;
    ArrayList<String> strings;

    public IngredientAdapter(Context mContext, ArrayList<String> strings) {
        this.mContext = mContext;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int position) {
        return strings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = LayoutInflater.from(mContext).inflate(R.layout.ingredient_row, parent, false);
        String ingredient = strings.get(position);
        TextView text = view.findViewById(R.id.txtIngredient);
        text.setText(ingredient);
        return view;


    }
}
