package com.example.donapp.Data.Persona;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Database.TableDB;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Persona;
import com.example.donapp.Entity.PersonaFisica;
import com.example.donapp.Entity.PersonaJuridica;
import com.example.donapp.Enums.StatusResponse;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreatePersonaAsync extends AsyncTask<String, Void, Integer> {
    private PersonaFisica personaFisica;
    private PersonaJuridica personaJuridica;
    private boolean isJuridica;
    private Context context;


    public CreatePersonaAsync(PersonaFisica persona, Context context){
        this.personaFisica = persona;
        this.context = context;
        this.isJuridica = false;
    }

    public CreatePersonaAsync(PersonaJuridica persona, Context context){
        this.personaJuridica = persona;
        this.context = context;
        this.isJuridica = true;
    }

    public CreatePersonaAsync(Context context){
        this.context = context;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            PreparedStatement preparedStatement =
                    con.prepareStatement(isJuridica ? insertPersonaJuridica() : insertPersonaFisica(), Statement.RETURN_GENERATED_KEYS);
            if(isJuridica){
                preparedStatement.setString(1, personaJuridica.getNombre());
                preparedStatement.setInt(2, personaJuridica.getTelefono());
                preparedStatement.setString(3, personaJuridica.getDireccion());
                preparedStatement.setInt(4, personaJuridica.getProvincia().getId());
                preparedStatement.setInt(5, personaJuridica.getLocalidad().getId());
                preparedStatement.setString(6, personaJuridica.getCuil());
                preparedStatement.setString(7, personaJuridica.getHorarioInicio());
                preparedStatement.setString(8, personaJuridica.getHorarioFin());
                preparedStatement.setBoolean(9, personaJuridica.getJuridica());
            } else {
                preparedStatement.setString(1, personaFisica.getNombre());
                preparedStatement.setString(2, personaFisica.getApellido());
                preparedStatement.setInt(3, personaFisica.getTelefono());
                preparedStatement.setString(4, personaFisica.getDireccion());
                preparedStatement.setInt(5, personaFisica.getProvincia().getId());
                preparedStatement.setInt(6, personaFisica.getLocalidad().getId());
                preparedStatement.setDate(7, new Date(personaFisica.getFechaNacimiento().getTime()));
                preparedStatement.setBoolean(8, personaFisica.getJuridica());
                preparedStatement.setInt(9, personaFisica.getDni());
            }
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

    private String insertPersonaFisica(){
        return "INSERT INTO `personas` (`nombre`, " +
                        "`apellido`, `" +
                        "telefono`, " +
                        "`direccion`, " +
                        "`id_provincia`, " +
                        "`id_localidad`, " +
                        "`fecha_nacimiento`," +
                        "`es_juridica`," +
                        "`dni`) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    private String insertPersonaJuridica(){
        return "INSERT INTO `personas`" +
                "(`nombre`, " +
                "`telefono`, " +
                "`direccion`, " +
                "`id_provincia`, " +
                "`id_localidad`, " +
                "`cuil`, " +
                "`horario_inicio`, " +
                "`horario_fin`, " +
                "`es_juridica`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }
}
