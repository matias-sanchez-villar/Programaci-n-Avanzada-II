package com.example.donapp.Entity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class GlobalPreferences {

    private GlobalPreferences(){

    }

    public static int getLoggedUserId(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt("idUsuario",0);
    }
}
