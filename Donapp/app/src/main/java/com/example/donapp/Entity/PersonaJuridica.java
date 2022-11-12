package com.example.donapp.Entity;

import android.text.format.Time;

public class PersonaJuridica extends Persona{
    String cuil;
    String horarioInicio;
    String horarioFin;

    public PersonaJuridica(){

    }

    public PersonaJuridica(int id,
                           String nombre,
                           int telefono,
                           String direccion,
                           Provincia provincia,
                           Localidad localidad,
                           String cuil,
                           String horarioInicio,
                           String horarioFin){
        super(id, nombre, telefono, direccion, provincia, localidad);
        this.cuil = cuil;
        this.horarioInicio = horarioInicio;
        this.horarioFin = horarioFin;
        this.isJuridica = true;
    }

    public PersonaJuridica(String nombre,
                           int telefono,
                           String direccion,
                           Provincia provincia,
                           Localidad localidad,
                           String cuil,
                           String horarioInicio,
                           String horarioFin){
        super(nombre, telefono, direccion, provincia, localidad);
        this.cuil = cuil;
        this.horarioInicio = horarioInicio;
        this.horarioFin = horarioFin;
        this.isJuridica = true;
    }

    public PersonaJuridica(Persona persona, String cuil, String horarioInicio, String horarioFin){
        this.nombre = persona.nombre;
        this.direccion = persona.direccion;
        this.provincia = persona.getProvincia();
        this.telefono = persona.getTelefono();
        this.localidad = persona.getLocalidad();
        this.cuil = cuil;
        this.horarioInicio = horarioInicio;
        this.horarioFin = horarioFin;
        this.isJuridica = true;
    }

    public PersonaJuridica(int id){
        this.id = id;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFin() {
        return horarioFin;
    }

    public void setHorarioFin(String horarioFin) {
        this.horarioFin = horarioFin;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + '\n' +
                "Telefono: " + telefono + '\n' +
                "Direccion: " + direccion + '\n' +
                "Cuil: " + cuil + '\n' +
                "HorarioInicio: " + horarioInicio + '\n' +
                "HorarioFin: " + horarioFin + '\n';
    }
}
