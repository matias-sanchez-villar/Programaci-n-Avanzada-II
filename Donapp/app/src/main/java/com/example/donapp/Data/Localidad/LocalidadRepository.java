package com.example.donapp.Data.Localidad;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Data.BaseRepository;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.IQueryRepository;
import com.example.donapp.Interfaces.IQuerySelectRepository;

public class LocalidadRepository extends BaseRepository<Localidad> implements IQuerySelectRepository<Localidad> {

    private AsyncTask<String, Void, StatusResponse> thread;
    private AsyncTask<String, Void, Integer> createThread;

    public LocalidadRepository(Context context){
        this.context = context;
    }

    @Override
    public StatusResponse selectAllForSpinner(Spinner spn) {
        return null;
    }

    @Override
    public StatusResponse selectAllForListView(ListView lv) {
        return null;
    }

    public StatusResponse selectAllForSpinnerByProvincia(Spinner spn, int provinciaId){
        this.thread = new DataLocalidadAsync(spn, context, provinciaId);
        return this.selectAllAsync(thread);
    }
}
