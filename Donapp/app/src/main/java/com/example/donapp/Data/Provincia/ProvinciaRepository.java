package com.example.donapp.Data.Provincia;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.loader.content.AsyncTaskLoader;

import com.example.donapp.Data.BaseRepository;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.IQueryRepository;

public class ProvinciaRepository extends BaseRepository<Provincia> implements IQueryRepository<Provincia> {

    private AsyncTask<String, Void, StatusResponse> thread;
    private AsyncTask<String, Void, Integer> createThread;

    public ProvinciaRepository(Context context){
        this.context = context;
    }

    @Override
    public Integer create(Provincia entity) {
        return null;
    }

    @Override
    public StatusResponse update(Provincia entity) {
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
        this.thread = new DataProvinciaAsync(spn, context);
        return this.selectAllAsync(thread);
    }

    @Override
    public StatusResponse selectAllForListView(ListView lv) {
        return null;
    }

    @Override
    public Provincia selectEntity(Provincia entity) {
        return null;
    }
}
