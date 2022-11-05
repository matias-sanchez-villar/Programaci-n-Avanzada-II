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

public class DataLocalidad extends AsyncTask<String, Void, String> {

    private ListView LVLocalidad;
    private Context context;
    private static String result;
    private static ArrayList<Localidad> listLocalidad= new ArrayList<Localidad>();

    public  DataLocalidad(ListView lv, Context ct){
        this.LVLocalidad = lv;
        this.context = ct;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM localidades");
            result = " ";

            Localidad localidad;
            listLocalidad.clear();
            while(rs.next()) {
                localidad = new Localidad();
                localidad.setId(rs.getInt("id"));
                localidad.setLocalidad(rs.getString("localidad"));
                // localidad.setEstado(Boolean.parseBoolean(rs.getString("estado")));

                //Agregamos el nombre del articulo
                listLocalidad.add(localidad);
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
