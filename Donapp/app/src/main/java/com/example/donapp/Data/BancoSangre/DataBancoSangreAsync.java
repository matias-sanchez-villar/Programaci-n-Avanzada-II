package com.example.donapp.Data.BancoSangre;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Database.TableDB;
import com.example.donapp.Entity.BancoSangre;
import com.example.donapp.Entity.GlobalPreferences;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Enums.StatusResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBancoSangreAsync extends AsyncTask<String, Void, StatusResponse> {

    private static ArrayList<BancoSangre> listBancoSangre = new ArrayList<BancoSangre>();
    private ListView LVBancoSangre;
    private Spinner spnBancoSangre;
    private Context context;

    public DataBancoSangreAsync(Context context) {
        this.context = context;
    }

    public DataBancoSangreAsync(Spinner spnBancoSangre, Context context) {
        this.spnBancoSangre = spnBancoSangre;
        this.context = context;
    }

    public DataBancoSangreAsync(ListView LVBancoSangre, Context context) {
        this.LVBancoSangre = LVBancoSangre;
        this.context = context;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(queryString());

            BancoSangre bancoSangre;
            listBancoSangre.clear();
            while(rs.next()) {

                bancoSangre = new BancoSangre();
                bancoSangre.setId(rs.getInt("id"));
                bancoSangre.setHospital(rs.getString("hospital"));
                bancoSangre.setProvincia(new Provincia(
                        rs.getInt("id_provincia"),
                        rs.getString("provincia")
                ));
                bancoSangre.setLocalidad(new Localidad(rs.getString("localidad")));

                listBancoSangre.add(bancoSangre);
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
        ArrayAdapter<BancoSangre> adapter = new ArrayAdapter<BancoSangre>(
                this.context,
                android.R.layout.simple_spinner_item,
                listBancoSangre
        );
        if(spnBancoSangre != null){
            spnBancoSangre.setAdapter(adapter);
            spnBancoSangre.setPrompt("PROVINCIAS");
        } else {
            this.LVBancoSangre.setAdapter(adapter);
        }
    }

    public String queryString(){
        return String.format("SELECT bs.id, bs.hospital, bs.id_provincia, p.nombre as provincia, l.nombre as localidad " +
                "FROM %1$s bs " +
                "INNER JOIN provincias p on p.id = bs.id_provincia " +
                "INNER JOIN localidades l on l.id = bs.id_localidad ", TableDB.BANCOS_SANGRE);
    }
}
