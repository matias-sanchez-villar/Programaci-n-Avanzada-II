package com.example.donapp.Entity;

import com.example.donapp.Enums.Estado;
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

    public Usuario(String nombreUsuario, String password){
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    public Usuario(int id){
        super(id);
    }

    public Usuario(int id,
                   String nombreUsuario,
                   TipoUsuario tipoUsuario,
                   String email,
                   String password,
                   Persona persona){
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.tipoUsuario = tipoUsuario;
        this.email = email;
        this.password = password;
        this.persona = persona;
    }

    public Usuario(String nombreUsuario,
                   String email,
                   String password,
                   TipoUsuario tipoUsuario){
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.estado = Estado.ACTIVO;
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
