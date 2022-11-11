package com.example.donapp.Data.InstitucionesMedicas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Database.TableDB;
import com.example.donapp.Entity.Persona;
import com.example.donapp.Entity.PersonaJuridica;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataInstitucionMedicaAsync extends AsyncTask<String, Void, StatusResponse> {

    Context context;
    ListView listView;
    ArrayList<PersonaJuridica> institucionesList = new ArrayList<PersonaJuridica>();

    public DataInstitucionMedicaAsync(ListView listView, Context context){
        this.listView = listView;
        this.context = context;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(queryJuridicasWithInstituciones());

            PersonaJuridica institucion;
            institucionesList.clear();
            while(rs.next()) {

                institucion = new PersonaJuridica();
                institucion.setId(rs.getInt("id"));
                institucion.setNombre(rs.getString("nombre"));

                // listProvincia.add(provincia);
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
        ArrayAdapter<PersonaJuridica> adapter = new ArrayAdapter<PersonaJuridica>(
                this.context,
                R.layout.instituciones_medicas_list_item,
                R.id.tvListInstitucionMedicaItem,
                institucionesList
        );

        this.listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public String queryJuridicasWithInstituciones(){
        return "SELECT per.* " +
                "FROM personas per" +
                "INNER JOIN usuarios user ON user.id_persona = per.id " +
                "WHERE user.tipo_usuario = 3";
    }
}
