package com.example.donapp.Enums;

import com.example.donapp.Interfaces.IEstado;
import com.example.donapp.Util.ErrorConst;
import com.example.donapp.Util.EstadosConst;

public enum EstadoPostulacion implements IEstado {
     ACTIVO,
     CONFIRMADO;

    @Override
    public String displayEstado() {
        return this == ACTIVO
                ? ACTIVO.name()
                : this == CONFIRMADO ? CONFIRMADO.name()
                : ErrorConst.ESTADO_INVALIDO;
    }

    public static EstadoPostulacion getTipoEstadoPostulacion(int estado){
        return estado == ACTIVO.ordinal()
                ? ACTIVO
                : CONFIRMADO;
    }
}
