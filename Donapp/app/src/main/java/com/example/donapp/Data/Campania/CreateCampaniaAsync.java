package com.example.donapp.Data.Campania;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Entity.Campana;


public class CreateCampaniaAsync extends AsyncTask<String, Void, Integer> {

    private Campana campana;
    private Context context;

    public CreateCampaniaAsync(Campana campana, Context context) {
        this.campana = campana;
        this.context = context;
    }

    public CreateCampaniaAsync(Context context) { this.context = context; }

    @Override
    protected Integer doInBackground(String... strings) {return null;}
}
