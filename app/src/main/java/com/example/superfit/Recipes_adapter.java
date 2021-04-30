package com.example.superfit;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class Recipes_adapter extends ArrayAdapter<String> {

    private final Context context;
    private int[] textureArray;
    private String[] name;
    private String[] kcal;
    private String[] protein;
    private String[] fat;
    private String[] carbs;

    public Recipes_adapter(Context context, int[] textureArray , String[] name, String[] kcal,String[] protein,
                                String[] fat, String[] carbs) {
        super(context, R.layout.recipes_item, textureArray, name, kcal, protein, fat, carbs);
        this.context = context;
        this.textureArray = textureArray;
        this.name = name;
        this.kcal = kcal;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public Recipes_adapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values[position]);
        // Изменение иконки для Windows и iPhone
        String s = values[position];
        if (s.startsWith("Windows7") || s.startsWith("iPhone")
                || s.startsWith("Solaris")) {
            imageView.setImageResource(R.drawable.no);
        } else {
            imageView.setImageResource(R.drawable.ok);
        }

        return rowView;
    }
}
