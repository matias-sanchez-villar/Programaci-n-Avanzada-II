package com.example.donapp.Data.Campania;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Entity.Campania;
import com.example.donapp.Enums.StatusResponse;

public class UpdateCampaniaAsync extends AsyncTask<String, Void, StatusResponse> {
    private Campania campania;
    private Context context;

    public UpdateCampaniaAsync(Campania campania, Context context) {
        this.campania = campania;
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
