package com.example.donapp.Entity;

public class BancoSangre extends EntidadBase{
    String hospital;
    String direccion;
    Provincia provincia;
    Localidad localidad;

    public BancoSangre() {
        super();
    }

    public BancoSangre(String hospital, String direccion, Provincia provincia, Localidad localidad) {
        this.hospital = hospital;
        this.direccion = direccion;
        this.provincia = provincia;
        this.localidad = localidad;
    }

    public BancoSangre(int id, String hospital, String direccion, Provincia provincia, Localidad localidad) {
        super(id);
        this.hospital = hospital;
        this.direccion = direccion;
        this.provincia = provincia;
        this.localidad = localidad;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
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
