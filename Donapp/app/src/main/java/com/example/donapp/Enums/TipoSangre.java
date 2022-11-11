package com.example.donapp.Enums;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.donapp.R;

import java.util.ArrayList;
import java.util.Arrays;

public enum TipoSangre {
    AP ("A+", 0),
    AN ("A-", 1),
    BP ("B+", 2),
    BN ("B-", 3),
    OP ("0+", 4),
    ON ("0-", 5),
    ABP ("AB+", 6),
    ABN ("AB-", 7);

    private String tipoSangre;
    private int posicion;

    TipoSangre(String tipoSangre, int posicion){
        this.tipoSangre = tipoSangre;
        this.posicion = posicion;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public int getPosicion() {
        return posicion;
    }

    public static TipoSangre getTipoSangreByPosition(int pos){
        switch (pos){
            case 0: return AP;
            case 1: return AN;
            case 2: return BP;
            case 3: return BN;
            case 4: return OP;
            case 5: return ON;
            case 6: return ABP;
            case 7: return ABN;
            default: return AP;
        }
    }

    public static TipoSangre getTipoSangreByString(String tipoSangre){
        switch (tipoSangre){
            case "A+": return AP;
            case "A-": return AN;
            case "B+": return BP;
            case "B-": return BN;
            case "0+": return OP;
            case "0-": return ON;
            case "AB+": return ABP;
            case "AB-": return ABN;
            default: return AP;
        }
    }

    public static String getStringByPosition(int pos){
        switch (pos){
            case 0: return AP.getTipoSangre();
            case 1: return AN.getTipoSangre();
            case 2: return BP.getTipoSangre();
            case 3: return BN.getTipoSangre();
            case 4: return OP.getTipoSangre();
            case 5: return ON.getTipoSangre();
            case 6: return ABP.getTipoSangre();
            case 7: return ABN.getTipoSangre();
            default: return AP.getTipoSangre();
        }
    }

    public static ArrayAdapter<String> getSpinnerAdapter(Context ct){
        ArrayList<String> values = new ArrayList<String>(){
            {
                add(AP.getTipoSangre());
                add(AN.getTipoSangre());
                add(BP.getTipoSangre());
                add(BN.getTipoSangre());
                add(OP.getTipoSangre());
                add(ON.getTipoSangre());
                add(ABP.getTipoSangre());
                add(ABN.getTipoSangre());
            }
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                ct,
                android.R.layout.simple_spinner_item,
                values
        );
        return adapter;
    }
}
