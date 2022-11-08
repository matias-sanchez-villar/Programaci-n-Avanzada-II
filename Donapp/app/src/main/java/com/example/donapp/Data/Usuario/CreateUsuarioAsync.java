package com.example.donapp.Data.Usuario;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.StatusResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateUsuarioAsync extends AsyncTask<String, Void, Integer> {

    private Usuario newUsuario;
    private Context context;

    public CreateUsuarioAsync(Usuario newUsuario, Context context){
        this.newUsuario = newUsuario;
        this.context = context;
    }

    public CreateUsuarioAsync(Context context){
        this.context = context;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            PreparedStatement preparedStatement =
                    con.prepareStatement(insertUsuario(), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newUsuario.getNombreUsuario());
            preparedStatement.setString(2, newUsuario.getEmail());
            preparedStatement.setString(3, newUsuario.getPassword());
            preparedStatement.setInt(4, newUsuario.getTipoUsuario().ordinal());
            preparedStatement.setInt(5, newUsuario.getPersona().getId());

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

    public String insertUsuario(){
        return  "INSERT INTO `usuarios`(`nombre_usuario`, " +
                "`email`, " +
                "`password`, " +
                "`tipo_usuario`, " +
                "`id_persona`) VALUES(?, ?, ?, ?, ?)";
    }
}
