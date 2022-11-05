package com.example.donapp.Data.Usuario;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Enums.StatusResponse;

public class DataUsuarioAsync extends AsyncTask<String, Void, StatusResponse> {

    ListView lv;
    Spinner spn;
    Context context;

    public DataUsuarioAsync(Spinner spn, Context context){
        this.spn = spn;
        this.context = context;
    }

    public DataUsuarioAsync(ListView lv, Context context){
        this.lv = lv;
        this.context = context;
    }

    public DataUsuarioAsync(Context context){
        this.context = context;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        return null;
    }
}
