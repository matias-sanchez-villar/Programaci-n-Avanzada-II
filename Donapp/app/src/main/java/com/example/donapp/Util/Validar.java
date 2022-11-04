package com.example.donapp.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * TODO:
 *       *   Estos metodos aun no han sido probados
 *           cualquier cosa me avisan o los prueban sin drama
 */

public class Validar {
    private static final String regexLetras = "[a-zA-Z]";
    private static final String regexEspacio = "\\s";
    private static final String regexNumeros = "[0-9]";
    private static final String regexCaracteresEspeciales = "\\W";

    public static boolean letras(String string) {
        Pattern pattern = Pattern.compile(regexLetras);
        Matcher mather = pattern.matcher(string);
        return mather.matches();
    }

    public static boolean espacios(String string) {
        Pattern pattern = Pattern.compile(regexEspacio);
        Matcher mather = pattern.matcher(string);
        return mather.matches();
    }

    public static boolean numeros(String string) {
        Pattern pattern = Pattern.compile(regexNumeros);
        Matcher mather = pattern.matcher(string);
        return mather.matches();
    }

    public static boolean caracteres(String string) {
        Pattern pattern = Pattern.compile(regexCaracteresEspeciales);
        Matcher mather = pattern.matcher(string);
        return mather.matches();
    }
}
