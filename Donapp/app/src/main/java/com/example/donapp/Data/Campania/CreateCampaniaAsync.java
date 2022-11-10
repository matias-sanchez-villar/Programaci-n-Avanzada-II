package com.example.donapp.Data.Campania;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Entity.Campania;


public class CreateCampaniaAsync extends AsyncTask<String, Void, Integer> {

    private Campania campania;
    private Context context;

    public CreateCampaniaAsync(Campania campania, Context context) {
        this.campania = campania;
        this.context = context;
    }

    public CreateCampaniaAsync(Context context) { this.context = context; }

    @Override
    protected Integer doInBackground(String... strings) {return null;}
}
