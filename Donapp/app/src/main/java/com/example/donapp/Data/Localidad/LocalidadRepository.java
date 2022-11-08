package com.example.donapp.Data.Localidad;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Data.BaseRepository;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.IQueryRepository;

public class LocalidadRepository extends BaseRepository<Localidad> implements IQueryRepository<Localidad> {

    private AsyncTask<String, Void, StatusResponse> thread;
    private AsyncTask<String, Void, Integer> createThread;

    public LocalidadRepository(Context context){
        this.context = context;
    }

    @Override
    public Integer create(Localidad entity) {
        return null;
    }

    @Override
    public StatusResponse update(Localidad entity) {
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
        return null;
    }

    @Override
    public Localidad selectEntity(Localidad entity) {
        return null;
    }

    public StatusResponse selectAllForSpinnerByProvincia(Spinner spn, int provinciaId){
        this.thread = new DataLocalidadAsync(spn, context, provinciaId);
        return this.selectAllAsync(thread);
    }
}
