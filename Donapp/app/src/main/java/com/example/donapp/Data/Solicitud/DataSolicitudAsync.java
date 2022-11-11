package com.example.donapp.Data.Solicitud;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Database.TableDB;
import com.example.donapp.Entity.Criticidad;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.EstadoSolicitud;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataSolicitudAsync extends AsyncTask<String, Void, StatusResponse> {

    private ListView lvSolicitud;
    private Context context;
    private Spinner spn;
    private ArrayList<Solicitud> listSolicitud = new ArrayList<Solicitud>();
    private int integerPropertie = -1;
    private String stringPropertie;
    private String searcheablePropertie;

    public DataSolicitudAsync(ListView lv, Context ct){
        this.lvSolicitud = lv;
        this.context = ct;
    }

    public DataSolicitudAsync(Context context){
        this.context = context;
    }

    public DataSolicitudAsync(Spinner spn, Context context){
        this.spn = spn;
        this.context = context;
    }

    public DataSolicitudAsync(
            Context context,
            ListView lv,
            int integerPropertie,
            String searcheablePropertie
    )
    {
        this.lvSolicitud = lv;
        this.context = context;
        this.integerPropertie = integerPropertie;
        this.searcheablePropertie = searcheablePropertie;
    }

    public DataSolicitudAsync(
            Context context,
            ListView lv,
            String stringPropertie,
            String searcheablePropertie
    )
    {
        this.lvSolicitud = lv;
        this.context = context;
        this.stringPropertie = stringPropertie;
        this.searcheablePropertie = searcheablePropertie;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                    findByIntegerPropertie()
                    ? querySolicitudWithCriticidadByIntegerPropertie(searcheablePropertie, integerPropertie)
                    : findByStringPropertie()
                    ? querySolicitudWithCriticidadLikeStringPropertie(searcheablePropertie, stringPropertie)
                    : querySolicitudWithCriticidad()
            );

            Solicitud solicitud;
            listSolicitud.clear();
            while(rs.next()) {

                solicitud = new Solicitud();
                solicitud.setId(rs.getInt("id"));
                solicitud.setCodigo(rs.getString("codigo"));
                solicitud.setNombre(rs.getString("nombre"));
                solicitud.setApellido(rs.getString("apellido"));
                solicitud.setFecha(rs.getDate("fecha"));
                solicitud.setProvincia(new Provincia(rs.getInt("id_provincia")));
                solicitud.setLocalidad(new Localidad(rs.getInt("id_localidad")));
                solicitud.setDireccion(rs.getString("direccion"));
                solicitud.setUsuario(new Usuario(rs.getInt("id_usuario")));
                solicitud.setCantidadDonantes(rs.getInt("cantidad_donantes"));
                solicitud.setCantidadDonantesConfirmados(rs.getInt("cant_donantes_confirmados"));
                solicitud.setEstado(EstadoSolicitud.getTipoEstadoSolicitud(rs.getInt("estado")));
                solicitud.setCriticidad(
                        new Criticidad(rs.getInt("id_criticidad"), rs.getString("descripcion"))
                );

                listSolicitud.add(solicitud);
            }
            return StatusResponse.SUCCESS;
        }
        catch(Exception e) {
            e.printStackTrace();
            return StatusResponse.FAIL;
        }
    }

    @Override
    protected void onPostExecute(StatusResponse response) {

        // TODO: Encontrar mejor forma de usar el if, no se debería cargar el mismo item a spn o lv.
        ArrayAdapter<Solicitud> adapter = new ArrayAdapter<Solicitud>(
                this.context,
                R.layout.solicitudes_list_item,
                R.id.tvListSolicitudItem,
                listSolicitud
        );
        this.lvSolicitud.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public String querySolicitudWithCriticidad(){
        return "SELECT sol.*, c.descripcion AS 'descripcion', c.id AS 'id_criticidad' " +
                "FROM `solicitudes` sol " +
                "INNER JOIN `criticidad` c ON c.id = sol.id_criticidad " +
                "WHERE sol.estado = 1";
    }

    public String querySolicitudWithCriticidadByIntegerPropertie(
            String propertie,
            int value){
        return String.format("SELECT sol.*, c.descripcion AS 'descripcion', c.id AS 'id_criticidad' " +
                "FROM `solicitudes` sol " +
                "INNER JOIN `criticidad` c ON c.id = sol.id_criticidad " +
                "WHERE sol.%1$s = %2$s ORDER BY fecha", propertie, value);
    }

    public String querySolicitudWithCriticidadLikeStringPropertie(
            String propertie,
            String value){
        return String.format("SELECT sol.*, c.descripcion AS 'descripcion', c.id AS 'id_criticidad' " +
                "FROM `solicitudes` sol " +
                "INNER JOIN `criticidad` c ON c.id = sol.id_criticidad " +
                "WHERE sol.%1$s LIKE '%" + "'%2$s'" + "%' ORDER BY fecha", propertie, value);
    }

    public boolean findByIntegerPropertie(){
        return this.integerPropertie != -1 && this.searcheablePropertie != null;
    }

    public boolean findByStringPropertie(){
        return this.stringPropertie != null && searcheablePropertie != null;
    }
}
