package com.example.donapp.Entity;

public class Criticidad extends EntidadBase{
    String descripcion;

    public Criticidad(){
        super();
    }

    public Criticidad(int id){
        super(id);
    }

    public Criticidad(String descripcion){
        this.descripcion = descripcion;
    }

    public Criticidad(int id, String descripcion){
        this.id = id;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }

    //TODO: TEMPORAL, BUSCAR DATOS DINAMICAMENTE.
}
