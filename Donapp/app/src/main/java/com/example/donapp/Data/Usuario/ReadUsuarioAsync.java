package com.example.donapp.Data.Usuario;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.RequiresPermission;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Database.TableDB;
import com.example.donapp.Entity.Persona;
import com.example.donapp.Entity.PersonaFisica;
import com.example.donapp.Entity.PersonaJuridica;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.TipoUsuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadUsuarioAsync extends AsyncTask<String, Void, Usuario> {

    private Usuario usuario;
    private Context context;
    private int searcheableId;
    private String nombreUsuario;
    private String password;

    public ReadUsuarioAsync(int id, Context context){
        this.searcheableId = id;
        this.context = context;
    }

    public ReadUsuarioAsync(String nombreUsuario, String password, Context context){
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.context = context;
    }

    @Override
    protected Usuario doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(String.format("SELECT * FROM %1$s " +
                    "WHERE nombre_usuario = '%2$s' " +
                    "AND password = '%3$s'",
                    TableDB.USUARIO,
                    nombreUsuario,
                    password));

            if(rs.next()){
                    this.usuario = new Usuario();
                    this.usuario.setId(rs.getInt("id"));
                    this.usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                    this.usuario.setPassword(rs.getString("password"));
                    this.usuario.setEmail(rs.getString("email"));
                    this.usuario.setTipoUsuario(TipoUsuario.getTipoUsuario(rs.getInt("tipo_usuario")));
                    this.usuario.setPersona(getPersona(this.usuario.getTipoUsuario(), rs.getInt("id_persona")));
                return usuario;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Persona getPersona(TipoUsuario tipoUsuario, int id){
        return (tipoUsuario == TipoUsuario.SOLICITANTE || tipoUsuario == TipoUsuario.DONANTE)
                ? new PersonaFisica(id)
                : new PersonaJuridica(id);
    }
}
