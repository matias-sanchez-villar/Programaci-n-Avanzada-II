package com.example.donapp.Data.Postulacion;

import android.content.Context;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Data.BaseRepository;
import com.example.donapp.Entity.Postulacion;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.ICRUDRepository;

public class PostulacionRepository extends BaseRepository<Postulacion> implements ICRUDRepository<Postulacion> {

    public PostulacionRepository(Context context){
        this.context = context;
    }

    @Override
    public Integer create(Postulacion entity) {
        this.createThread = new CreatePostulacionAsync(entity, context);
        return this.createAsync(createThread);
    }

    @Override
    public StatusResponse update(Postulacion entity) {
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
    public Postulacion selectEntity(Postulacion entity) {
        this.selectEntityThread = new ReadPostulacionAsync(
                entity.getCategoria(),
                entity.getEstado(),
                entity.getUsuario().getId(),
                entity.getRegistroPostulado().getIdRegistro());
        return this.selectEntity(selectEntityThread);
    }
}
