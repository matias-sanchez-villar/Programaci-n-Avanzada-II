package com.example.unidad3_tp3;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;


public class AdminSQLite extends SQLiteOpenHelper {

    public AdminSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table usuarios" +
                "(id integer primary key autoincrement, Nombre text , Mail text, password text)";

        String queryParqueos ="CREATE TABLE parqueos" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, id_usuario INTEGER, nro_matricula text," +
                "tiempo INTEGER)";

        db.execSQL(query);
        db.execSQL(queryParqueos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void abrirDB(){
        this.getWritableDatabase();
    }
    public void cerarDB(){
        this.close();
    }

    //Metodo Insertar Usuario con validacion de campos vacios
    public void insertarUsuario(String nom, String mail, String pass){
        ContentValues registro=new ContentValues();
        if(!nom.isEmpty() && !mail.isEmpty() && !pass.isEmpty()){
             registro.put("Nombre",nom);
             registro.put("Mail",mail);
             registro.put("password",pass);
             this.getWritableDatabase().insert("usuarios", null, registro);
        }
    }

    public void insertarParqueo(String matricula, int tiempo, int idUsuario){
        ContentValues nuevoParqueo = new ContentValues();
        nuevoParqueo.put("nro_matricula", matricula);
        nuevoParqueo.put("id_usuario", idUsuario);
        nuevoParqueo.put("tiempo", tiempo);
        this.getWritableDatabase().insert("parqueos", null, nuevoParqueo);
    }

    //Metodo para validar correo existente
    public Cursor ConsultaMail (String correo) throws SQLException{
        Cursor mcursor=null;
        mcursor=this.getReadableDatabase().query("usuarios", new String[] {"id","Nombre","Mail", "password"},
                            "Mail = " + Utilities.SQLString(correo),null,null,null,null);
        return mcursor;
    }

    //Metodo para leer parqueos
    public Cursor LeerParqueos(int idUsuario){
        Cursor c = this.getReadableDatabase().rawQuery("select nro_matricula,tiempo from parqueos where id_usuario ="+ idUsuario,null);
        return c;
    }

}
