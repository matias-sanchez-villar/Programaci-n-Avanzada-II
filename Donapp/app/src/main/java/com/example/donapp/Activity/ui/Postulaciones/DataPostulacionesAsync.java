package com.example.donapp.Activity.ui.Postulaciones;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Database.TableDB;
import com.example.donapp.Entity.BancoSangre;
import com.example.donapp.Entity.Campania;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Postulacion;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.Categoria;
import com.example.donapp.Enums.EstadoPostulacion;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.IRegistroPostulable;
import com.example.donapp.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataPostulacionesAsync extends AsyncTask<String, Void, StatusResponse> {

    ListView listView;
    Context context;
    int usuarioId;
    ArrayList<Postulacion> postulaciones = new ArrayList<Postulacion>();
    EstadoPostulacion estado;

    public DataPostulacionesAsync(ListView listView, Context context, EstadoPostulacion estado, int usuarioId){
        this.listView = listView;
        this.context = context;
        this.estado = estado;
        this.usuarioId = usuarioId;
    }

    public DataPostulacionesAsync(ListView listView, Context context){
        this.listView = listView;
        this.context = context;
    }

    public DataPostulacionesAsync(ListView listView, Context context, int usuarioId){
        this.listView = listView;
        this.context = context;
        this.usuarioId = usuarioId;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                    estado != null
                            ? String.format(TableDB.SelectByPropertieInt(
                                    TableDB.POSTULACION,
                                    "id_usuario",
                                    usuarioId) + " AND estado = %1$s", estado.ordinal())
                            : TableDB.SelectByPropertieInt(TableDB.POSTULACION, "id_usuario", usuarioId)
            );

            Postulacion postulacion;
            postulaciones.clear();
            while(rs.next()) {

                postulacion = new Postulacion();
                postulacion.setId(rs.getInt("id"));
                postulacion.setCodigo(rs.getString("codigo"));
                postulacion.setFechaGeneracion(rs.getDate("fecha_generacion"));
                postulacion.setCategoria(Categoria.getCategoria(rs.getInt("categoria")));
                postulacion.setFechaConfirmacion(rs.getDate("fecha_confirmacion"));
                postulacion.setUsuario(new Usuario(rs.getInt("id_usuario")));
                postulacion.setRegistroPostulado(
                        getRegistroType(
                                postulacion.getCategoria(),
                                rs.getInt("id_registro_postulado")
                        )
                );
                postulacion.setEstado(
                        EstadoPostulacion
                                .getTipoEstadoPostulacion(rs.getInt("estado"))
                );

                postulaciones.add(postulacion);
            }
            return StatusResponse.SUCCESS;
        }
        catch(Exception e) {
            e.printStackTrace();
            return StatusResponse.FAIL;
        }
    }

    public IRegistroPostulable getRegistroType(Categoria categoria, int id){
        if(categoria == Categoria.SOLICITUD){
            return new Solicitud(id);
        } else if(categoria == Categoria.BANCO){
            return new BancoSangre(id);
        } else {
            return new Campania(id);
        }
    }

    @Override
    protected void onPostExecute(StatusResponse response) {
        ArrayAdapter<Postulacion> adapter = new ArrayAdapter<Postulacion>(
                this.context,
                R.layout.postulaciones_list_item,
                R.id.tvListPostulacionItem,
                postulaciones
        );
        listView.setAdapter(adapter);
    }

}
