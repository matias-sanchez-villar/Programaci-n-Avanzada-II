package com.example.donapp.Data.Solicitud;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Enums.StatusResponse;

public class DeleteSolicitudAsync extends AsyncTask<String, Void, StatusResponse> {

    private int solicitudID;
    private Context context;

    public DeleteSolicitudAsync(int solicitudID, Context context){
        this.solicitudID = solicitudID;
        this.context = context;
    }

    public DeleteSolicitudAsync(Context context){
        this.context = context;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        return null;
    }
}
