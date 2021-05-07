package com.example.superfit;

import android.graphics.Bitmap;

public class Recipe_Item {
    Bitmap iv_recipe;
    String tv_recipe_name, tv_recipe_value, tv_protein, tv_fat, tv_carbs, tv_ingredient, tv_q;
    //ArrayList it_ing;
    //ListView it_ing;

    public Bitmap getIv_recipe() { return iv_recipe; }

    public String getTv_recipe_name() { return tv_recipe_name; }

    public String getTv_recipe_value() { return tv_recipe_value; }

    public String getTv_protein() { return tv_protein; }

    public String getTv_fat() { return tv_fat; }

    public String getTv_carbs() { return tv_carbs; }

    //public ArrayList getTv_ing() {return it_ing;}
    //public ListView getIt_ing() {return it_ing;}

    public void setIv_recipe(Bitmap iv_recipe) { this.iv_recipe = iv_recipe; }

    public void setTv_recipe_name(String tv_recipe_name) { this.tv_recipe_name = tv_recipe_name; }

    public void setTv_recipe_value(String tv_recipe_value) { this.tv_recipe_value = tv_recipe_value; }

    public void setTv_protein(String tv_protein) { this.tv_protein = tv_protein; }

    public void setTv_fat(String tv_fat) { this.tv_fat = tv_fat; }

    public void setTv_carbs(String tv_carbs) { this.tv_carbs = tv_carbs; }

    public String getTv_ingredient() { return tv_ingredient;}
    public void setTv_ingredient(String tv_ingredient) {this.tv_ingredient = tv_ingredient;}

    //public String getTv_q() {return tv_q;}
    //public void setTv_q(String tv_q) {this.tv_q = tv_q;}

    //public void setTv_ing(ArrayList tv_ing) {this.it_ing = tv_ing;}
    //public void  setIt_ing(ListView it_ing) {this.it_ing = it_ing;}

}

