package com.example.donapp.Util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class CodigoGenerator {
    private static String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static int codigoSolicitudLenght = 6;
    public static int codigoCampaniaLeght = 5;

    public static String getAutomaticCode(int cantidadChars){
        String codigoGenerated = "";
        for(int x = 0; x < cantidadChars; x++){
            int indiceAleatorio = numeroAleatorio(0, chars.length());
            char caracterAleatorio = chars.charAt(indiceAleatorio);
            codigoGenerated += caracterAleatorio;
        }
        return codigoGenerated;
    }

    private static int numeroAleatorio(int minimo, int maximo){
        return new Random().nextInt(maximo);
    }
}
