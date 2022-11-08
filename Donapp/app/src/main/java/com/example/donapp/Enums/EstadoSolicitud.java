package com.example.donapp.Enums;

import com.example.donapp.Interfaces.IEstado;
import com.example.donapp.Util.ErrorConst;

public enum EstadoSolicitud implements IEstado {
    CANCELADA,
    ACTIVA;

    @Override
    public String displayEstado() {
        return this == CANCELADA
                ? CANCELADA.name()
                : this == ACTIVA ? ACTIVA.name()
                : ErrorConst.ESTADO_INVALIDO;
    }

    public static EstadoSolicitud getTipoEstadoSolicitud(int estado){
        return estado == CANCELADA.ordinal()
                ? CANCELADA
                : ACTIVA;
    }
}
