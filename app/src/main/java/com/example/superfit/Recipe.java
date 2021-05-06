package com.example.superfit;

import android.graphics.Bitmap;

public class Recipe  {
    Bitmap iv_recipe;
    String tv_recipe_name, tv_recipe_value, tv_protein, tv_fat, tv_carbs, tv_ingredient;
    //Array tv_ingredient;

    public Bitmap getIv_recipe() {
        return iv_recipe;
    }

    public String getTv_recipe_name() {
        return tv_recipe_name;
    }

    public String getTv_recipe_value() {
        return tv_recipe_value;
    }

    public String getTv_protein() {
        return tv_protein;
    }

    public String getTv_fat() {
        return tv_fat;
    }

    public String getTv_carbs() {
        return tv_carbs;
    }

    public void setIv_recipe(Bitmap iv_recipe) {
        this.iv_recipe = iv_recipe;
    }

    public void setTv_recipe_name(String tv_recipe_name) {
        this.tv_recipe_name = tv_recipe_name;
    }

    public void setTv_recipe_value(String tv_recipe_value) { this.tv_recipe_value = tv_recipe_value;
    }

    public void setTv_protein(String tv_protein) {
        this.tv_protein = tv_protein;
    }

    public void setTv_fat(String tv_fat) {
        this.tv_fat = tv_fat;
    }

    public void setTv_carbs(String tv_carbs) {
        this.tv_carbs = tv_carbs;
    }

    public String getTv_ingredient() { return tv_ingredient;}

    public void setTv_ingredient(String tv_ingredient) {this.tv_ingredient = tv_ingredient;}

   // public Array getTv_ingredient() { return tv_ingredient;}
    //public void setTv_ingredient(Array tv_ingredient) {this.tv_ingredient = tv_ingredient;}
}
