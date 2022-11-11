package com.example.donapp.Data.HistorialMedico;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Entity.Criticidad;
import com.example.donapp.Entity.HistorialMedico;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.EstadoSolicitud;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Enums.TipoSangre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadHistorialMedicoAsync extends AsyncTask<String, Void, HistorialMedico> {

    Context context;
    int usuarioId;
    int id;

    public ReadHistorialMedicoAsync(Context context, int id){
        this.context = context;
        this.usuarioId = id;
    }

    @Override
    protected HistorialMedico doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(queryHistorialMedico(usuarioId));

            HistorialMedico historialMedico;
            if(rs.next()) {

                historialMedico = new HistorialMedico();
                historialMedico.setId(rs.getInt("id"));
                historialMedico.setTipoSangre(TipoSangre.getTipoSangreByString(rs.getString("tipo_sangre")));
                historialMedico.setPeso(rs.getInt("peso"));
                historialMedico.setAltura(rs.getBigDecimal("altura"));
                historialMedico.setUltimaDonacion(rs.getDate("ultima_donacion"));
                historialMedico.setTatuajes(rs.getBoolean("tatuajes"));
                historialMedico.setVacunaAlergia(rs.getBoolean("inyeccion_alergia"));
                historialMedico.setExamenSangre(rs.getBoolean("examen_sangre"));
                historialMedico.setRevisionMedica(rs.getBoolean("revision_medica"));
                historialMedico.setTratamientoDental(rs.getBoolean("tratamiento_dental"));
                historialMedico.setEndoscopia(rs.getBoolean("endoscopia"));
                historialMedico.setEmbarazo(rs.getBoolean("embarazo_parto"));
                historialMedico.setEnfermedaCronica(rs.getBoolean("enfermedad_cronica"));
                historialMedico.setOperacion(rs.getBoolean("operacion"));
                historialMedico.setViaje(rs.getBoolean("viaje"));
                historialMedico.setAnemia(rs.getBoolean("anemia"));
                historialMedico.setAccidenteVascular(rs.getBoolean("accidente_vascular"));
                historialMedico.setUsaMedicamentos(rs.getBoolean("usa_medicamentos"));
                historialMedico.setHepatitis(rs.getBoolean("hepatitis"));

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
        return String.format("SELECT * FROM `historiales_medicos` WHERE id_usuario = %1$s", id);
    }
}
