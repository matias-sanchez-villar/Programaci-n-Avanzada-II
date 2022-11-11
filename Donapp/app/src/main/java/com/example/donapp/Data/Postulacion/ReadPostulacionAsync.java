package com.example.donapp.Data.Postulacion;

import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Entity.Criticidad;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Postulacion;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.Categoria;
import com.example.donapp.Enums.EstadoPostulacion;
import com.example.donapp.Enums.EstadoSolicitud;
import com.example.donapp.Interfaces.IEstado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadPostulacionAsync extends AsyncTask<String, Void, Postulacion> {

        Categoria categoria;
        IEstado estado;
        int idUsuario;
        int idRegistroPostulable;

        public ReadPostulacionAsync(Categoria categoria,
                                    IEstado estado,
                                    int idUsuario,
                                    int idRegistroPostulable){
                this.categoria = categoria;
                this.estado = estado;
                this.idUsuario = idUsuario;
                this.idRegistroPostulable = idRegistroPostulable;
        }

        @Override
        protected Postulacion doInBackground(String... strings) {
                try {
                        Class.forName(DataDB.driver);
                        Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(
                                searchQuery());

                        Postulacion postulacion;
                        if(rs.next()) {

                                postulacion = new Postulacion();
                                postulacion.setId(rs.getInt("id"));
                                postulacion.setCodigo(rs.getString("codigo"));
                                postulacion.setFechaGeneracion(rs.getDate("fecha_generacion"));

                                return postulacion;
                        }
                        return null;
                }
                catch(Exception e) {
                        e.printStackTrace();
                        return null;
                }
        }

        private String searchQuery(){
                return String.format("SELECT * " +
                        "FROM `postulaciones` " +
                        "WHERE id_usuario = %1$s " +
                        "AND categoria = %2$s " +
                        "AND id_registro_postulado = %3$s " +
                        "AND estado = 0", idUsuario, categoria.ordinal(), idRegistroPostulable);
        }
}
