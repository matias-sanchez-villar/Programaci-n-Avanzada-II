package com.example.donapp.Data.BancoSangre;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Database.DataDB;
import com.example.donapp.Database.TableDB;
import com.example.donapp.Entity.BancoSangre;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.TipoUsuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadBancoSangreAsync extends AsyncTask<String, Void, BancoSangre> {
    BancoSangre bancoSangre;
    Context context;
    int id;

    public ReadBancoSangreAsync(int id, Context context){
        this.id = id;
        this.context = context;
    }

    @Override
    protected BancoSangre doInBackground(String... strings) {
        try {
            Class.forName(DataDB.driver);
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(queryString(id));

            if(rs.next()){
                this.bancoSangre = new BancoSangre();
                this.bancoSangre.setId(id);
                this.bancoSangre.setHospital(rs.getString("hospital"));
                this.bancoSangre.setDireccion(rs.getString("direccion"));
                this.bancoSangre.setProvincia(new Provincia(
                        rs.getInt("id_provincia"),
                        rs.getString("provincia")
                ));
                this.bancoSangre.setLocalidad(new Localidad(rs.getString("localidad")));
                return bancoSangre;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String queryString(int id){
        return String.format("SELECT bs.hospital, bs.direccion, bs.id_provincia, p.nombre as provincia, l.nombre as localidad " +
                "FROM %1$s bs " +
                "INNER JOIN provincias p on p.id = bs.id_provincia " +
                "INNER JOIN localidades l on l.id = bs.id_localidad " +
                "WHERE bs.id = %2$s ", TableDB.BANCOS_SANGRE, id);
    }
}
