package com.example.donapp.Data.Usuario;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Data.BaseRepository;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.ICRUDRepository;


public class UsuarioRepository extends BaseRepository<Usuario> implements ICRUDRepository<Usuario> {

    private AsyncTask<String, Void, StatusResponse> thread;
    private AsyncTask<String, Void, Integer> createThread;
    private AsyncTask<String, Void, Usuario> entityThread;

    public UsuarioRepository(Context context){
        this.context = context;
    }

    @Override
    public Integer create(Usuario entity){
        this.createThread = new CreateUsuarioAsync(entity, this.context);
        return this.createAsync(createThread);
    }

    @Override
    public StatusResponse update(Usuario entity) {
        this.thread = new UpdateUsuarioAsync(entity, this.context);
        return this.updateAsync(thread);
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

    @Override
    public Usuario selectEntity(Usuario entity) {
        this.entityThread = new ReadUsuarioAsync(
                entity.getNombreUsuario(),
                entity.getPassword(),
                context);
        return this.selectEntity(entityThread);
    }

}
