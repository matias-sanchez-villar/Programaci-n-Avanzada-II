package com.example.unidad3_tp3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarUsuario {

    public static final String emailRegex = "^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@\"\n\"[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$";
    public static final String nombreRegex = "^[A-Za-z]{1,20}$";
    public static final String passwordRegex = "";

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
