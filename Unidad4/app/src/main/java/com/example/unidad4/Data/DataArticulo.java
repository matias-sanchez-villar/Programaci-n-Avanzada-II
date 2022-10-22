package com.example.unidad4.Data;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.unidad4.Adapter.ArticuloAdapter;
import com.example.unidad4.Entity.Articulo;
import com.example.unidad4.MainActivity;
import com.example.unidad4.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataMainActivity extends AsyncTask<String, Void, String> {

    private ListView lvArticulos;
    private Context context;

    private static String result2;
    private static ArrayList<String> listaArticulos = new ArrayList<String>();

    //Recibe por constructor el listView
    //Constructor
    public DataMainActivity(ListView lv, Context ct)
    {
        lvArticulos = lv;
        context = ct;
    }

    // Conexión con base de datos
    @Override
    protected String doInBackground(String... urls) {
        String response = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM articulo");
            result2 = " ";

            Articulo articulo;
            while(rs.next()) {
                articulo = new Articulo();
                articulo.setId(rs.getInt("id"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setStock(rs.getInt("stock"));
                articulo.setIdCategoria(rs.getInt("idCategoria"));

                //Agregamos el nombre del articulo
                listaArticulos.add(articulo.getNombre());
            }
            response = "Conexion exitosa";
        }
        catch(Exception e) {
            e.printStackTrace();
            result2 = "Conexion no exitosa";
        }
        return response;
    }


    // Ejecuta despues de conectarse a la base de datos.
    // Se cargan datos recibidos desde la BD en algún control. EJ: ListView
    @Override
    protected void onPostExecute(String response) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this.context,
                R.layout.list_item_articulos,
                listaArticulos
        );
        lvArticulos.setAdapter(adapter);
    }
}
