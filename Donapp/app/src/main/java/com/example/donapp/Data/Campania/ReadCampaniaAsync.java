package com.example.donapp.Data.Campania;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Entity.Campana;


public class ReadCampaniaAsync extends AsyncTask<String,Void, Campana> {

    Campana campana;
    private Context context;
    private int searcheableId;

    public ReadCampaniaAsync(Campana campana, Context context) {
        this.campana = campana;
        this.context = context;
    }

    public ReadCampaniaAsync(Context context, int searcheableId) {
        this.context = context;
        this.searcheableId = searcheableId;
    }

    @Override
    protected Campana doInBackground(String... strings) {

        //Conexion a BD
        return null;
    }
}
