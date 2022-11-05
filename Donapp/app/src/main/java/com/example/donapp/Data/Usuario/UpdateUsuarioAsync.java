package com.example.donapp.Data.Usuario;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.StatusResponse;

public class UpdateUsuarioAsync extends AsyncTask<String, Void, StatusResponse> {

    private Usuario usuario;
    private Context context;

    public UpdateUsuarioAsync(Usuario usuario, Context context){
        this.usuario = usuario;
        this.context = context;
    }

    public UpdateUsuarioAsync(Context context){
        this.context = context;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        return null;
    }
}
