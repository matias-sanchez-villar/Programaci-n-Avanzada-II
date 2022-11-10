package com.example.donapp.Data.Campania;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Entity.Campania;


public class ReadCampaniaAsync extends AsyncTask<String,Void, Campania> {

    Campania campania;
    private Context context;
    private int searcheableId;

    public ReadCampaniaAsync(Campania campania, Context context) {
        this.campania = campania;
        this.context = context;
    }

    public ReadCampaniaAsync(Context context, int searcheableId) {
        this.context = context;
        this.searcheableId = searcheableId;
    }

    @Override
    protected Campania doInBackground(String... strings) {

        //Conexion a BD
        return null;
    }
}
