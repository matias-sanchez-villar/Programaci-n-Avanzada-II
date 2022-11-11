package com.example.donapp.Data.Solicitud;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Entity.Criticidad;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.EstadoSolicitud;
import com.example.donapp.Enums.StatusResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadSolicitudAsync extends AsyncTask<String, Void, Solicitud> {

    Solicitud solicitud;
    private Context context;
    private int searcheableId;

    public ReadSolicitudAsync(int id, Context context){
        this.searcheableId = id;
        this.context = context;
    }


    @Override
    protected Solicitud doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(querySolicitudWithCriticidad(searcheableId));

            Solicitud solicitud;
            if(rs.next()) {

                solicitud = new Solicitud();
                solicitud.setId(rs.getInt("id"));
                solicitud.setCodigo(rs.getString("codigo"));
                solicitud.setNombre(rs.getString("nombre"));
                solicitud.setApellido(rs.getString("apellido"));
                solicitud.setFecha(rs.getDate("fecha"));
                solicitud.setProvincia(new Provincia(rs.getInt("id_provincia"), rs.getString("provincia")));
                solicitud.setLocalidad(new Localidad(rs.getString("localidad")));
                solicitud.setDireccion(rs.getString("direccion"));
                solicitud.setUsuario(new Usuario(rs.getInt("id_usuario")));
                solicitud.setCantidadDonantes(rs.getInt("cantidad_donantes"));
                solicitud.setCantidadDonantesConfirmados(rs.getInt("cant_donantes_confirmados") );
                solicitud.setTipoDeSangre(rs.getString("tipo_sangre"));
                solicitud.setEstado(EstadoSolicitud.getTipoEstadoSolicitud(rs.getInt("estado")));
                solicitud.setCriticidad(new Criticidad(rs.getString("descripcion")));

                return solicitud;
            }
            return null;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String querySolicitudWithCriticidad(int id){
        return String.format("SELECT sol.*, " +
                "c.descripcion AS 'descripcion', " +
                "p.nombre AS 'provincia', l.nombre AS 'localidad' " +
                "FROM `solicitudes` sol " +
                "INNER JOIN `criticidad` c ON c.id = sol.id_criticidad " +
                "INNER JOIN `provincias` p ON p.id = sol.id_provincia " +
                "INNER JOIN `localidades` l ON l.id = sol.id_localidad " +
                "WHERE sol.id = %1$s", id);
    }
}
