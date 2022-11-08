package com.example.donapp.Data.Solicitud;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Enums.StatusResponse;

public class DataSolicitudAsync extends AsyncTask<String, Void, StatusResponse> {
    Context context;

    public DataSolicitudAsync(Context context){
        this.context = context;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        return null;
    }
}
