package com.example.unidad3_tp3;

public class Utilities {

    public static String SQLString(String campo){
        return "'" + campo + "'";
    }

    public static int stringToNumber(String value){
        try{
            return Integer.valueOf(value);
        }catch (NumberFormatException e){
            return -1;
        }
    }
}
