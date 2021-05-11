package recipes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.superfit.R;

import java.util.ArrayList;

public class CustomAdapterRecipes extends BaseAdapter {

    ArrayList<RecipeObject> recipes = new ArrayList<>();
    LayoutInflater inflater;
    Context context;

    public CustomAdapterRecipes(Context context, ArrayList<RecipeObject> recipes){
        this.context = context;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        if(view == null){
            view = inflater.inflate(R.layout.recipes_item, null, false);
        }

        RecipeObject recipe = recipes.get(position);

        ((TextView) view.findViewById(R.id.recipes_item_name)).setText(recipe.getLabel());
        ((TextView) view.findViewById(R.id.recipes_item_text_kcal)).setText(recipe.getCalories());
        ((TextView) view.findViewById(R.id.recipes_item_text_fat)).setText(recipe.getDigest()[0]);
        ((TextView) view.findViewById(R.id.recipes_item_text_protein)).setText(recipe.getDigest()[1]);
        ((TextView) view.findViewById(R.id.recipes_item_text_carbs)).setText(recipe.getDigest()[2]);

        try {
            ((ImageView) view.findViewById(R.id.recipes_item_image)).setImageBitmap(recipe.getImage());
        } catch (Exception e) {
            Log.e("CustomAdapter", " " + e.getMessage());
        }

        return view;
    }
}
