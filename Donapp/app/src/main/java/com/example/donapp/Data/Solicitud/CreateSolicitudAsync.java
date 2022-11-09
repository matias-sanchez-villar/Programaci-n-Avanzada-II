package com.example.donapp.Data.Solicitud;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.Enums.StatusResponse;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateSolicitudAsync extends AsyncTask<String, Void, Integer> {

    private Solicitud solicitud;
    private Context context;

    public CreateSolicitudAsync(Solicitud solicitud, Context context){
        this.solicitud = solicitud;
        this.context = context;
    }

    public CreateSolicitudAsync(Context context){
        this.context = context;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            PreparedStatement preparedStatement =
                    con.prepareStatement(insertSolicitud(), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, solicitud.getCodigo());
            preparedStatement.setString(2, solicitud.getNombre());
            preparedStatement.setString(3, solicitud.getApellido());
            preparedStatement.setDate(4, new Date(solicitud.getFecha().getTime()));
            preparedStatement.setInt(5, solicitud.getLocalidad().getId());
            preparedStatement.setInt(6, solicitud.getProvincia().getId());
            preparedStatement.setString(7, solicitud.getDireccion());
            preparedStatement.setInt(8, solicitud.getUsuario().getId());
            preparedStatement.setInt(9, solicitud.getCantidadDonantes());
            preparedStatement.setString(10, solicitud.getTipoDeSangre());
            preparedStatement.setInt(11, solicitud.getEstadoInt());
            preparedStatement.setInt(12, solicitud.getCriticidad().getId());


            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if(rs.next()){
                return rs.getInt(1);
            }

            return StatusResponse.FAIL.ordinal();
        }
        catch(Exception e) {
            e.printStackTrace();
            return StatusResponse.FAIL.ordinal();
        }
    }

    public String insertSolicitud(){
        return "INSERT INTO `solicitudes`" +
                "(`codigo`, `nombre`, `apellido`, `fecha`, `id_localidad`, `id_provincia`, `direccion`, `id_usuario`, `cantidadDonantes`, `tipo_sangre`, `estado`, `id_criticidad`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }
}
