package com.example.donapp.Data.Campania;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Entity.Campania;
import com.example.donapp.Enums.StatusResponse;

import java.sql.Connection;
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
            preparedStatement.setString(3, campania.getFecha().toString());
            preparedStatement.setString(4, campania.getFechaFin().toString());
            preparedStatement.setInt(5, campania.getLocalidad().getId());
            preparedStatement.setInt(6, campania.getProvincia().getId());
            preparedStatement.setString(7, campania.getDireccion());
            preparedStatement.setInt(8, campania.getCantDonantes());
            preparedStatement.setInt(9, 0);
            preparedStatement.setInt(10, campania.getCantDias());
            preparedStatement.setInt(11, campania.getInstitucion().getId());
            preparedStatement.setInt(12, campania.getUsuario().getId()); //ver
            preparedStatement.setInt(13, campania.getEstadoInt());

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
        return  "INSERT INTO `campanias`(`codigo`, " +
                "`nombre_campania`, " +
                "`fecha`, " +
                "`fecha_fin`, " +
                "`id_localidad`, " +
                "`id_provincia`, " +
                "`direccion`, " +
                "`cantidadDonantes`, " +
                "`cantidad_donantes_confirmados`, " +
                "`cantidadDias`, " +
                "`id_institucion`, " +
                "`id_usuario`, " +
                "`estado`) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }
}
