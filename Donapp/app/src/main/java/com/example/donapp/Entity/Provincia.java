package com.example.donapp.Entity;

import java.util.ArrayList;

public class Provincia {
    private int id;
    private String provincia;
    private ArrayList<Localidad> localidad = new ArrayList<Localidad>();
    private Boolean estado;

    public Provincia() {
        this.estado = true;
    }

    public Provincia(int id, String provincia, ArrayList<Localidad> localidad, Boolean estado) {
        this.id = id;
        this.provincia = provincia;
        this.localidad = localidad;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public ArrayList<Localidad> getLocalidad() {
        return localidad;
    }

    public void setLocalidad(ArrayList<Localidad> localidad) {
        this.localidad = localidad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
