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
import java.sql.ResultSet;
import java.sql.Statement;


public class CreateCampaniaAsync extends AsyncTask<String, Void, Integer> {

    private Campania campania;
    private Context context;

    public CreateCampaniaAsync(Campania campania, Context context) {
        this.campania = campania;
        this.context = context;
    }

    public CreateCampaniaAsync(Context context) { this.context = context; }

    @Override
    protected Integer doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            PreparedStatement preparedStatement = con.prepareStatement(insertCampania(), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, campania.getCodigo());
            preparedStatement.setString(2, campania.getNombreCampana());
            preparedStatement.setDate(3, new Date(campania.getFecha().getTime()));
            preparedStatement.setDate(4, new Date(campania.getFechaFin().getTime()));
            preparedStatement.setInt(5, campania.getLocalidad().getId());
            preparedStatement.setInt(6, campania.getProvincia().getId());
            preparedStatement.setString(7, campania.getDireccion());
            preparedStatement.setInt(8, campania.getCantDonantes());
            preparedStatement.setInt(9, campania.getCantDias());
            preparedStatement.setInt(10, campania.getUsuario().getId());
            preparedStatement.setInt(11, campania.getEstadoInt());

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

    private String insertCampania() {
        return  "INSERT INTO `campanias`(`codigo`, " +
                "`nombre_campania`, " +
                "`fecha`, " +
                "`fecha_fin`, " +
                "`id_localidad`, " +
                "`id_provincia`, " +
                "`direccion`, " +
                "`cantidad_donantes`, " +
                "`cantidad_dias`, " +
                "`id_usuario`, " +
                "`estado`) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }
}
