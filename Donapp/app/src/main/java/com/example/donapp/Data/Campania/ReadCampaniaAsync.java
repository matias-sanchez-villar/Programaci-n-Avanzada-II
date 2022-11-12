package com.example.donapp.Data.Campania;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Entity.Campania;
import com.example.donapp.Entity.Criticidad;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.EstadoSolicitud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class ReadCampaniaAsync extends AsyncTask<String,Void, Campania> {

    Campania campania;
    private Context context;
    private int searcheableId;

    public ReadCampaniaAsync(int id, Context context) {
        this.searcheableId = id;
        this.context = context;
    }


    @Override
    protected Campania doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(queryCampaniaWithid(searcheableId));

            Campania campania;
            if(rs.next()) {

                campania = new Campania();
                campania.setId(rs.getInt("id"));
                campania.setCodigo(rs.getString("codigo"));
                campania.setNombreCampana(rs.getString("nombre_campania"));
                campania.setFecha(rs.getDate("fecha"));
                campania.setFechaFin(rs.getDate("fecha_fin"));
                campania.setProvincia(new Provincia(rs.getInt("id_provincia"), rs.getString("provincia")));
                campania.setLocalidad(new Localidad(rs.getString("localidad")));
                campania.setDireccion(rs.getString("direccion"));
                campania.setCantDonantes(rs.getInt("cantidad_donantes"));
                campania.setCantDonantesConfirmados(rs.getInt("cantidad_donantes_confirmados") );
                campania.setCantDias(rs.getInt("cantidad_dias"));
                campania.setEstado(EstadoSolicitud.getTipoEstadoSolicitud(rs.getInt("estado")));
                campania.setUsuario(new Usuario(rs.getInt("id_usuario")));

                return campania;
            }
            return null;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String queryCampaniaWithid(int id) {
        return String.format("SELECT cam.*, " +
                "p.nombre AS 'provincia', l.nombre AS 'localidad' " +
                "FROM `campanias` cam " +
                "INNER JOIN `provincias` p ON p.id = cam.id_provincia " +
                "INNER JOIN `localidades` l ON l.id = cam.id_localidad " +
                "WHERE cam.id = %1$s", id);
    }
}
