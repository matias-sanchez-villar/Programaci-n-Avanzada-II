package com.example.donapp.Data.Campania;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Entity.Campania;
import com.example.donapp.Enums.EstadoCampania;
import com.example.donapp.Enums.StatusResponse;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class UpdateCampaniaAsync extends AsyncTask<String, Void, StatusResponse> {
    private Campania campania;
    private Context context;

    public UpdateCampaniaAsync(Campania campania, Context context) {
        this.campania = campania;
        this.context = context;
    }

    public UpdateCampaniaAsync(Context context) {
        this.context = context;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            PreparedStatement preparedStatement = con.prepareStatement(updateCampania(), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, campania.getNombreCampana());
            preparedStatement.setDate(2, new Date(campania.getFecha().getTime()));
            preparedStatement.setDate(3, new Date(campania.getFechaFin().getTime()));
            preparedStatement.setInt(4, campania.getLocalidad().getId());
            preparedStatement.setInt(5, campania.getProvincia().getId());
            preparedStatement.setString(6, campania.getDireccion());
            preparedStatement.setInt(7, campania.getCantDonantes());
            preparedStatement.setInt(8, campania.getCantDias());
            preparedStatement.setInt(9, campania.getUsuario().getId());
            preparedStatement.setInt(10, campania.getEstadoInt());
            preparedStatement.setInt(11, campania.getId());


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

    private String updateCampania() {
        return  "UPDATE `campanias` SET " +
                "`nombre_campania`=?, " +
                "`fecha`= ?," +
                "`fecha_fin`= ?," +
                "`id_localidad`= ?," +
                "`id_provincia`= ?," +
                "`direccion`= ?," +
                "`cantidad_donantes`= ?," +
                "`cantidad_dias`= ?," +
                "`id_usuario`= ?," +
                "`estado`= ?" +
                " WHERE id = ?";
    }
}
