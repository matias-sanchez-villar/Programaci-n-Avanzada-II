package com.example.unidad4.Data;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.unidad4.Entity.Articulo;
import com.example.unidad4.Entity.Categoria;
import com.example.unidad4.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataCategoria extends AsyncTask<String, Void, String> {
    private Spinner categoriasSpinner;
    private Context context;

    private static String result2;
    private static ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();

    //Recibe por constructor el listView
    //Constructor
    public DataCategoria(Spinner spn, Context ct)
    {
        categoriasSpinner = spn;
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
            ResultSet rs = st.executeQuery("SELECT * FROM categoria");
            result2 = " ";

            Categoria categoria;
            listaCategorias.clear();
            while(rs.next()) {
                categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setDescripcion(rs.getString("descripcion"));

                //Agregamos el nombre del articulo
                listaCategorias.add(categoria);
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
        ArrayAdapter<Categoria> adapter = new ArrayAdapter<Categoria>(
                this.context,
                android.R.layout.simple_spinner_item,
                listaCategorias
        );
        categoriasSpinner.setAdapter(adapter);
        categoriasSpinner.setPrompt("CATEGORIAS");
    }
}
