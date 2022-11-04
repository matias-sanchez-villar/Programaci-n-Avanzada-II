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
                ? EstadosConst.ACTIVO
                : this == CONFIRMADO ? EstadosConst.CONFIRMADO
                : ErrorConst.ESTADO_INVALIDO;
    }
}
