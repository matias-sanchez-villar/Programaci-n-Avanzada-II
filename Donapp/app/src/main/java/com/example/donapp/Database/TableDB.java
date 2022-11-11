package com.example.donapp.Database;

public final class TableDB {
    public static String USUARIO = "usuarios";
    public static String CRITICIDAD = "criticidad";
    public static String PROVINCIA = "provincias";
    public static String LOCALIDAD = "localidades";
    public static String PERSONA = "personas";
    public static String SOLICITUD = "solicitudes";
    public static String CAMPANIA = "campanias";
    public static String BANCOS_SANGRE = "bancos_sangre";
    public static String POSTULACION = "postulaciones";

    public static String SelectAll(String table){
        return "SELECT * FROM " + table;
    }

    public static String SelectByPropertieString(String table, String column, String value){
        return String.format("SELECT * FROM %1$s WHERE %2$s = '%3$s'", table, column, value);
    }

    public static String SelectByPropertieInt(String table, String column, int value){
        return String.format("SELECT * FROM %1$s WHERE %2$s = %3$s", table, column, value);
    }

    public static String DeletePreparedStatement(String table){
        return String.format("DELETE FROM %1$s WHERE id = ?", table);
    }
}
