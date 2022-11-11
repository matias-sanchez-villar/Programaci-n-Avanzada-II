package com.example.donapp.Data.Persona;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Database.TableDB;
import com.example.donapp.Entity.BancoSangre;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.PersonaFisica;
import com.example.donapp.Entity.Provincia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadPersonaFisicaAsync extends AsyncTask<String, Void, PersonaFisica> {

    PersonaFisica personaFisica;
    Context context;
    int id;

    public ReadPersonaFisicaAsync(int id, Context context){
        this.id = id;
        this.context = context;
    }

    @Override
    protected PersonaFisica doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(querySolicitudWithCriticidad(id));

            if(rs.next()){
                this.personaFisica = new PersonaFisica();
                this.personaFisica.setId(id);
                this.personaFisica.setNombre(rs.getString("Nombre"));
                this.personaFisica.setTelefono(rs.getInt("telefono"));
                this.personaFisica.setDireccion(rs.getString("direccion"));
                this.personaFisica.setProvincia(new Provincia(
                        rs.getInt("id_provincia"),
                        rs.getString("provincia")
                ));
                this.personaFisica.setLocalidad(new Localidad(rs.getString("localidad")));
                return personaFisica;
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
