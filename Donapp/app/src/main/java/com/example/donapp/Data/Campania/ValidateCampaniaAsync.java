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
import java.util.Date;

/* Metodo Validar fecha y Zona (Error!!!)
public class ValidateCampaniaAsync extends AsyncTask<Void,Void,Boolean> {

    private Campania campania;
    private Context context;
    private int searcheableId;
    private Date fecha;
    private int idZone;

    public ValidateCampaniaAsync(Date fecha, int idZona, Context context) {
        this.fecha = fecha;
        this.idZone = idZone;
        this.context = context;
    }


    @Override
    protected Boolean doInBackground(String... params) {
            try {
                Class.forName(DataDB.driver);
                Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(queryCampaniaWithDateandZone(fecha,idZone));

                Campania campania;
                if(rs.next()) {

                    campania = new Campania();
                    campania.setId(rs.getInt("id"));
                    campania.setNombreCampana(rs.getString("nombre_campania"));
                    campania.setFecha(rs.getDate("fecha"));
                    campania.setProvincia(new Provincia(rs.getInt("id_provincia"), rs.getString("provincia")));
                    campania.setLocalidad(new Localidad(rs.getString("localidad")));
                    campania.setDireccion(rs.getString("direccion"));
                    campania.setCantSolicitante(rs.getInt("cantidaSolicitantes"));
                    campania.setCantDias(rs.getInt("cantidad_dias"));
                    campania.setEstado(EstadoSolicitud.getTipoEstadoSolicitud(rs.getInt("estado")));
                    campania.setUsuario(new Usuario(rs.getInt("id_usuario")));

                    return true;
                }
                return false;
            }
            catch(Exception e) {
                e.printStackTrace();
                return false;
            }
    }
    private String queryCampaniaWithDateandZone(Date fecha, int idZone) {
        return String.format("SELECT *, " +
                "FROM `campanias` cam " +
                "WHERE fecha = %1$s " +
                "AND id_localidad = %2$s " +
                "AND estado = 1", fecha, idZone);
    }
}
*/