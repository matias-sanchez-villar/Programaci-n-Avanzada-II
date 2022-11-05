package com.example.donapp.Entity;

import java.util.Date;

public class PersonaFisica extends Persona {
    String apellido;
    Date fechaNacimiento;

    public PersonaFisica(){

    }

    public PersonaFisica(
            int id,
            String nombre,
            int telefono,
            String direccion,
            Provincia provincia,
            Localidad localidad,
            String apellido,
            Date fechaNacimiento
    ){
        super(id, nombre, telefono, direccion, provincia, localidad);
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
