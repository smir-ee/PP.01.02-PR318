package com.example.superfit;

public class User {
   private String name;
   private String email;
   private String code;
   private boolean signedUp;

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


}
