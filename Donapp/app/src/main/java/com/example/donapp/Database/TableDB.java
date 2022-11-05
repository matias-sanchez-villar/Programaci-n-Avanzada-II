package com.example.donapp.Database;

public final class TableDB {
    public static String USUARIO = "usuario";
    public static String CRITICIDAD = "criticidad";

    public static String SelectAll(String table){
        return "SELECT * FROM " + table;
    }
}
