package com.example.superfit.Recipes;

public class Ingredient {
    private String ingredient;

    public Ingredient (String name){
        ingredient = name;
    }

    public String getIngredient(){
        return ingredient;
    }

    public void setIngredient(String ingredient){
        this.ingredient = ingredient;
    }
}
