package com.example.donapp.Data.HistorialMedico;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Entity.HistorialMedico;
import com.example.donapp.Enums.StatusResponse;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class UpdateHistorialMedicoAsync extends AsyncTask<String, Void, StatusResponse> {

    Context context;
    HistorialMedico historial;
    public UpdateHistorialMedicoAsync(Context context, HistorialMedico historial){
        this.context = context;
        this.historial = historial;
    }
    public UpdateHistorialMedicoAsync(Context context){
        this.context = context;
    }
    @Override
    protected StatusResponse doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            PreparedStatement preparedStatement =
                    con.prepareStatement(updateHistorial(), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, historial.getTipoSangre().toString());
            preparedStatement.setInt(2,historial.getPeso());
            preparedStatement.setBigDecimal(3, historial.getAltura());
            preparedStatement.setDate(4, new Date(historial.getUltimaDonacion().getTime()));
            preparedStatement.setBoolean(5,historial.isTatuajes());
            preparedStatement.setBoolean(6, historial.isVacunaAlergia() );
            preparedStatement.setBoolean(7, historial.isExamenSangre());
            preparedStatement.setBoolean(8, historial.isRevisionMedica());
            preparedStatement.setBoolean(9, historial.isTratamientoDental());
            preparedStatement.setBoolean(10, historial.isEndoscopia());
            preparedStatement.setBoolean(11, historial.isEmbarazo());
            preparedStatement.setBoolean(12, historial.isEnfermedaCronica());
            preparedStatement.setBoolean(13, historial.isViaje());
            preparedStatement.setBoolean(14, historial.isAnemia());
            preparedStatement.setBoolean(15, historial.isAccidenteVascular());
            preparedStatement.setBoolean(16, historial.isUsaMedicamentos());
            preparedStatement.setBoolean(17, historial.isHepatitis());
            preparedStatement.setBoolean(18, true);
            preparedStatement.setInt(19, historial.getUsuario().getId());
            preparedStatement.setInt(20, historial.getId());


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

    private String updateHistorial(){
        return "UPDATE historiales_medicos SET " +
                "tipo_sangre= ?, " +
                "peso= ?, " +
                "altura= ?, " +
                "ultima_donacion= ?, " +
                "tatuajes= ?, " +
                "inyeccion_alergia= ?, " +
                "examen_sangre= ?, " +
                "revision_medica= ?, " +
                "tratamiento_dental= ?, " +
                "endoscopia= ?, " +
                "embarazo_parto= ?, " +
                "enfermedad_cronica= ?, " +
                "viaje= ?, " +
                "anemia= ?, " +
                "accidente_vascular= ?, " +
                "usa_medicamentos= ?, " +
                "hepatitis= ?, " +
                "estado= ?," +
                "id_usuario= ? " +
                "WHERE id = ?";
    }

}
