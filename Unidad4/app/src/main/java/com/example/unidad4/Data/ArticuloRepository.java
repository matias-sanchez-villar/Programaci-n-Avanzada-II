package com.example.unidad4.Data;

import android.content.Context;
import android.os.AsyncTask;

import com.example.unidad4.Entity.Articulo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ArticuloRepository extends AsyncTask<String, Void, String> {

    private Articulo articulo;
    private Context ct;
    private boolean isInsert;
    public static String OPERACION_EXITOSA = "Operacion exitosa";

    private static String result2;

    public ArticuloRepository(Articulo art, boolean isInsert, Context ct){
        this.articulo = art;
        this.ct = ct;
        this.isInsert = isInsert;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();

            String query = String.format(
                    (isInsert ? "INSERT INTO articulo VALUES (%1$s, '%2$s', %3$s, %4$s)"
                              : "UPDATE articulo SET " +
                                "nombre = '%2$s', stock = %3$s, idCategoria = %4$s " +
                                "WHERE id = %1$s"),
                    articulo.getId(), articulo.getNombre(), articulo.getStock(),articulo.getIdCategoria());
            int result = st.executeUpdate(query);
            result2 = "";

            response = OPERACION_EXITOSA;
        }
        catch(Exception e) {
            e.printStackTrace();
            result2 = "Conexion no exitosa";
        }
        return response;
    }
}
