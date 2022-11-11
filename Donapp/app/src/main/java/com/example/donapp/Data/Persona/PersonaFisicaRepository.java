package com.example.donapp.Data.Persona;

import android.content.Context;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Data.BaseRepository;
import com.example.donapp.Entity.PersonaFisica;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.ICRUDRepository;

public class PersonaFisicaRepository extends BaseRepository<PersonaFisica> implements ICRUDRepository<PersonaFisica> {

    public PersonaFisicaRepository(Context context){
        this.context = context;
    }

    @Override
    public Integer create(PersonaFisica entity) {
        return null;
    }

    @Override
    public StatusResponse update(PersonaFisica entity) {
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
    public PersonaFisica selectEntity(PersonaFisica entity) {
        this.selectEntityThread = new ReadPersonaFisicaAsync(entity.getId(), context);
        return this.selectEntity(selectEntityThread);
    }

    public PersonaFisica selectEntityByUserId(int usuarioId){
        this.selectEntityThread = new ReadPersonaFisicaAsync(usuarioId, context);
        return this.selectEntity(selectEntityThread);
    }
}
