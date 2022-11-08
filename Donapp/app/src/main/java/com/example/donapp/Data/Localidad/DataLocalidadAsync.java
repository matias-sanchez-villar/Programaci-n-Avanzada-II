package com.example.donapp.Data.Localidad;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Database.TableDB;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Enums.StatusResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataLocalidadAsync extends AsyncTask<String, Void, StatusResponse> {

    private ListView lvLocalidad;
    private Spinner spnLocalidad;
    private Context context;
    int provinciaId;
    private static ArrayList<Localidad> listLocalidad = new ArrayList<Localidad>();

    public DataLocalidadAsync(ListView lv, Context ct, int provinciaId){
        this.lvLocalidad = lv;
        this.context = ct;
        this.provinciaId = provinciaId;
    }

    public DataLocalidadAsync(Spinner spn, Context ct, int provinciaId){
        this.spnLocalidad = spn;
        this.context = ct;
        this.provinciaId = provinciaId;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                    TableDB.SelectByPropertieInt(
                        TableDB.LOCALIDAD,
                        "id_provincia",
                        provinciaId
                    )
            );

            Localidad localidad;
            listLocalidad.clear();
            while(rs.next()) {

                localidad = new Localidad();
                localidad.setId(rs.getInt("id"));
                localidad.setNombre(rs.getString("nombre"));

                listLocalidad.add(localidad);
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
        ArrayAdapter<Localidad> adapter = new ArrayAdapter<Localidad>(
                this.context,
                android.R.layout.simple_spinner_item,
                listLocalidad
        );
        spnLocalidad.setAdapter(adapter);
        spnLocalidad.setPrompt("LOCALIDAD");
    }
}
