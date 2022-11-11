package com.example.donapp.Entity;

import com.example.donapp.Enums.EstadoSolicitud;
import com.example.donapp.Interfaces.IRegistroPostulable;
import com.example.donapp.Util.CodigoGenerator;
import com.example.donapp.Util.DateUtil;

import java.util.Date;

public class Solicitud extends EntidadEstadoBase implements IRegistroPostulable {
    private String codigo;
    private String nombre;
    private String apellido;
    private Date fecha;
    private Provincia provincia;
    private Localidad localidad;
    private String direccion;
    private String tipoDeSangre;
    private int cantidadDonantes;
    private int cantidadDonantesConfirmados;
    private Usuario usuario;
    private Criticidad criticidad;

    public Solicitud() {
    }

    public Solicitud(int id){
        super(id);
    }

    public Solicitud(String nombre,
                     String apellido,
                     Date fecha,
                     Provincia provincia,
                     String direccion,
                     int cantidadDonantes,
                     String tipoDeSangre) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
        this.provincia = provincia;
        this.direccion = direccion;
        this.cantidadDonantes = cantidadDonantes;
        this.tipoDeSangre = tipoDeSangre;
        setAutomaticCodigo();
    }

    public Solicitud(int id,
                     String nombre,
                     String apellido,
                     Date fecha,
                     Provincia provincia,
                     String direccion,
                     int cantidadDonantes,
                     Boolean estado,
                     String tipoDeSangre) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
        this.provincia = provincia;
        this.direccion = direccion;
        this.cantidadDonantes = cantidadDonantes;
        this.tipoDeSangre = tipoDeSangre;
        setAutomaticCodigo();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getAndroidFecha(){
        return DateUtil.convertToAndroidDate(fecha.toString());
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCantidadDonantes() {
        return cantidadDonantes;
    }

    public void setCantidadDonantes(int cantidadDonantes) {
        this.cantidadDonantes = cantidadDonantes;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Criticidad getCriticidad() {
        return criticidad;
    }

    public void setCriticidad(Criticidad criticidad) {
        this.criticidad = criticidad;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + '\n' +
                "Nombre: " + nombre + ", " + apellido + '\n' +
                "Fecha: " + getAndroidFecha() + '\n' +
                "Criticidad: " + criticidad.getDescripcion();
    }

    public String getTipoDeSangre() {
        return tipoDeSangre;
    }

    public void setTipoDeSangre(String tipoDeSangre) {
        this.tipoDeSangre = tipoDeSangre;
    }

    public void setAutomaticCodigo(){
        this.codigo = CodigoGenerator.getAutomaticCode(CodigoGenerator.codigoSolicitudLenght);
    }

    public int getEstadoInt(){
        return EstadoSolicitud.getTipoEstadoToInt(this.estado);
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

    public int getCantidadDonantesConfirmados() {
        return cantidadDonantesConfirmados;
    }

    public void setCantidadDonantesConfirmados(int cantidadDonantesConfirmados) {
        this.cantidadDonantesConfirmados = cantidadDonantesConfirmados;
    }
}
