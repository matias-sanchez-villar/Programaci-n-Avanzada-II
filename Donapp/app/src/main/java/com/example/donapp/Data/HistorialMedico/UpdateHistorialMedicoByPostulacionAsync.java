package com.example.donapp.Data.HistorialMedico;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Util.DateUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateHistorialMedicoByPostulacionAsync extends AsyncTask<String, Void, StatusResponse> {

    int idUsuario;
    Context context;

    public UpdateHistorialMedicoByPostulacionAsync(int idUsuario, Context context){
        this.idUsuario = idUsuario;
        this.context = context;
    }



    @Override
    protected StatusResponse doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            PreparedStatement preparedStatement =
                    con.prepareStatement(updateQueryHistorial());
            preparedStatement.setDate(1, new Date(DateUtil.getActualDate().getTime()));
            preparedStatement.setInt(2, idUsuario);

            int status = preparedStatement.executeUpdate();

            if(status != 0){
                return StatusResponse.SUCCESS;
            }

            return StatusResponse.FAIL;
        }
        catch(Exception e) {
            e.printStackTrace();
            return StatusResponse.FAIL;
        }
    }

    private String updateQueryHistorial(){
        return "UPDATE historiales_medicos SET ultima_donacion = ? " +
                "WHERE id_usuario = ?";
    }
}
