package com.example.donapp.Entity;

public class Localidad extends EntidadBase{
    private String nombre;

    public Localidad() {
        super();
    }

    public Localidad(String localidad) {
        super();
        this.nombre = localidad;
    }

    public Localidad(int id){
        this.id = id;
    }

    public Localidad(int id, String localidad, Boolean estado) {
        super(id);
        this.nombre = localidad;
    }

    @Override
    public String toString(){
        return this.nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    }

