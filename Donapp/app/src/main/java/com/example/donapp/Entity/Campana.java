package com.example.donapp.Entity;

public class Campana {
    private int id;
    private int idEmpresa;
    private String nombreCampana;
    private String fecha;
    private Provincia provincia;
    private int cantSolicitante;
    private int cantDias;
    private Boolean estado;

    public Campana() {this.estado = true;}


    public Campana(String nombreCampana, String fecha, Provincia provincia, int cantSolicitante, int cantDias) {
        //Falta el ID empresa de session
        this.nombreCampana = nombreCampana;
        this.fecha = fecha;
        this.provincia = provincia;
        this.cantSolicitante = cantSolicitante;
        this.cantDias = cantDias;
        this.estado = true;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreCampana() {
        return nombreCampana;
    }

    public void setNombreCampana(String nombreCampana) {
        this.nombreCampana = nombreCampana;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
