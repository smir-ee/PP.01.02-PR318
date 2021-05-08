package com.example.superfit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class exercises_adapter  extends BaseAdapter {

    Context context;
    LayoutInflater li;
    ArrayList<exercises_item> list = new ArrayList<exercises_item>();
//    ArrayList<exercises_item> image = new ArrayList<exercises_item>();
//    ArrayList<exercises_item> name = new  ArrayList<exercises_item>();
//    ArrayList<exercises_item> text = new  ArrayList<exercises_item>();

    public exercises_adapter(Context context, ArrayList<exercises_item> list ){
        this.context = context;
        this.list = list;
        li = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        this.image = image;
//        this.name = name;
//        this.text = text;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View someView, ViewGroup parent) {
        View view = someView;

        if (view == null){
            view = li.inflate(R.layout.main_item, parent, false);
        }

        exercises_item recipe = (exercises_item) getItem(position);
        //((ImageView) view.findViewById(R.id.main_item_image)).setImageDrawable(recipe.getImage());
        ((ImageView) view.findViewById(R.id.main_item_image)).setImageResource(recipe.getImage());
        ((TextView) view.findViewById(R.id.main_item_name)).setText(recipe.getName());
        ((TextView) view.findViewById(R.id.main_item_text)).setText(recipe.getText());
        return view;

    }
}
