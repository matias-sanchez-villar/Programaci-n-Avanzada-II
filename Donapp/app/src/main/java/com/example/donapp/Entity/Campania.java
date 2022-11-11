package com.example.donapp.Entity;

import com.example.donapp.Enums.EstadoCampania;
import com.example.donapp.Interfaces.IEstado;
import com.example.donapp.Interfaces.IRegistroPostulable;

import java.util.Date;

public class Campania extends EntidadEstadoBase implements IRegistroPostulable {

    private String nombreCampania;
    private Date fecha;
    private String direccion;
    private Localidad localidad;
    private Provincia provincia;
    private int cantSolicitante;
    private int cantDias;
    private Usuario UsuarioEmpresa;

    public Campania() { }

    public Campania(int id) {super(id);}

    public Campania(String nombreCampana,
                    Date fecha,
                    Provincia provincia,
                    String direccion,
                    int cantSolicitante,
                    int cantDias) {
        //Falta el ID empresa de session
        this.nombreCampania = nombreCampana;
        this.fecha = fecha;
        this.provincia = provincia;
        this.direccion = direccion;
        this.cantSolicitante = cantSolicitante;
        this.cantDias = cantDias;
    }

    public Campania(int id,
                    String nombreCampana,
                    Date fecha,
                    Provincia provincia,
                    String direccion,
                    int cantSolicitante,
                    int cantDias) {
        this.id = id;
        this.nombreCampania = nombreCampana;
        this.fecha = fecha;
        this.provincia = provincia;
        this.direccion = direccion;
        this.cantSolicitante = cantSolicitante;
        this.cantDias = cantDias;
    }


    public Usuario getUsuario() {
        return UsuarioEmpresa;
    }

    public void setUsuario(Usuario usuario) {
        UsuarioEmpresa = usuario;
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

    public String getNombreCampana() {
        return nombreCampania;
    }

    public void setNombreCampana(String nombreCampana) {
        this.nombreCampania = nombreCampana;
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

    public IEstado getEstado() {
        return estado;
    }

    public void setEstado(IEstado estado) {
        this.estado = estado;
    }

    @Override
    public String getDireccionPostulacion() {
        return direccion;
    }

    @Override
    public String getProvinciaPostulacion() {
        return provincia.getNombre();
    }

    @Override
    public String getLocalidadPostulacion() {
        return localidad.getNombre();
    }

    @Override
    public int getIdRegistro() {
        return this.id;
    }
    public int getEstadoInt(){
        return EstadoCampania.getTipoEstadoToInt(this.estado);
    }

}
