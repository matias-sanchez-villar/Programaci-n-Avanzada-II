package com.example.donapp.Data.Persona;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.RequiresPermission;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Database.TableDB;
import com.example.donapp.Entity.Persona;
import com.example.donapp.Entity.PersonaJuridica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadPersonaAsync extends AsyncTask<String, Void, Persona> {
    private Persona persona;
    private Context context;
    private int id;
    private int dni;
    private String cuil;

    public ReadPersonaAsync(Context context){
        this.context = context;
    }

    public ReadPersonaAsync(Persona persona, Context context){
        this.persona = persona;
        this.context = context;
    }

    public ReadPersonaAsync(int dni, Context context){
        this.dni = dni;
        this.context = context;
    }

    public ReadPersonaAsync(String cuil, Context context){
        this.cuil = cuil;
        this.context = context;
    }

    @Override
    protected Persona doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(TableDB.SelectByPropertieInt(
                    TableDB.USUARIO,
                    "dni",
                    this.dni));

            if(rs.next()){
                boolean isJuridica = rs.getBoolean("es_juridica");
                if(isJuridica){
                    this.persona = new PersonaJuridica();
                    this.persona.setId(rs.getInt("id"));
                } else {

                }
                return persona;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
