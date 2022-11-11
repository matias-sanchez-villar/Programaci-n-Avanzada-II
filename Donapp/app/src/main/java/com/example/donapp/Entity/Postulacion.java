package com.example.donapp.Entity;

import com.example.donapp.Enums.Categoria;
import com.example.donapp.Enums.EstadoPostulacion;
import com.example.donapp.Interfaces.IRegistroPostulable;
import com.example.donapp.Util.CodigoGenerator;

import java.util.Date;

public class Postulacion extends EntidadEstadoBase{
    String codigo;
    Date fechaGeneracion;
    Categoria categoria;
    Usuario usuario;
    Date fechaConfirmacion;
    IRegistroPostulable registroPostulado;

    public Postulacion(){
    }

    public Postulacion(IRegistroPostulable registroPostulado, Categoria categoria){
        this.registroPostulado = registroPostulado;
        this.categoria = categoria;
    }

    public Postulacion(int id){
        this.id = id;
    }

    public Postulacion(
            Date fechaGeneracion,
            Categoria categoria,
            Usuario usuario,
            IRegistroPostulable registroPostulado){
        this.codigo = CodigoGenerator.getAutomaticCode(6);
        this.fechaGeneracion = fechaGeneracion;
        this.categoria = categoria;
        this.registroPostulado = registroPostulado;
        this.estado = EstadoPostulacion.ACTIVO;
        this.usuario = usuario;
    }

    public Postulacion(Categoria categoria, Usuario usuario, IRegistroPostulable registroPostulado){
        this.categoria = categoria;
        this.usuario = usuario;
        this.registroPostulado = registroPostulado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaConfirmacion() {
        return fechaConfirmacion;
    }

    public void setFechaConfirmacion(Date fechaConfirmacion) {
        this.fechaConfirmacion = fechaConfirmacion;
    }

    public IRegistroPostulable getRegistroPostulado() {
        return registroPostulado;
    }

    public void setRegistroPostulado(IRegistroPostulable registroPostulado) {
        this.registroPostulado = registroPostulado;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + '\n' +
                "Fecha generacion: " + fechaGeneracion + '\n' +
                "Categoria: " + categoria.name() + '\n' +
                "Fecha confirmacion: " +
                (fechaConfirmacion != null
                ? fechaConfirmacion.toString()
                : "") + '\n' +
                "Estado: " + estado.displayEstado();
    }
}
