package com.example.donapp.Data.HistorialMedico;

import android.content.Context;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Data.BaseRepository;
import com.example.donapp.Data.Usuario.ReadUsuarioAsync;
import com.example.donapp.Entity.HistorialMedico;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.ICRUDRepository;

public class HistorialMedicoRepository extends BaseRepository<HistorialMedico> implements ICRUDRepository<HistorialMedico> {


    public HistorialMedicoRepository(Context context){
        this.context = context;
    }

    @Override
    public Integer create(HistorialMedico entity) {
        this.createThread = new CreateHistorialMedicoAsync(entity, context);
        return this.createAsync(createThread);
    }

    @Override
    public StatusResponse update(HistorialMedico entity) {
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
    public HistorialMedico selectEntity(HistorialMedico entity) {
        this.selectEntityThread = new ReadHistorialMedicoAsync(context, entity.getId());
        return selectEntity(selectEntityThread);
    }
}
