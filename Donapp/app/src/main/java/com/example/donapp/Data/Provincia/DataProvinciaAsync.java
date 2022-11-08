package com.example.donapp.Data.Provincia;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Database.TableDB;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Enums.StatusResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataProvinciaAsync extends AsyncTask<String, Void, StatusResponse> {

    private ListView LVProvincia;
    private Spinner spnProvincia;
    private Context context;
    private static ArrayList<Provincia> listProvincia = new ArrayList<Provincia>();

    public DataProvinciaAsync(ListView lv, Context ct){
        this.LVProvincia = lv;
        this.context = ct;
    }

    public DataProvinciaAsync(Spinner spn, Context ct){
        this.spnProvincia = spn;
        this.context = ct;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(TableDB.SelectAll(TableDB.PROVINCIA));

            Provincia provincia;
            listProvincia.clear();
            while(rs.next()) {

                provincia = new Provincia();
                provincia.setId(rs.getInt("id"));
                provincia.setNombre(rs.getString("nombre"));

                listProvincia.add(provincia);
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
        ArrayAdapter<Provincia> adapter = new ArrayAdapter<Provincia>(
                this.context,
                android.R.layout.simple_spinner_item,
                listProvincia
        );
        if(spnProvincia != null){
            spnProvincia.setAdapter(adapter);
            spnProvincia.setPrompt("PROVINCIAS");
        } else {
            this.LVProvincia.setAdapter(adapter);
        }
    }
}
