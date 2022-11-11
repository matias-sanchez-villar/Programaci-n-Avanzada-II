package com.example.donapp.Data.HistorialMedico;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Entity.HistorialMedico;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Enums.TipoSangre;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateHistorialMedicoAsync extends AsyncTask<String, Void, Integer> {

    private HistorialMedico historialMedico;
    private Context context;

    public CreateHistorialMedicoAsync(HistorialMedico historialMedico, Context context){
        this.historialMedico = historialMedico;
        this.context = context;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            PreparedStatement preparedStatement =
                    con.prepareStatement(insertHistorialMedico(), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, TipoSangre.getStringByPosition(historialMedico.getTipoSangre().ordinal()));
            preparedStatement.setInt(2, historialMedico.getPeso());
            preparedStatement.setBigDecimal(3, historialMedico.getAltura());
            preparedStatement.setDate(4, new Date(historialMedico.getUltimaDonacion().getTime()));
            preparedStatement.setBoolean(5, historialMedico.isTatuajes());
            preparedStatement.setBoolean(6, historialMedico.isVacunaAlergia());
            preparedStatement.setBoolean(7, historialMedico.isExamenSangre());
            preparedStatement.setBoolean(8, historialMedico.isRevisionMedica());
            preparedStatement.setBoolean(9, historialMedico.isTratamientoDental());
            preparedStatement.setBoolean(10, historialMedico.isEndoscopia());
            preparedStatement.setBoolean(11, historialMedico.isEmbarazo());
            preparedStatement.setBoolean(12, historialMedico.isEnfermedaCronica());
            preparedStatement.setBoolean(13, historialMedico.isOperacion());
            preparedStatement.setBoolean(14, historialMedico.isViaje());
            preparedStatement.setBoolean(15, historialMedico.isAnemia());
            preparedStatement.setBoolean(16, historialMedico.isAccidenteVascular());
            preparedStatement.setBoolean(17, historialMedico.isUsaMedicamentos());
            preparedStatement.setBoolean(18, historialMedico.isHepatitis());
            preparedStatement.setBoolean(19, true);
            preparedStatement.setInt(20, historialMedico.getUsuario().getId());


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

    public String insertHistorialMedico(){
        return "INSERT INTO `historiales_medicos`(" +
                "`tipo_sangre`, " +
                "`peso`, " +
                "`altura`, " +
                "`ultima_donacion`, " +
                "`tatuajes`, " +
                "`inyeccion_alergia`, " +
                "`examen_sangre`, " +
                "`revision_medica`, " +
                "`tratamiento_dental`, " +
                "`endoscopia`, " +
                "`embarazo_parto`, " +
                "`enfermedad_cronica`, " +
                "`operacion`, " +
                "`viaje`, " +
                "`anemia`, " +
                "`accidente_vascular`, " +
                "`usa_medicamentos`, " +
                "`hepatitis`, " +
                "`estado`, `id_usuario`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }
}
