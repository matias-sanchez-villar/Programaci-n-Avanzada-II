package com.example.donapp.Data.Criticidad;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Database.TableDB;
import com.example.donapp.Entity.Criticidad;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Enums.StatusResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataCriticidadAsync extends AsyncTask<String, Void, StatusResponse> {

    private Spinner spnCriticidad;
    private Context context;
    private static ArrayList<Criticidad> listCriticidad = new ArrayList<Criticidad>();

    public DataCriticidadAsync(Spinner spn, Context ct){
        this.spnCriticidad = spn;
        this.context = ct;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                    TableDB.SelectAll(TableDB.CRITICIDAD)
            );

            Criticidad criticidad;
            listCriticidad.clear();
            while(rs.next()) {

                criticidad = new Criticidad();
                criticidad.setId(rs.getInt("id"));
                criticidad.setDescripcion(rs.getString("descripcion"));

                listCriticidad.add(criticidad);
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
        ArrayAdapter<Criticidad> adapter = new ArrayAdapter<Criticidad>(
                this.context,
                android.R.layout.simple_spinner_item,
                listCriticidad
        );
        spnCriticidad.setAdapter(adapter);
        spnCriticidad.setPrompt("LOCALIDAD");
    }
}
