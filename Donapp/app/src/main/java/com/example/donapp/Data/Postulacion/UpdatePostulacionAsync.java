package com.example.donapp.Data.Postulacion;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Data.Solicitud.UpdateSolicitudAsync;
import com.example.donapp.Database.DataDB;
import com.example.donapp.Enums.EstadoPostulacion;
import com.example.donapp.Enums.EstadoSolicitud;
import com.example.donapp.Enums.StatusResponse;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class UpdatePostulacionAsync extends AsyncTask<String, Void, StatusResponse> {

    int id_registro;
    int id;
    int categoria;
    Context context;

    public UpdatePostulacionAsync(Context context, int id_registro, int categoria){
        this.context = context;
        this.id_registro = id_registro;
        this.categoria = categoria;
    }

    public UpdatePostulacionAsync(Context context, int id){
        this.context = context;
        this.id = id;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            PreparedStatement preparedStatement = con.prepareStatement(updatePostulacionStatus());

            preparedStatement.setInt(1, EstadoPostulacion.CONFIRMADO.ordinal());
            preparedStatement.setInt(2, id);

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

    private String updatePostulacionStatus(){
        return "UPDATE `postulaciones` SET `estado` = ? " +
                "WHERE id_registro_postulado = ? " +
                "AND categoria = ?";
    }

    private String updatePostulacionStatusById(){
        return "UPDATE `postulaciones` SET `estado` = ? " +
                "WHERE id = ?";
    }
}
