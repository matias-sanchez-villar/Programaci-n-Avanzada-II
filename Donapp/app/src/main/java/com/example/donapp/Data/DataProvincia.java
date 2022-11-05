package com.example.donapp.Data;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Entity.Solicitud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataProvincia extends AsyncTask<String, Void, String> {

    private ListView LVProvincia;
    private Context context;
    private static String result;
    private static ArrayList<Provincia> listProvincia = new ArrayList<Provincia>();

    public  DataProvincia(ListView lv, Context ct){
        this.LVProvincia = lv;
        this.context = ct;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM provincias");
            result = " ";

            Provincia provincia;
            ArrayList<Localidad> localidad;
            listProvincia.clear();
            while(rs.next()) {
                provincia = new Provincia();

                localidad = new ArrayList<Localidad>();
                localidad.add(new Localidad((rs.getString("localidad"))));

                provincia.setId(rs.getInt("id"));
                provincia.setProvincia(rs.getString("provincia"));
                provincia.setLocalidad(localidad);
                // provincia.setEstado(Boolean.parseBoolean(rs.getString("estado")));

                //Agregamos el nombre del articulo
                listProvincia.add(provincia);
            }
            response = "Conexion exitosa";
        }
        catch(Exception e) {
            e.printStackTrace();
            result = "Conexion no exitosa";
        }
        return response;
    }
}
