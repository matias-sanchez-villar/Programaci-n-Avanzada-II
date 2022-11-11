package com.example.donapp.Enums;

import com.example.donapp.Interfaces.IEstado;
import com.example.donapp.Util.ErrorConst;

public enum EstadoCampania implements IEstado {
    CANCELADA,
    ACTIVA;

    @Override
    public String displayEstado() {
        return this == CANCELADA
                ? CANCELADA.name()
                : this == ACTIVA ? ACTIVA.name()
                : ErrorConst.ESTADO_INVALIDO;
    }

    public static EstadoCampania getTipoEstadoCampania(int estado){
        return estado == CANCELADA.ordinal()
                ? CANCELADA
                : ACTIVA;
    }
    public static int getTipoEstadoToInt(IEstado estado){
        return estado.equals(ACTIVA.displayEstado()) ? ACTIVA.ordinal() : CANCELADA.ordinal();
    }
}
