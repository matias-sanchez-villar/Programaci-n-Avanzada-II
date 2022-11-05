package com.example.donapp.Data.Usuario;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.StatusResponse;

public class DeleteUsuarioAsync extends AsyncTask<String, Void, StatusResponse> {

    private int usuarioId;
    private Context context;

    public DeleteUsuarioAsync(int usuarioId, Context context){
        this.usuarioId = usuarioId;
        this.context = context;
    }

    public DeleteUsuarioAsync(Context context){
        this.context = context;
    }

    @Override
    protected StatusResponse doInBackground(String... strings) {
        return null;
    }
}
