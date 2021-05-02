package com.example.glambeauty.model;

import android.content.Context;
import android.content.SharedPreferences;

public class User {
    String id;
    String username;
    String password;

    public static String GetUserId(Context context){
        String userId = "";
        String MY_PREFERENCES = "USER_DATA";
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCES, context.MODE_PRIVATE);

        if(!sharedPreferences.getString("user_id", "emptyString").matches("emptyString"))
        {
            userId = sharedPreferences.getString("user_id", "emptyString");
        }

        return userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
