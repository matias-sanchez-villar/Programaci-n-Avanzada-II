package com.example.donapp.Data.Persona;

import android.content.Context;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Data.BaseRepository;
import com.example.donapp.Entity.PersonaJuridica;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.ICRUDRepository;

public class PersonaJuridicaRepository extends BaseRepository<PersonaJuridica> implements ICRUDRepository<PersonaJuridica> {

    public PersonaJuridicaRepository(Context context){
        this.context = context;
    }

    @Override
    public Integer create(PersonaJuridica entity) {
        return null;
    }

    @Override
    public StatusResponse update(PersonaJuridica entity) {
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
    public PersonaJuridica selectEntity(PersonaJuridica entity) {
        this.selectEntityThread = new ReadPersonaJuridicaAsync(entity.getId(), context);
        return this.selectEntity(selectEntityThread);
    }

    public PersonaJuridica selectEntityByUserId(int userId){
        this.selectEntityThread = new ReadPersonaJuridicaAsync(userId, context);
        return this.selectEntity(selectEntityThread);
    }
}
