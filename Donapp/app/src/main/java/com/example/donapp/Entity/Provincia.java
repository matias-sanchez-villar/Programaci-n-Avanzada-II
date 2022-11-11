package com.example.donapp.Entity;

import java.util.ArrayList;

public class Provincia extends EntidadBase {
    private String nombre;
    private ArrayList<Localidad> localidad = new ArrayList<Localidad>();

    public Provincia() {
        super();
    }

    public Provincia(int id){
        this.id = id;
    }

    public Provincia(String nombre){
        this.nombre = nombre;
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

    public Provincia(int id, String nombre){
        super(id);
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String provincia) {
        this.nombre = provincia;
    }

    public ArrayList<Localidad> getLocalidad() {
        return localidad;
    }

    public void setLocalidad(ArrayList<Localidad> localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString(){
        return this.nombre;
    }
}
