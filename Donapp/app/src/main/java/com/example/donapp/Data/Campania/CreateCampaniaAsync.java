package com.example.donapp.Data.Campania;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Entity.Campana;
import com.example.donapp.Enums.StatusResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class CreateCampaniaAsync extends AsyncTask<String, Void, Integer> {

    private Campana newCampana;
    private Context context;

    public CreateCampaniaAsync(Campana newCampana, Context context) {
        this.newCampana = newCampana;
        this.context = context;
    }

    public CreateCampaniaAsync(Context context) { this.context = context; }

    @Override
    protected Integer doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            PreparedStatement preparedStatement = con.prepareStatement(insertCampania(), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newCampana.getNombreCampana());
            preparedStatement.setString(2, newCampana.getFecha().toString());
            preparedStatement.setInt(3, newCampana.getLocalidad().getId());
            preparedStatement.setInt(4, newCampana.getProvincia().getId());
            preparedStatement.setString(5, newCampana.getDireccion());
            preparedStatement.setInt(6, newCampana.getCantSolicitante());
            preparedStatement.setInt(7, newCampana.getCantDias());
            preparedStatement.setInt(8, newCampana.getUsuarioEmpresa()); //ver

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

    // sin chequear
    private String insertCampania() {
        return  "INSERT INTO `campanias`(`nombre_campana`, " +
                "`fecha`, " +
                "`id_localidad`, " +
                "`id_provincia`, " +
                "`direccion`, " +
                "`cantidadDonantes`, " +
                "`cantidadDias`, " +
                "`id_persona`) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    }
}
