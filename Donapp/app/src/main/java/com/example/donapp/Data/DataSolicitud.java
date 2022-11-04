package com.example.donapp.Data;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
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

public class DataSolicitud extends AsyncTask<String, Void, String> {

    private ListView LVSolicitud;
    private Context context;
    private static String result;
    private static ArrayList<Solicitud> listSolicitud = new ArrayList<Solicitud>();

    public  DataSolicitud(ListView lv, Context ct){
        this.LVSolicitud = lv;
        this.context = ct;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM solicitudes");
            result = " ";

            Solicitud solicitud;
            ArrayList<Localidad> localidad;
            listSolicitud.clear();
            while(rs.next()) {
                solicitud = new Solicitud();

                localidad = new ArrayList<Localidad>();
                localidad.add(new Localidad((rs.getString("localidad"))));

                solicitud.setId(rs.getInt("id"));
                solicitud.setNombre(rs.getString("nombre"));
                solicitud.setApellido(rs.getString("apellido"));
                solicitud.setFecha(rs.getString("fecha"));
                solicitud.setProvincia(
                        new Provincia(
                                rs.getString("provincia"),
                                localidad
                        )
                );
                solicitud.setDireccion(rs.getString("direccion"));
                solicitud.setCantidadDonantes(Integer.parseInt(rs.getString("cantidadDonantes")));
                solicitud.setEstado(Boolean.parseBoolean(rs.getString("estado")));

                //Agregamos el nombre del articulo
                listSolicitud.add(solicitud);
            }
            response = "Conexion exitosa";
        }
        catch(Exception e) {
            e.printStackTrace();
            result = "Conexion no exitosa";
        }
        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this.context,
                android.R.layout.list_item_articulos,
                listSolicitud
        );
        LVSolicitud.setAdapter(adapter);
         */
    }

}
