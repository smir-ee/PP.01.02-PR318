package com.example.superfit;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class Recipe implements Serializable {
    String label;
    String kcal;
    String protein;
    String fat;
    String carbs;
    String image;
    ArrayList<String> ingredients = new ArrayList<>();

    public Recipe(String label, String kcal, String protein, String fat, String carbs, String image, JSONArray ingredients) throws JSONException {
        this.label = label;
        this.kcal = kcal;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.image = image;
        for (int i = 0; i < ingredients.length(); i++) {
            this.ingredients.add(ingredients.getString(i));
        }
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getKcal() {
        return kcal.split("\\.")[0] + " kcal";
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
    public String getPFC(){
        return protein.split("\\.")[0] + "g protein • " + fat.split("\\.")[0]
                + "g fat • " + carbs.split("\\.")[0] + "g carbs" ;
    }

    @NonNull
    @Override
    public String toString() {
        return ingredients.get(0);
    }
}
