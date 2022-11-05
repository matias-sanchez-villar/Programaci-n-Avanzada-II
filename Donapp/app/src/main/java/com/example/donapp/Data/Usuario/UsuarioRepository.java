package com.example.donapp.Data.Usuario;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Data.BaseRepository;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.IQueryRepository;


public class UsuarioRepository extends BaseRepository<Usuario> implements IQueryRepository<Usuario> {

    private AsyncTask<String, Void, StatusResponse> thread;

    public UsuarioRepository(Context context){
        this.context = context;
    }

    @Override
    public StatusResponse create(Usuario entity){
        this.thread = new CreateUsuarioAsync(entity, this.context);
        return this.createAsync(entity, thread);
    }

    @Override
    public StatusResponse update(Usuario entity) {
        this.thread = new UpdateUsuarioAsync(entity, this.context);
        return this.updateAsync(entity, thread);
    }

    @Override
    public StatusResponse delete(int id) {
        this.thread = new DeleteUsuarioAsync(id, this.context);
        return this.deleteAsync(id, thread);
    }

    @Override
    public StatusResponse selectAll() {
        this.thread = new DataUsuarioAsync(this.context);
        return this.selectAllAsync(thread);
    }

    @Override
    public StatusResponse selectAllForSpinner(Spinner spn) {
        this.thread = new DataUsuarioAsync(spn, this.context);
        return this.selectAllAsync(thread);
    }

    @Override
    public StatusResponse selectAllForListView(ListView lv) {
        this.thread = new DataUsuarioAsync(lv, this.context);
        return this.selectAllAsync(thread);
    }

}
