package com.example.donapp.Entity;

public class BancoSangre extends EntidadBase{
    String hospita;
    String direccion;
    Provincia provincia;
    Localidad localidad;

    public BancoSangre() {
        super();
    }

    public BancoSangre(String hospita, String direccion, Provincia provincia, Localidad localidad) {
        this.hospita = hospita;
        this.direccion = direccion;
        this.provincia = provincia;
        this.localidad = localidad;
    }

    public BancoSangre(int id, String hospita, String direccion, Provincia provincia, Localidad localidad) {
        super(id);
        this.hospita = hospita;
        this.direccion = direccion;
        this.provincia = provincia;
        this.localidad = localidad;
    }

    public String getHospita() {
        return hospita;
    }

    public void setHospita(String hospita) {
        this.hospita = hospita;
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
}
