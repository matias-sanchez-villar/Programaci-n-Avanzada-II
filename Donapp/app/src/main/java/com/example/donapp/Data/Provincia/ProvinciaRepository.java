package com.example.donapp.Data.Provincia;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Data.BaseRepository;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.IReadRepository;

public class ProvinciaRepository extends BaseRepository<Provincia> implements IReadRepository<Provincia> {

    private AsyncTask<String, Void, StatusResponse> thread;
    private AsyncTask<String, Void, Integer> createThread;

    public ProvinciaRepository(Context context){
        this.context = context;
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

}
