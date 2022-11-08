package com.example.donapp.Data.Solicitud;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Entity.Solicitud;
import com.example.donapp.Enums.StatusResponse;

public class CreateSolicitudAsync extends AsyncTask<String, Void, StatusResponse> {

    private Solicitud solicitud;
    private Context context;

    public CreateSolicitudAsync(Solicitud solicitud, Context context){
        this.solicitud = solicitud;
        this.context = context;
    }

    public CreateSolicitudAsync(Context context){
        this.context = context;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        return null;
    }
}
