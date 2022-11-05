package com.example.donapp.Entity;

import com.example.donapp.Enums.Estado;
import com.example.donapp.Interfaces.IEstado;

public abstract class EntidadEstadoBase {
    int id;
    IEstado estado;

    public EntidadEstadoBase(){

    }

    public EntidadEstadoBase(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public IEstado getEstado(){
        return this.estado;
    }

    public void setEstado(IEstado estado){
        this.estado = estado;
    }
}
