package com.example.donapp.Entity;

import java.util.Date;

public class Campana {
    private int id;
    private String cuitEmpresa; //para probar
    private String nombreCampana;
    private Date fecha;
    private String direccion;
    private Localidad localidad;
    private Provincia provincia;
    private int cantSolicitante;
    private int cantDias;
    private Boolean estado;
    private int UsuarioEmpresa;

    public Campana() {this.estado = true;}

    public Campana(String nombreCampana, Date fecha, String direccion, Provincia provincia, int cantSolicitante, int cantDias) {
        //Falta el ID empresa de session
        this.nombreCampana = nombreCampana;
        this.fecha = fecha;
        this.direccion = direccion;
        this.provincia = provincia;
        this.cantSolicitante = cantSolicitante;
        this.cantDias = cantDias;
    }

    public Campana(String nombreCampana, Date fecha, String direccion, Localidad localidad, Provincia provincia, int cantSolicitante, int cantDias, Usuario usuarioEmpresa) {
        this.nombreCampana = nombreCampana;
        this.fecha = fecha;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.cantSolicitante = cantSolicitante;
        this.cantDias = cantDias;
        this.estado = true;
        UsuarioEmpresa = usuarioEmpresa.getId();
    }

    public int getUsuarioEmpresa() {
        return UsuarioEmpresa;
    }

    public void setUsuarioEmpresa(int usuarioEmpresa) {
        UsuarioEmpresa = usuarioEmpresa;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCuitEmpresa() {
        return cuitEmpresa;
    }

    public void setCuitEmpresa(String cuitEmpresa) {
        this.cuitEmpresa = cuitEmpresa;
    }

    public String getNombreCampana() {
        return nombreCampana;
    }

    public void setNombreCampana(String nombreCampana) {
        this.nombreCampana = nombreCampana;
    }

    public Date getFecha() {return fecha;}

    public void setFecha(Date fecha) {this.fecha = fecha;}

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

    public int getCantSolicitante() {
        return cantSolicitante;
    }

    public void setCantSolicitante(int cantSolicitante) {
        this.cantSolicitante = cantSolicitante;
    }

    public int getCantDias() {
        return cantDias;
    }

    public void setCantDias(int cantDias) {
        this.cantDias = cantDias;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
