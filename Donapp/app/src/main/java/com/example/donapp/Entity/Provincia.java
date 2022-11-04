package com.example.donapp.Entity;

import java.util.ArrayList;

public class Provincia extends EntidadEstadoBase {
    private String nombre;
    private ArrayList<Localidad> localidad = new ArrayList<Localidad>();

    public Provincia() {
        super();
    }

    public Provincia(String provincia, ArrayList<Localidad> localidad) {
        super();
        this.nombre = provincia;
        this.localidad = localidad;
    }

    public Provincia(int id, String provincia, ArrayList<Localidad> localidad, Boolean estado) {
        super(id);
        this.nombre = provincia;
        this.localidad = localidad;
    }

    public String getProvincia() {
        return nombre;
    }

    public void setProvincia(String provincia) {
        this.nombre = provincia;
    }

    public ArrayList<Localidad> getLocalidad() {
        return localidad;
    }

    public void setLocalidad(ArrayList<Localidad> localidad) {
        this.localidad = localidad;
    }
}
