package com.example.superfit.Recipes;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Recipe {
    private String name;
    private String calories;
    private String protein;
    private String fat;
    private String carbs;
    private ImageView img;

    /*public Recipe (String name, String calories, String protein, String fat, String carbs){
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }*/

    public String getName(){
        return name;
    }
    public String getCalories(){
        return calories;
    }
    public String getProtein(){
        return protein;
    }
    public String getFat(){
        return fat;
    }
    public String getCarbs(){
        return carbs;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setCalories(String calories){
        this.calories = calories;
    }
    public void setProtein(String protein){
        this.protein = protein;
    }
    public void setFat(String fat){
        this.fat = fat;
    }
    public void setCarbs(String carbs){
        this.carbs = carbs;
    }
    /*public void setImage(Bitmap bitmap){
        img.setImageBitmap(bitmap);
    }*/
}
