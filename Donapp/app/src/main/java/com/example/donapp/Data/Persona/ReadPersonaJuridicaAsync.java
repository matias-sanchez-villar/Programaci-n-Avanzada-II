package com.example.donapp.Data.Persona;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Database.TableDB;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.PersonaFisica;
import com.example.donapp.Entity.PersonaJuridica;
import com.example.donapp.Entity.Provincia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadPersonaJuridicaAsync extends AsyncTask<String, Void, PersonaJuridica> {

    PersonaJuridica personaJuridica;
    Context context;
    int usuarioId;
    int id;

    public ReadPersonaJuridicaAsync(Context context){
        this.context = context;
    }

    public ReadPersonaJuridicaAsync(int usuarioId, Context context){
        this.usuarioId = usuarioId;
        this.context = context;
    }

    @Override
    protected PersonaJuridica doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(queryPersonaJuridicaByUsuario(usuarioId));

            if(rs.next()){
                this.personaJuridica = new PersonaJuridica();
                this.personaJuridica.setId(id);
                this.personaJuridica.setNombre(rs.getString("nombre"));
                this.personaJuridica.setTelefono(rs.getInt("telefono"));
                this.personaJuridica.setDireccion(rs.getString("direccion"));
                this.personaJuridica.setProvincia(new Provincia(
                        rs.getString("provincia")
                ));
                this.personaJuridica.setLocalidad(new Localidad(rs.getString("localidad")));
                this.personaJuridica.setCuil(rs.getString("cuil"));
                this.personaJuridica.setHorarioInicio(rs.getString("horario_inicio"));
                this.personaJuridica.setHorarioFin(rs.getString("horario_fin"));
                return personaJuridica;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String queryPersonaJuridicaByUsuario(int id){
        return String.format(
                "SELECT ps.nombre, ps.horario_inicio, ps.telefono, ps.direccion, ps.horario_fin, ps.cuil, p.nombre as provincia, l.nombre as localidad " +
                        "FROM %1$s ps " +
                        "INNER JOIN provincias p on p.id = ps.id_provincia " +
                        "INNER JOIN localidades l on l.id = ps.id_localidad " +
                        "INNER JOIN usuarios user ON user.id_persona = ps.id " +
                        "WHERE user.id = %2$s", TableDB.PERSONA, id);
    }

}
