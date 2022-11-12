package com.example.donapp.Entity;

import com.example.donapp.Enums.Categoria;
import com.example.donapp.Enums.EstadoCampania;
import com.example.donapp.Interfaces.IEstado;
import com.example.donapp.Interfaces.IRegistroPostulable;
import com.example.donapp.Util.CodigoGenerator;
import com.example.donapp.Util.DateUtil;

import java.util.Date;

public class Campania extends EntidadEstadoBase implements IRegistroPostulable {

    private String codigo;
    private String nombreCampania;
    private Date fecha;
    private Date fechaFin;
    private String direccion;
    private Localidad localidad;
    private Provincia provincia;
    private int cantDonantes;
    private int cantDonantesConfirmados;
    private Usuario institucion;
    private int cantDias;
    private Usuario usuario;

    public Campania() { }

    public Campania(int id) {super(id);}

    public Campania(String nombreCampana,
                    Date fecha,
                    Date fechaFin,
                    Provincia provincia,
                    String direccion,
                    int cantDonantes,
                    int cantDias) {
        //Falta el ID empresa de session
        this.nombreCampania = nombreCampana;
        this.fecha = fecha;
        this.fechaFin = fechaFin;
        this.provincia = provincia;
        this.direccion = direccion;
        this.cantDonantes = cantDonantes;
        this.cantDias = cantDias;
        setAutomaticCodigo();
    }

    public Campania(int id,
                    String nombreCampana,
                    Date fecha,
                    Provincia provincia,
                    String direccion,
                    int cantDonantes,
                    int cantDias) {
        this.id = id;
        this.nombreCampania = nombreCampana;
        this.fecha = fecha;
        this.provincia = provincia;
        this.direccion = direccion;
        this.cantDonantes = cantDonantes;
        this.cantDias = cantDias;
        setAutomaticCodigo();
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public String getAndroidFecha(){
        return DateUtil.convertToAndroidDate(fecha.toString());
    }

    public String getAndroidFechaFin(){
        return DateUtil.convertToAndroidDate(fechaFin.toString());
    }

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
    public Categoria getCategoriaPostulacion() {
        return Categoria.CAMPANIA;
    }

    @Override
    public int getIdRegistro() {
        return this.id;
    }
    public int getEstadoInt(){
        return EstadoCampania.getTipoEstadoToInt(this.estado);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCampania() {
        return nombreCampania;
    }

    public void setNombreCampania(String nombreCampania) {
        this.nombreCampania = nombreCampania;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getCantDonantes() {
        return cantDonantes;
    }

        /*public int getCantDonantes() {
        return cantDonantes;
    }

    public void setCantDonantes(int cantSolicitante) {
        this.cantDonantes = cantSolicitante;
    }*/

    public void setCantDonantes(int cantDonantes) {
        this.cantDonantes = cantDonantes;
    }

    public int getCantDonantesConfirmados() {
        return cantDonantesConfirmados;
    }

    public void setCantDonantesConfirmados(int cantDonantesConfirmados) {
        this.cantDonantesConfirmados = cantDonantesConfirmados;
    }

    public void setAutomaticCodigo(){
        this.codigo = CodigoGenerator.getAutomaticCode(CodigoGenerator.codigoSolicitudLenght);
    }

    public Usuario getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Usuario institucion) {
        this.institucion = institucion;
    }

    public void confirmarDonacion(){
        this.cantDonantesConfirmados++;
        if(this.cantDonantes == this.cantDonantesConfirmados){
            this.estado = EstadoCampania.COMPLETADA;
        }
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + '\n' +
                "Nombre: " + nombreCampania + '\n' +
                "Fecha: " + fecha.toString() + '\n' +
                "Fecha fin: " + fechaFin.toString() + '\n' +
                "Donantes solicitados: " + cantDonantes + '\n';
    }
}
