package com.example.donapp.Data.Campania;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Data.BaseRepository;
import com.example.donapp.Data.Solicitud.DataSolicitudAsync;
import com.example.donapp.Data.Usuario.ReadUsuarioAsync;
import com.example.donapp.Entity.Campania;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.ICRUDRepository;

import java.util.ArrayList;

public class CampaniaRepository extends BaseRepository<Campania> implements ICRUDRepository<Campania> {
    private AsyncTask<String, Void, StatusResponse> thread;
    private AsyncTask<String, Void, Integer> createThread;
    private AsyncTask<String, Void, Campania> entityThread;
    private AsyncTask<String, Void, Boolean> validateThread;

    public CampaniaRepository(Context context){
        this.context = context;
    }

    @Override
    public Integer create(Campania entity) {
        this.createThread = new CreateCampaniaAsync(entity, this.context);
        return this.createAsync(createThread);
    }

    @Override
    public StatusResponse update(Campania entity) {
        this.thread = new UpdateCampaniaAsync(entity, this.context);
        return this.updateAsync(thread);
    }

    @Override
    public StatusResponse delete(int id) {
        this.thread = new DeleteCampaniaAsync(id, this.context);
        return this.deleteAsync(id, thread);
    }

    @Override
    public StatusResponse selectAll() {
        this.thread = new DataCampaniaAsync(this.context);
        return this.selectAllAsync(thread);
    }

    @Override
    public StatusResponse selectAllForSpinner(Spinner spn) {
        this.thread = new DataCampaniaAsync(spn, this.context);
        return this.selectAllAsync(thread);
    }

    @Override
    public StatusResponse selectAllForListView(ListView lv) {
        this.thread = new DataCampaniaAsync(lv, context);
        return this.selectAllAsync(thread);
    }

    public StatusResponse selectAllForListViewByIntegerPropertie(
            ListView lv,
            String propertie,
            int value
    )
    {
        this.thread = new DataCampaniaAsync(context, lv, value, propertie);
        return this.selectAllAsync(thread);
    }


    public ArrayList<Campania> selectEntityList(int id) {
        this.listEntityThread  = new ReadCampaniaUsuarioJuridicioAsync(id, context);
        return this.selectEntityListAsync(listEntityThread);
    }

    @Override
    public Campania selectEntity(Campania entity)  {
        return this.selectEntity(entityThread);
    }
    /* Creo que hize cualquier cosa "La idea era validar fecha y Zona(IdLocalidad)
    @Override
    public Boolean selectEntityforDateAndZone(Campania entity) {
        this.validateThread = new ValidateCampaniaAsync(
                entity.getFecha(),
                entity.getLocalidad().getId(),
                context);
        return this.selectEntityforDateAndZone(validateThread);
    }*/
}
