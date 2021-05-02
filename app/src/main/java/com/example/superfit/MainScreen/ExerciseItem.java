package com.example.superfit.MainScreen;


public class ExerciseItem {

    private String name;
    private String description;
    private int image;

    public ExerciseItem(String name, String description, int image){
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public int getImage(){
        return image;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setImage(int image){
        this.image = image;
    }
}
