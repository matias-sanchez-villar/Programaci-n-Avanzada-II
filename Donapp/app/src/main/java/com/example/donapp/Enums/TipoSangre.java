package com.example.donapp.Enums;

public enum TipoSangre {
    AP ("A +", 0),
    AN ("A -", 1),
    BP ("B +", 2),
    BN ("B -", 3),
    OP ("O +", 4),
    ON ("O -", 5),
    ABP ("AB +", 6),
    ABN ("AB -", 7);

    private String tipoSangre;
    private int enumString;

    TipoSangre(String tipoSangre, int enumString){
        this.tipoSangre = tipoSangre;
        this.enumString = enumString;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public int getEnumString() {
        return enumString;
    }
}
