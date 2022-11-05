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
                ? ACTIVO.name()
                : this == INACTIVO ? INACTIVO.name()
                : ErrorConst.ESTADO_INVALIDO;
    }

}
