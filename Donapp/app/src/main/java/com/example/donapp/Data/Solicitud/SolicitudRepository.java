package com.example.donapp.Data.Solicitud;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Data.BaseRepository;
import com.example.donapp.Data.Usuario.ReadUsuarioAsync;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.IQueryRepository;

public class SolicitudRepository extends BaseRepository<Solicitud> implements IQueryRepository<Solicitud> {
    private AsyncTask<String, Void, StatusResponse> thread;
    private AsyncTask<String, Void, Integer> createThread;
    private AsyncTask<String, Void, Solicitud> entityThread;

    public SolicitudRepository(Context context){
        this.context = context;
    }

    @Override
    public Integer create(Solicitud entity) {
        this.createThread = new CreateSolicitudAsync(entity, this.context);
        return this.createAsync(createThread);
    }

    @Override
    public StatusResponse update(Solicitud entity) {
        this.thread = new UpdateSolicitudAsync(entity, this.context);
        return this.updateAsync(thread);
    }

    @Override
    public StatusResponse delete(int id) {
        this.thread = new DeleteSolicitudAsync(id, this.context);
        return this.deleteAsync(id, thread);
    }

    @Override
    public StatusResponse selectAll() {
        this.thread = new DataSolicitudAsync(this.context);
        return this.selectAllAsync(thread);
    }

    @Override
    public StatusResponse selectAllForSpinner(Spinner spn) {
        this.thread = new DataSolicitudAsync(spn, this.context);
        return this.selectAllAsync(thread);
    }

    @Override
    public StatusResponse selectAllForListView(ListView lv) {
        this.thread = new DataSolicitudAsync(lv, context);
        return this.selectAllAsync(thread);
    }

    @Override
    public Solicitud selectEntity(Solicitud entity) {
        return this.selectEntity(entityThread);
    }
}
