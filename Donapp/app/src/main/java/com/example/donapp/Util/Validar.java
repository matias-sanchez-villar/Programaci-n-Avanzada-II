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
    private static final String regexDate = "^(?:3[01]|[12][0-9]|0?[1-9])([\\-/.])(0?[1-9]|1[1-2])\\1\\d{4}$";

    public static boolean contLetras(String string) {
        if (string.isEmpty())
            return false;
        Pattern pattern = Pattern.compile(regexLetras);
        Matcher mather = pattern.matcher(string);
        return mather.matches();
    }

    public static boolean contEspacios(String string) {
        if (string.isEmpty())
            return false;
        Pattern pattern = Pattern.compile(regexEspacio);
        Matcher mather = pattern.matcher(string);
        return mather.matches();
    }

    public static boolean contNumeros(String string) {
        if (string.isEmpty())
            return false;
        Pattern pattern = Pattern.compile(regexNumeros);
        Matcher mather = pattern.matcher(string);
        return mather.matches();
    }

    public static boolean contCaracteres(String string) {
        if (string.isEmpty())
            return false;
        Pattern pattern = Pattern.compile(regexCaracteresEspeciales);
        Matcher mather = pattern.matcher(string);
        return mather.matches();
    }

    public static boolean texto(String string){
        if (!contEspacios(string))
            return false;
        if (!contNumeros(string))
            return false;
        if (!contCaracteres(string))
            return false;
        return true;
    }

    public static boolean textoConEspacios(String string){
        if (!contCaracteres(string))
            return false;
        if (!contNumeros(string))
            return false;
        return true;
    }

    public static boolean textoConEspaciosNumeros(String string){
        if (!contCaracteres(string))
            return false;
        return true;
    }

    public static boolean numeros(String string){
        if (!contCaracteres(string))
            return false;
        if (!contLetras(string))
            return false;
        if (!contEspacios(string))
            return false;
        return true;
    }

    public static boolean date(String string){
        if (!contLetras(string))
            return false;
        if (!contEspacios(string))
            return false;
        Pattern pattern = Pattern.compile(regexDate);
        Matcher mather = pattern.matcher(string);
        return mather.matches();
    }
}
