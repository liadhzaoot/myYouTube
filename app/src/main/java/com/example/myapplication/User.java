package com.example.myapplication;

import android.net.Uri;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    private String email;
    private String pass;
    private ArrayList<Uri> favoriteList;
    public User(String email,String pass,ArrayList<Uri> favoriteList)
    {
     this.favoriteList = favoriteList;
     this.email = email;
     this.pass = pass;
    }

    public ArrayList<Uri> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(ArrayList<Uri> favoriteList) {
        this.favoriteList = favoriteList;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public User() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public boolean checkUser(String email,String pass)
    {
        if(this.getEmail().equals(email) && this.getPass().equals(pass))
            return true;
        return false;
    }

}
