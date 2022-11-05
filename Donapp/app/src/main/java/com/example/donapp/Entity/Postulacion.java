package com.example.donapp.Entity;

import com.example.donapp.Enums.EstadoPostulacion;
import com.example.donapp.Enums.Categoria;

import java.util.Date;

public class Postulacion extends EntidadEstadoBase{
    Date fechaGeneracion;
    String codigo;
    Usuario usuario;
    EstadoPostulacion estado;
    Categoria categoria;
    Date fechaConfirmacion;

    public Postulacion(){

    }

}
