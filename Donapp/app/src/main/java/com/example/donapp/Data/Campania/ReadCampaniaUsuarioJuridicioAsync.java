package com.example.donapp.Data.Campania;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Entity.Campania;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.EstadoSolicitud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ReadCampaniaUsuarioJuridicioAsync extends AsyncTask<String,Void, ArrayList<Campania>> {
    ArrayList<Campania> listCampania;
    Campania campania;
    Context context;
    int searcheableId;

    public ReadCampaniaUsuarioJuridicioAsync(int id, Context context) {
        this.searcheableId = id;
        this.context = context;
    }


    @Override
    protected ArrayList<Campania> doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(queryCampaniaWithid(searcheableId));

            Campania campania;
            listCampania = new ArrayList<Campania>();
            while (rs.next()) {
                campania = new Campania();
                campania.setId(rs.getInt("id"));
                campania.setFecha(rs.getDate("fecha"));
                campania.setCantDonantesConfirmados(rs.getInt("cantidad_donantes_confirmados"));
                campania.setEstado(EstadoSolicitud.getTipoEstadoSolicitud(rs.getInt("estado")));

                listCampania.add(campania);
            }
            if (listCampania.size() != 0) return listCampania;
            return null;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String queryCampaniaWithid(int id) {
        return String.format("SELECT id, fecha, cantidad_donantes_confirmados, estado " +
                "FROM campanias " +
                "WHERE id_usuario = %1$s " +
                "ORDER BY cantidad_donantes_confirmados ASC", id);
    }
}
