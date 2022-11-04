package com.example.donapp.Entity;

public class Localidad {
    private int id;
    private String nombre;
    private Boolean estado;

    public Localidad() {
        this.estado = true;
    }

    public Localidad(String localidad) {
        this.nombre = localidad;
        this.estado = true;
    }

    public Localidad(int id, String localidad, Boolean estado) {
        this.id = id;
        this.nombre = localidad;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalidad() {
        return nombre;
    }

    public void setLocalidad(String localidad) {
        this.nombre = localidad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
