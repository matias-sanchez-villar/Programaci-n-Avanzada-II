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

public class ReadUsuarioPersonaJuridicaAsync extends AsyncTask<String, Void, PersonaJuridica> {

    PersonaJuridica personaJuridica;
    Context context;
    int id;

    public ReadUsuarioPersonaJuridicaAsync(Context context){
        this.context = context;
    }

    public ReadUsuarioPersonaJuridicaAsync(int id, Context context){
        this.id = id;
        this.context = context;
    }

    @Override
    protected PersonaJuridica doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(querySolicitudWithCriticidad(id));

            if(rs.next()){
                this.personaJuridica = new PersonaJuridica();
                this.personaJuridica.setId(id);
                this.personaJuridica.setNombre(rs.getString("Nombre"));
                this.personaJuridica.setTelefono(rs.getInt("telefono"));
                this.personaJuridica.setDireccion(rs.getString("direccion"));
                this.personaJuridica.setProvincia(new Provincia(
                        rs.getInt("id_provincia"),
                        rs.getString("provincia")
                ));
                this.personaJuridica.setLocalidad(new Localidad(rs.getString("localidad")));
                return personaJuridica;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String querySolicitudWithCriticidad(int id){
        return String.format("SELECT bs.hospital, bs.direccion, bs.id_provincia, p.nombre as provincia, l.nombre as localidad " +
                "FROM %1$s bs " +
                "INNER JOIN provincias p on p.id = bs.id_provincia " +
                "INNER JOIN localidades l on l.id = bs.id_localidad " +
                "WHERE bs.id = %2$s ", TableDB.BANCOS_SANGRE, id);
    }

}
