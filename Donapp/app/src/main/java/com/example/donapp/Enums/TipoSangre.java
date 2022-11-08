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
}
