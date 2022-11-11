package com.example.donapp.Data.HistorialMedico;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Entity.HistorialMedico;
import com.example.donapp.Enums.StatusResponse;

public class UpdateHistorialMedicoAsync extends AsyncTask<String, Void, StatusResponse> {

    Context context;
    HistorialMedico historial;
    public UpdateHistorialMedicoAsync(Context context, HistorialMedico historial){
        this.context = context;
        this.historial = historial;
    }
    @Override
    protected StatusResponse doInBackground(String... strings) {
        return null;
    }
}
