package com.example.donapp.Enums;

import com.example.donapp.Interfaces.IEstado;
import com.example.donapp.Util.ErrorConst;
import com.example.donapp.Util.EstadosConst;

public enum Estado implements IEstado {
    ACTIVO,
    INACTIVO;

    @Override
    public String displayEstado() {
        return this == ACTIVO
                ? EstadosConst.ACTIVO
                : this == INACTIVO ? EstadosConst.INACTIVO
                : ErrorConst.ESTADO_INVALIDO;
    }

}
