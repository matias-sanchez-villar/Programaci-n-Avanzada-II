package com.example.donapp.Enums;

import com.example.donapp.Interfaces.ICategoria;
import com.example.donapp.Util.CategoriasConst;
import com.example.donapp.Util.ErrorConst;

public enum Categoria implements ICategoria {
    SOLICITUD,
    CAMPANIA,
    BANCO;

    @Override
    public String displayCategoria() {
        return this == SOLICITUD
                ? CategoriasConst.SOLICITUD
                : this == CAMPANIA ? CategoriasConst.CAMPANIA
                : this == BANCO ? CategoriasConst.BANCO
                : ErrorConst.CATEGORIA_INVALIDA;
    }

    public static Categoria getCategoria(int pos){
        return pos == SOLICITUD.ordinal()
                ? SOLICITUD
                : pos == CAMPANIA.ordinal()
                ? CAMPANIA
                : BANCO;
    }
}
