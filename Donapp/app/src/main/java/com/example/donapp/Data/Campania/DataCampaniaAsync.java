package com.example.donapp.Data.Campania;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Enums.StatusResponse;

public class DataCampaniaAsync extends AsyncTask<String,Void, StatusResponse> {

    private ListView lvCampania;
    private Context context;
    private Spinner spn;

    public DataCampaniaAsync(Context context) {
        this.context = context;
    }

    public DataCampaniaAsync(ListView lvCampania, Context context) {
        this.lvCampania = lvCampania;
        this.context = context;
    }

    public DataCampaniaAsync(Spinner spn, Context context) {
        this.context = context;
        this.spn = spn;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        //Conexion a DB

        return null;
    }
}
