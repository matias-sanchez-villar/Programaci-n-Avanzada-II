package com.example.donapp.Data.Campania;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Entity.Campana;
import com.example.donapp.Enums.StatusResponse;

public class UpdateCampaniaAsync extends AsyncTask<String, Void, StatusResponse> {
    private Campana campana;
    private Context context;

    public UpdateCampaniaAsync(Campana campana, Context context) {
        this.campana = campana;
        this.context = context;
    }

    public UpdateCampaniaAsync(Context context) {
        this.context = context;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        return null;
    }
}
