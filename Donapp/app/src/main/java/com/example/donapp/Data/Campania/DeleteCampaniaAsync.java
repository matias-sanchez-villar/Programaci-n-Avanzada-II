package com.example.donapp.Data.Campania;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Enums.StatusResponse;

public class DeleteCampaniaAsync extends AsyncTask<String, Void, StatusResponse> {

    private int campaniaID;
    private Context context;

    public DeleteCampaniaAsync(int campaniaID, Context context) {
        this.campaniaID = campaniaID;
        this.context = context;
    }

    public DeleteCampaniaAsync(Context context) {
        this.context = context;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        return null;
    }
}
