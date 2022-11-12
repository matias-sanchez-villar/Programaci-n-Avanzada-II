package com.example.donapp.Data.Usuario;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.StatusResponse;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class UpdateUsuarioAsync extends AsyncTask<String, Void, StatusResponse> {

    private Usuario usuario;
    private Context context;

    public UpdateUsuarioAsync(Usuario usuario, Context context){
        this.usuario = usuario;
        this.context = context;
    }

    public UpdateUsuarioAsync(Context context){
        this.context = context;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            PreparedStatement preparedStatement =
                    con.prepareStatement(updateSolicitud(), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, usuario.getNombreUsuario());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getPassword());
            preparedStatement.setInt(4, usuario.getId());

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
        return "UPDATE usuarios SET " +
                "nombre_usuario= ?, " +
                "email= ?, " +
                "password= ? " +
                "WHERE id = ?";
    }

}
