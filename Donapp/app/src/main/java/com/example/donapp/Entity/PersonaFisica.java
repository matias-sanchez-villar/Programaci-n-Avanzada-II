package com.example.donapp.Entity;

import java.util.Date;

public class PersonaFisica extends Persona {
    String apellido;
    Date fechaNacimiento;
    int dni;

    public PersonaFisica(){
        this.isJuridica = false;
    }

    public PersonaFisica(Persona persona, String apellido, Date fechaNacimiento, int dni){
        this.isJuridica = false;
        this.nombre = persona.nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
        this.id = persona.getId();
    }

    public PersonaFisica(
            int id,
            String nombre,
            int telefono,
            String direccion,
            Provincia provincia,
            Localidad localidad,
            String apellido,
            Date fechaNacimiento,
            int dni
    ){
        super(id, nombre, telefono, direccion, provincia, localidad);
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
        this.isJuridica = false;
    }

    public PersonaFisica(
            String nombre,
            int telefono,
            String direccion,
            Provincia provincia,
            Localidad localidad,
            String apellido,
            Date fechaNacimiento,
            int dni
    ){
        super(nombre, telefono, direccion, provincia, localidad);
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
        this.isJuridica = false;
    }

    public PersonaFisica(int id){
        this.id = id;
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
}
