package com.example.unidad3_tp3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarUsuario {

    public static final String emailRegex = "([a-zA-Z0-9_-]+@[a-zA-Z0-9]+.[a-z]{2,4})";
    public static final String nombreRegex = "^[A-Za-z]{1,20}$";
    public static final String passwordRegex = "([a-zA-Z]*)([0-9]*)([@#$%^&+=]*)(?=\\S+$).{3,}";

    public static boolean nombre (String nombre) {
        Pattern pattern = Pattern.compile(nombreRegex);
        Matcher mather = pattern.matcher(nombre);
        return  mather.matches();
    }

    public static boolean email (String email) {
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher mather = pattern.matcher(email);
        return  mather.matches();
    }

    public static boolean password (String password){
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher mather = pattern.matcher(password);
        return  mather.matches();
    }

}
