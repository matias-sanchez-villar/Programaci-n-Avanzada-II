package com.example.donapp.Entity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.donapp.Enums.TipoUsuario;

public class GlobalPreferences {

    private GlobalPreferences(){

    }

    public static int getLoggedUserId(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt("idUsuario",0);
    }

    public static Usuario getLoggedUserNamePass(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Usuario usuario = new Usuario(
                preferences.getString("nombreUsuario",""),
                preferences.getString("passwordUsuario","")
        );
        return usuario;
    }

    public static TipoUsuario getLoggedTipoUsuario(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return TipoUsuario.getTipoUsuario(preferences.getInt("tipoUsuario", 0));
    }

    public static void restartPreferences(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("mailUsuario", "");
        edit.putString("nombreUsuario", "");
        edit.putInt("idUsuario", -1);
        edit.putInt("tipoUsuario", -1);
        edit.apply();
    }
}
