package com.example.donapp.Interfaces;

import com.example.donapp.Enums.Categoria;

import java.util.Date;

public interface IRegistroPostulable {

    String getDireccionPostulacion();
    String getProvinciaPostulacion();
    String getLocalidadPostulacion();
    Categoria getCategoriaPostulacion();
    int getIdRegistro();

}
