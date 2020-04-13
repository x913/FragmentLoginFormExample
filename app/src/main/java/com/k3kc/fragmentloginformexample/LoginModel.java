package com.k3kc.fragmentloginformexample;

import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("id")
    public int Id;

    @SerializedName("name")
    public String Name;

    @SerializedName("username")
    public String Username;

    @SerializedName("email")
    public String Email;

    @SerializedName("website")
    public String Website;

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getUsername() {
        return Username;
    }

    public String getEmail() {
        return Email;
    }

    public String getWebsite() {
        return Website;
    }
}
