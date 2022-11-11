package com.example.donapp.Data.Postulacion;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Entity.Postulacion;
import com.example.donapp.Enums.EstadoPostulacion;
import com.example.donapp.Enums.StatusResponse;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreatePostulacionAsync extends AsyncTask<String, Void, Integer> {

    Postulacion entity;
    Context context;

    public CreatePostulacionAsync(Context context){

    }

    public CreatePostulacionAsync(Postulacion entity, Context context){
        this.entity = entity;
        this.context = context;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            PreparedStatement preparedStatement =
                    con.prepareStatement(insertPostulacion(), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getCodigo());
            preparedStatement.setDate(2, new Date(entity.getFechaGeneracion().getTime()));
            preparedStatement.setInt(3, entity.getCategoria().ordinal());
            preparedStatement.setInt(4, entity.getUsuario().getId());
            preparedStatement.setInt(5, entity.getRegistroPostulado().getIdRegistro());
            preparedStatement.setInt(6, EstadoPostulacion.ACTIVO.ordinal());

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

    private String insertPostulacion(){
        return "INSERT INTO `postulaciones`(`codigo`, " +
                "`fecha_generacion`, " +
                "`categoria`, " +
                "`id_usuario`, " +
                "`id_registro_postulado`, " +
                "`estado`) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
    }
}
