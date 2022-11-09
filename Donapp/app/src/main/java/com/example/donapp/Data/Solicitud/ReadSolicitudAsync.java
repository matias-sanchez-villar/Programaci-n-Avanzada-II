package com.example.donapp.Data.Solicitud;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Entity.Solicitud;

public class ReadSolicitudAsync extends AsyncTask<String, Void, Solicitud> {

    Solicitud solicitud;
    private Context context;
    private int searcheableId;

    public ReadSolicitudAsync(int id, Context context){
        this.searcheableId = id;
        this.context = context;
    }


    @Override
    protected Solicitud doInBackground(String... strings) {
        return null;
    }
}
