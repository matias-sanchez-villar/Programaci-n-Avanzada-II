package com.example.donapp.Util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

    private final static String formatoEntrada = "dd/MM/yyyy";
    private final static String formatoSalida = "yyyy-MM-dd";

    public static Date convertToSqlDate(String stringFechaEntrada){
        Log.i("TAG", "stringFechaEntrada :" +  stringFechaEntrada);
        //Definimos formato del string que ingresamos.
        SimpleDateFormat sdf = new SimpleDateFormat(formatoEntrada);
        try {
            Date date = sdf.parse(stringFechaEntrada);
            //Definimos formato del string que deseamos obtener.
            sdf = new SimpleDateFormat(formatoSalida);
            String stringFechaSalida = sdf.format(date);
            Log.i("TAG", "stringFechaSalida :" +  stringFechaSalida);
            Date dateSalida = sdf.parse(stringFechaSalida);
            //Log.i("TAG", "dateSalida :" +  dateSalida);
            return dateSalida;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
