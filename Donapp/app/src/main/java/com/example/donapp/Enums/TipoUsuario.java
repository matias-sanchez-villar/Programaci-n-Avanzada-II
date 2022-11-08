package com.example.donapp.Enums;

public enum TipoUsuario {
    SOLICITANTE,
    DONANTE,
    EMPRESA,
    INSTITUCION;

    public static TipoUsuario getTipoUsuario(int tipoUsuarioInt){
        switch (tipoUsuarioInt){
            case 0: { return SOLICITANTE; }
            case 1: return DONANTE;
            case 2: return EMPRESA;
            case 3: return INSTITUCION;
            default: return null;
        }
    }
}
