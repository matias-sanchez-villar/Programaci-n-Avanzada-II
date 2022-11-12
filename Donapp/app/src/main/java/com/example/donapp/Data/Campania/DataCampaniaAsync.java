package com.example.donapp.Data.Campania;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Database.TableDB;
import com.example.donapp.Entity.Campania;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.EstadoCampania;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataCampaniaAsync extends AsyncTask<String,Void, StatusResponse> {

    private ListView lvCampania;
    private Context context;
    private Spinner spn;
    private static ArrayList<Campania> listCampania = new ArrayList<Campania>();
    private int integerPropertie = -1;
    private String stringPropertie;
    private String searcheablePropertie;

    public DataCampaniaAsync(ListView lvCampania, Context context) {
        this.lvCampania = lvCampania;
        this.context = context;
    }

    public DataCampaniaAsync(Context context) {
        this.context = context;
    }

    public DataCampaniaAsync(Spinner spn, Context context) {
        this.context = context;
        this.spn = spn;
    }

    public DataCampaniaAsync(
            Context context,
            ListView lv,
            int integerPropertie,
            String searcheablePropertie
    )
    {
        this.lvCampania = lv;
        this.context = context;
        this.integerPropertie = integerPropertie;
        this.searcheablePropertie = searcheablePropertie;
    }

    public DataCampaniaAsync(
            Context context,
            ListView lv,
            String stringPropertie,
            String searcheablePropertie
    )
    {
        this.lvCampania = lv;
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
            ResultSet rs = st.executeQuery(TableDB.SelectAll(TableDB.CAMPANIA) + " WHERE `estado` = 1");

            Campania campania;
            listCampania.clear();
            while(rs.next()) {

                campania = new Campania();
                campania.setId(rs.getInt("id"));
                campania.setCodigo(rs.getString("codigo"));
                campania.setNombreCampana(rs.getString("nombre_campania"));
                campania.setFecha(rs.getDate("fecha"));
                campania.setFechaFin(rs.getDate("fecha_fin"));
                campania.setLocalidad(new Localidad(rs.getInt("id_localidad")));
                campania.setProvincia(new Provincia(rs.getInt("id_provincia")));
                campania.setDireccion(rs.getString("direccion"));
                campania.setCantDonantes(rs.getInt("cantidad_donantes"));
                campania.setCantDonantesConfirmados(rs.getInt("cantidad_donantes_confirmados"));
                campania.setCantDias(rs.getInt("cantidad_dias"));
                campania.setInstitucion(new Usuario(rs.getInt("id_institucion")));
                campania.setUsuario(new Usuario(rs.getInt("id_usuario")));
                campania.setEstado(EstadoCampania.getTipoEstadoCampania(rs.getInt("estado")));

                listCampania.add(campania);
            }
            return StatusResponse.SUCCESS;
        }
        catch(Exception e) {
            e.printStackTrace();
            return StatusResponse.FAIL;
        }
    }

    @Override
    protected void onPostExecute(StatusResponse statusResponse) {
        // TODO: Encontrar mejor forma de usar el if, no se debería cargar el mismo item a spn o lv.

        ArrayAdapter<Campania> adapter = new ArrayAdapter<Campania>(
                this.context,
                R.layout.campanias_list_item,
                R.id.tvListCampaniaItem,
                listCampania
        );
        this.lvCampania.setAdapter(adapter);
    }

    public boolean findByIntegerPropertie(){
        return this.integerPropertie != -1 && this.searcheablePropertie != null;
    }

    public boolean findByStringPropertie(){
        return this.stringPropertie != null && searcheablePropertie != null;
    }



}
