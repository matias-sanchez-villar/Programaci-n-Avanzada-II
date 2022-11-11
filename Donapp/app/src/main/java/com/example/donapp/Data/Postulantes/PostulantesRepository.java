package com.example.donapp.Data.Postulantes;

import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Data.BaseRepository;
import com.example.donapp.Entity.PersonaFisica;
import com.example.donapp.Enums.Categoria;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.IReadRepository;

public class PostulantesRepository extends BaseRepository<PersonaFisica> implements IReadRepository<PersonaFisica> {

    @Override
    public StatusResponse selectAllForSpinner(Spinner spn) {
        return null;
    }

    @Override
    public StatusResponse selectAllForListView(ListView lv) {
        return null;
    }

    public StatusResponse selectAllByIdRegistro(ListView lv, int id, Categoria categoria){
        this.mainThread = new DataPostulantesAsync(context, lv, id, categoria);
        return this.selectAllAsync(mainThread);
    }
}
