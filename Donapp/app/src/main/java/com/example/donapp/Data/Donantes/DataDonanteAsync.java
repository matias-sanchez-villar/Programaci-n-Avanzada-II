package com.example.donapp.Data.Donantes;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.PersonaFisica;
import com.example.donapp.Entity.PersonaJuridica;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataDonanteAsync extends AsyncTask<String, Void, StatusResponse> {

    ListView listView;
    Context context;
    ArrayList<PersonaFisica> personaFisicaList = new ArrayList<PersonaFisica>();

    public DataDonanteAsync(Context context, ListView listView){
        this.context = context;
        this.listView = listView;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(queryFisicas());

            PersonaFisica donante;
            personaFisicaList.clear();
            while(rs.next()) {

                donante = new PersonaFisica();
                donante.setId(rs.getInt("id"));
                donante.setNombre(rs.getString("nombre"));
                donante.setApellido(rs.getString("Apellido"));
                donante.setTelefono(rs.getInt("telefono"));
                donante.setDireccion(rs.getString("direccion"));
                donante.setProvincia(new Provincia(rs.getString("provincia")));
                donante.setLocalidad(new Localidad(rs.getString("localidad")));
                donante.setDni(rs.getInt("dni"));
                donante.setFechaNacimiento(rs.getDate("fecha_nacimiento"));

                personaFisicaList.add(donante);
            }
            return StatusResponse.SUCCESS;
        }
        catch(Exception e) {
            e.printStackTrace();
            return StatusResponse.FAIL;
        }
    }

    @Override
    protected void onPostExecute(StatusResponse response) {
        ArrayAdapter<PersonaFisica> adapter = new ArrayAdapter<PersonaFisica>(
                this.context,
                R.layout.donantes_list_item,
                R.id.tvListDonanteItem,
                personaFisicaList
        );

        this.listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public String queryFisicas(){
        return "SELECT per.*, prov.nombre AS 'provincia', loc.nombre AS 'localidad' " +
                "FROM `personas` per " +
                "INNER JOIN `usuarios` user ON user.id_persona = per.id " +
                "INNER JOIN `provincias` prov ON prov.id = per.id_provincia " +
                "INNER JOIN `localidades` loc ON loc.id = per.id_localidad " +
                "WHERE user.tipo_usuario = 1";
    }


}
