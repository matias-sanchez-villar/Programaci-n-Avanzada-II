package com.example.donapp.Data.Solicitud;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Data.BaseRepository;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.IQueryRepository;

public class SolicitudRepository extends BaseRepository<Solicitud> implements IQueryRepository<Solicitud> {
    private AsyncTask<String, Void, StatusResponse> thread;

    public SolicitudRepository(Context context){
        this.context = context;
    }

    @Override
    public Integer create(Solicitud entity) {
        return null;
    }

    @Override
    public StatusResponse update(Solicitud entity) {
        return null;
    }

    @Override
    public StatusResponse delete(int id) {
        return null;
    }

    @Override
    public StatusResponse selectAll() {
        return null;
    }

    @Override
    public StatusResponse selectAllForSpinner(Spinner spn) {
        return null;
    }

    @Override
    public StatusResponse selectAllForListView(ListView lv) {
        this.thread = new DataSolicitudAsync(lv, context);
        return this.selectAllAsync(thread);
    }

    @Override
    public Solicitud selectEntity(Solicitud entity) {
        return null;
    }
}
