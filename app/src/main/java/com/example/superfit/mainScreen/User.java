package com.example.superfit.mainScreen;

public class User {
    private String name;
    private String email;
    private String code;
    private boolean signedUp;
    private String weight = "Undefined";
    private String height = "Undefined";



    public User(String name, String email, String code, boolean signedUp) {
        this.name = name;
        this.email = email;
        this.code = code;
        this.signedUp = signedUp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSignedUp() {
        return signedUp;
    }

    public void setSignedUp(boolean signedUp) {
        this.signedUp = signedUp;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public void setWeight(String weight) {
        this.weight = weight + " kg";
    }

    public void setHeight(String height) {
        this.height = height + " cm";
    }


}
