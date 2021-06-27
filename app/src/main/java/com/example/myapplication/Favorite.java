package com.example.myapplication;

public class Favorite {
    private String uriPath;
    private String email;

    public Favorite(String uriPath, String email) {
        this.uriPath = uriPath;
        this.email = email;
    }

    public Favorite() {

    }

    public String getUriPath() {
        return uriPath;
    }

    public void setUriPath(String uriPath) {
        this.uriPath = uriPath;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
