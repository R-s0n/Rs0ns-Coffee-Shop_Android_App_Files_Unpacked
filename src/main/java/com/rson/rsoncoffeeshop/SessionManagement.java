package com.rson.rsoncoffeeshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(User user) {
        Long id = user.getId();
        editor.putLong(SESSION_KEY,id).commit();
    }

    public Long getSession(){
        return sharedPreferences.getLong(SESSION_KEY,-1);
    }

    public void removeSession(){
        editor.putLong(SESSION_KEY, -1).commit();
    }
}
