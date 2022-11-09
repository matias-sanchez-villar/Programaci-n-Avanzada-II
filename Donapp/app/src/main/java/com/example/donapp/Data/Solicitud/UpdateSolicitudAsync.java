package com.example.donapp.Data.Solicitud;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.StatusResponse;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateSolicitudAsync extends AsyncTask<String, Void, StatusResponse> {
    private Solicitud solicitud;
    private Context context;

    public UpdateSolicitudAsync(Solicitud solicitud, Context context){
        this.solicitud = solicitud;
        this.context = context;
    }

    public UpdateSolicitudAsync(Context context){
        this.context = context;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            PreparedStatement preparedStatement =
                    con.prepareStatement(updateSolicitud(), Statement.RETURN_GENERATED_KEYS);
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
            preparedStatement.setInt(13, solicitud.getId());


            int row = preparedStatement.executeUpdate();


            if(row == 1){
                return StatusResponse.SUCCESS;
            }

            return StatusResponse.FAIL;
        }
        catch(Exception e) {
            e.printStackTrace();
            return StatusResponse.FAIL;
        }
    }

    private String updateSolicitud(){
        return "UPDATE `solicitudes` SET " +
                "`codigo`= ?," +
                "`nombre`= ?," +
                "`apellido`= ?," +
                "`fecha`= ?," +
                "`id_localidad`= ?," +
                "`id_provincia`= ?," +
                "`direccion`= ?," +
                "`id_usuario`= ?," +
                "`cantidadDonantes`= ?," +
                "`tipo_sangre` = ?," +
                "`estado`= ?," +
                "`id_criticidad` = ? " +
                "WHERE id = ?";
    }
}
