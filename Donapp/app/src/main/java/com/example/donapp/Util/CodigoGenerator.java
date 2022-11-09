package com.example.donapp.Util;

import java.util.concurrent.ThreadLocalRandom;

public class CodigoGenerator {
    private static String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static String codigoGenerated = "";

    public static int codigoSolicitudLenght = 6;

    public static String getAutomaticCode(int cantidadChars){
        for(int x = 0; x < cantidadChars; x++){
            int indiceAleatorio = numeroAleatorio(0, chars.length() - 1);
            char caracterAleatorio = chars.charAt(indiceAleatorio);
            codigoGenerated += caracterAleatorio;
        }
        return codigoGenerated;
    }

    private static int numeroAleatorio(int minimo, int maximo){
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }
}
