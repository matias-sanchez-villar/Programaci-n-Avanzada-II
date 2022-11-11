package com.example.donapp.Data.BancoSangre;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Database.TableDB;
import com.example.donapp.Entity.BancoSangre;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Enums.StatusResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BancoSangreRepository extends AsyncTask<String, Void, StatusResponse> {

    ListView lvBancoSangre;
    Context context;
    static ArrayList<BancoSangre> listBancoSangre = new ArrayList<BancoSangre>();

    public BancoSangreRepository(ListView lv, Context ct){
        this.lvBancoSangre = lv;
        this.context = ct;
    }

    public BancoSangreRepository(Context ct){
        this.context = ct;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(TableDB.SelectAll(TableDB.BANCOS_SANGRE));

            BancoSangre bancoSangre;
            listBancoSangre.clear();
            while(rs.next()) {

                bancoSangre = new BancoSangre();
                bancoSangre.setId(rs.getInt("id"));
                bancoSangre.setHospital(rs.getString("hospital"));
                bancoSangre.setDireccion(rs.getString("direccion"));
                bancoSangre.setLocalidad(new Localidad(rs.getInt("idLocalidad")));
                bancoSangre.setProvincia(new Provincia(rs.getInt("idProvincia")));
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
        this.lvBancoSangre.setAdapter(adapter);
    }

}
