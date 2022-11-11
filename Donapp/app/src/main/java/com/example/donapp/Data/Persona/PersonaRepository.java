package com.example.donapp.Data.Persona;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Data.BaseRepository;
import com.example.donapp.Entity.Persona;
import com.example.donapp.Entity.PersonaFisica;
import com.example.donapp.Entity.PersonaJuridica;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.ICRUDRepository;

public class PersonaRepository extends BaseRepository<Persona> implements ICRUDRepository<Persona> {
    private AsyncTask<String, Void, StatusResponse> thread;
    private AsyncTask<String, Void, Persona> threadEntity;
    private AsyncTask<String, Void, PersonaFisica> threadEntityPF;
    private AsyncTask<String, Void, PersonaJuridica> threadEntityPJ;
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

    public PersonaFisica selectPersonaFisica(int id){
        this.threadEntityPF = new ReadPersonaFisicaAsync(id, context);
        return (PersonaFisica) this.selectEntity(threadEntityPF);
    }

    //falta lo mismo
    public PersonaJuridica selectPersonaJuridica(int id){
        this.threadEntityPJ = new ReadPersonaJuridicaAsync(id, context);
        return this.selectEntity(threadEntityPJ);
    }
}
