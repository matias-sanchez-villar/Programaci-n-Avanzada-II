package com.example.donapp.Data.Campania;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Entity.Campania;
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
            preparedStatement.setString(2, campania.getFecha().toString());
            preparedStatement.setInt(3, campania.getLocalidad().getId());
            preparedStatement.setInt(4, campania.getProvincia().getId());
            preparedStatement.setString(5, campania.getDireccion());
            preparedStatement.setInt(6, campania.getCantDonantes());
            preparedStatement.setInt(7, campania.getCantDias());
            preparedStatement.setInt(8, campania.getUsuario().getId()); //ver


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
                "`id_localidad`= ?," +
                "`id_provincia`= ?," +
                "`direccion`= ?," +
                "`cantidadDonantes`= ?," +
                "`cantidadDias`= ?," +
                "`id_persona`= ?";
    }
}
