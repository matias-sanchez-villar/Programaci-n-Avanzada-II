package com.example.donapp.Entity;

import com.example.donapp.Enums.TipoUsuario;

public class Usuario extends EntidadEstadoBase {
    private String nombreUsuario;
    private TipoUsuario tipoUsuario;
    private String email;
    private String password;
    private Persona persona;

    public Usuario(){
        super();
    }

    public Usuario(int id){
        super(id);
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
