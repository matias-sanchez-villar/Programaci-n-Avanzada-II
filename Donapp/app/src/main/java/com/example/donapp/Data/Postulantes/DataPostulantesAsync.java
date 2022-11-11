package com.example.donapp.Data.Postulantes;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Entity.PersonaFisica;
import com.example.donapp.Enums.Categoria;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataPostulantesAsync extends AsyncTask<String, Void, StatusResponse> {

    ListView listView;
    ArrayList<PersonaFisica> personasList = new ArrayList<PersonaFisica>();
    int idRegistro;
    Categoria categoria;
    Context context;

    public DataPostulantesAsync(Context context, ListView lv, int idRegistro, Categoria categoria){
        this.context = context;
        this.listView = lv;
        this.idRegistro = idRegistro;
        this.categoria = categoria;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(queryPostulantes());

            PersonaFisica personaFisica;
            personasList.clear();
            while(rs.next()) {

                personaFisica = new PersonaFisica();
                personaFisica.setId(rs.getInt("id"));
                personaFisica.setNombre(rs.getString("nombre"));
                personaFisica.setApellido(rs.getString("apellido"));
                personaFisica.setDni(rs.getInt("dni"));

                personasList.add(personaFisica);
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
                R.layout.postulantes_list_item,
                R.id.tvListPostulanteItem,
                personasList
        );
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private String queryPostulantes(){
        return String.format("" +
                "SELECT per.id, per.nombre, per.apellido, per.dni " +
                "FROM personas per " +
                "INNER JOIN usuarios usua ON usua.id_persona = per.id " +
                "INNER JOIN postulaciones post ON post.id_usuario = usua.id " +
                "WHERE post.id_registro_postulado = %1$s " +
                "AND post.categoria = %2$s", idRegistro, categoria.ordinal());
    }
}
