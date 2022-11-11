package com.example.donapp.Data.Donantes;

import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Data.BaseRepository;
import com.example.donapp.Entity.PersonaFisica;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.IReadRepository;

public class DonanteRepository extends BaseRepository<PersonaFisica> implements IReadRepository<PersonaFisica> {

    @Override
    public StatusResponse selectAllForSpinner(Spinner spn) {
        return null;
    }

    @Override
    public StatusResponse selectAllForListView(ListView lv) {
        return null;
    }
}
