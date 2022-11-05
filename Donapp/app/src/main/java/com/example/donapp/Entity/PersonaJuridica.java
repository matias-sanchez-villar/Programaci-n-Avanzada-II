package com.example.donapp.Entity;

import android.text.format.Time;

public class PersonaJuridica extends Persona{
    String cuil;
    String horarioInicio;
    String horarioFin;

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
}
