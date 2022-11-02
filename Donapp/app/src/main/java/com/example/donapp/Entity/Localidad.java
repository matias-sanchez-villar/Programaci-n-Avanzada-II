package com.example.donapp.Entity;

public class Localidad {
    private int id;
    private String localidad;
    private Boolean estado;

    public Localidad() {
        this.estado = true;
    }

    public Localidad(int id, String localidad, Boolean estado) {
        this.id = id;
        this.localidad = localidad;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
