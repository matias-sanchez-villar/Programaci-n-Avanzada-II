package com.example.donapp.Data.HistorialMedico;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Entity.HistorialMedico;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.TipoSangre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadHistorialMedicoByPersonaAsync extends AsyncTask<String, Void, HistorialMedico> {

    Context context;
    int personaId;

    public ReadHistorialMedicoByPersonaAsync(Context context, int personaId){
        this.context = context;
        this.personaId = personaId;
    }

    @Override
    protected HistorialMedico doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(queryHistorialMedico(personaId));

            HistorialMedico historialMedico;
            if(rs.next()) {

                historialMedico = new HistorialMedico();
                historialMedico.setId(rs.getInt("id"));
                historialMedico.setUsuario(new Usuario(rs.getInt("id_usuario")));

                return historialMedico;
            }
            return null;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String queryHistorialMedico(int id){
        return String.format("SELECT hist.*, user.id AS 'id_usuario' FROM " +
                "`historiales_medicos` hist " +
                "INNER JOIN `usuarios` user ON user.id = hist.id_usuario " +
                "INNER JOIN `personas` per ON per.id = user.id_persona " +
                "WHERE per.id = %1$s", id);
    }
}
