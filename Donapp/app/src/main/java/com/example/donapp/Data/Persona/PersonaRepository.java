package com.example.donapp.Data.Persona;

import android.app.Person;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Data.BaseRepository;
import com.example.donapp.Entity.Persona;
import com.example.donapp.Entity.PersonaFisica;
import com.example.donapp.Entity.PersonaJuridica;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.IQueryRepository;

public class PersonaRepository extends BaseRepository<Persona> implements IQueryRepository<Persona> {
    private AsyncTask<String, Void, StatusResponse> thread;
    private AsyncTask<String, Void, Persona> threadEntity;
    private AsyncTask<String, Void, Integer> createThread;

    public PersonaRepository(Context context){
        this.context = context;
    }

    @Override
    public Integer create(Persona entity) {
        return null;
    }

    @Override
    public StatusResponse update(Persona entity) {
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
    public Persona selectEntity(Persona entity) {
        return null;
    }

    public Integer createPersonaFisica(PersonaFisica persona){
        this.createThread = new CreatePersonaAsync(persona, context);
        return createAsync(createThread);
    }

    public Integer createPersonaJuridica(PersonaJuridica persona){
        this.createThread = new CreatePersonaAsync(persona, context);
        return createAsync(createThread);
    }

    public PersonaFisica selectPersonaFisica(PersonaFisica persona){
        this.threadEntity = new ReadPersonaAsync(persona.getDni(), context);
        return new PersonaFisica(this.selectEntity(threadEntity),
                persona.getApellido(),
                persona.getFechaNacimiento(),
                persona.getDni());
    }

    public PersonaJuridica selectPersonaJuridica(PersonaJuridica persona){
        this.threadEntity = new ReadPersonaAsync(persona.getId(), context);
        return new PersonaJuridica(this.selectEntity(threadEntity), persona.getCuil(), persona.getHorarioInicio(), persona.getHorarioFin());
    }
}
