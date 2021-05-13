package com.example.superfit.exercises;

public class Exercise {
    int image;
    String label, text;

    public Exercise(int image, String label, String text) {
        this.image = image;
        this.label = label;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
