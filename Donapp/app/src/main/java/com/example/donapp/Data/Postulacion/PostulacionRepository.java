package com.example.donapp.Data.Postulacion;

import android.content.Context;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Activity.ui.Postulaciones.DataPostulacionesAsync;
import com.example.donapp.Data.BaseRepository;
import com.example.donapp.Entity.Postulacion;
import com.example.donapp.Enums.Categoria;
import com.example.donapp.Enums.EstadoPostulacion;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.ICRUDRepository;
import com.mysql.fabric.xmlrpc.base.Data;

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
        this.mainThread = new UpdatePostulacionAsync(context,
                entity.getId());
        return this.updateAsync(mainThread);
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

    public Postulacion selectEntityByRegistroAndCategoria(Postulacion entity, int personaId){
        this.selectEntityThread = new ReadPostulacionAsync(context,
                personaId,
                entity.getRegistroPostulado().getIdRegistro(),
                entity.getRegistroPostulado().getCategoriaPostulacion());
        return this.selectEntity(selectEntityThread);
    }

    public StatusResponse selectAllByEstado(ListView lv, EstadoPostulacion estado, int usuarioId){
        this.mainThread = new DataPostulacionesAsync(lv, context, estado, usuarioId);
        return this.selectAllAsync(mainThread);
    }

    public StatusResponse selectAllById(ListView lv, int id){
        this.mainThread = new DataPostulacionesAsync(lv, context, id);
        return this.selectAllAsync(mainThread);
    }
}
