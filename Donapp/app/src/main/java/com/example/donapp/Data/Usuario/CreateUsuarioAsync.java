package com.example.donapp.Data.Usuario;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.StatusResponse;

public class CreateUsuarioAsync extends AsyncTask<String, Void, StatusResponse> {

    private Usuario newUsuario;
    private Context context;

    public CreateUsuarioAsync(Usuario newUsuario, Context context){
        this.newUsuario = newUsuario;
        this.context = context;
    }

    public CreateUsuarioAsync(Context context){
        this.context = context;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        return null;
    }
}
