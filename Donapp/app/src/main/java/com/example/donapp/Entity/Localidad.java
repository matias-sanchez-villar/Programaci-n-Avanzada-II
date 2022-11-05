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

    public Localidad(int id, String localidad, Boolean estado) {
        super(id);
        this.nombre = localidad;
    }

    public String getLocalidad() {
        return nombre;
    }

    public void setLocalidad(String localidad) {
        this.nombre = localidad;
    }
    }
