package com.example.donapp.Entity;

import android.os.Parcelable;

import java.io.Serializable;

public abstract class Persona extends EntidadBase {
    String nombre;
    int telefono;
    String direccion;
    Provincia provincia;
    Localidad localidad;
    Boolean isJuridica;

    public Persona(){
    }

    public Persona(int id){
        this.id = id;
    }

    public Persona(int id,
                   String nombre,
                   int telefono,
                   String direccion,
                   Provincia provincia,
                   Localidad localidad){
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.provincia = provincia;
        this.localidad = localidad;
    }

    public Persona(String nombre,
                   int telefono,
                   String direccion,
                   Provincia provincia,
                   Localidad localidad){
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.provincia = provincia;
        this.localidad = localidad;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public Boolean getJuridica() {
        return isJuridica;
    }

    public void setJuridica(Boolean juridica) {
        isJuridica = juridica;
    }

}
