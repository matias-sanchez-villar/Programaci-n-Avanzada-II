package com.example.donapp.Data.BancoSangre;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Data.BaseRepository;
import com.example.donapp.Entity.BancoSangre;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.ICRUDRepository;

public class BancoSangreRepository extends BaseRepository<BancoSangre> implements ICRUDRepository<BancoSangre> {

    private AsyncTask<String, Void, StatusResponse> thread;
    private AsyncTask<String, Void, BancoSangre> threadBanco;

    public BancoSangreRepository(Context context){
        this.context = context;
    }

    @Override
    public Integer create(BancoSangre entity) {
        return null;
    }

    @Override
    public StatusResponse update(BancoSangre entity) {
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
        this.thread = new DataBancoSangreAsync(lv, context);
        return this.selectAllAsync(thread);
    }

    @Override
    public BancoSangre selectEntity(BancoSangre banco) {
        this.threadBanco = new ReadBancoSangreAsync(banco.getId(), context);
        return this.selectEntity(threadBanco);
    }
}
