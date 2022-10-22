package com.example.unidad4.Entity;

public class Categoria {

    private int id;
    private String descripcion;

    public Categoria(){

    }

    public Categoria(int id, String descripcion){
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId(){
        return this.id;
    }

    public String getDescripcion(){
        return this.descripcion;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    @Override
    public String toString(){
        return this.descripcion;
    }


}
