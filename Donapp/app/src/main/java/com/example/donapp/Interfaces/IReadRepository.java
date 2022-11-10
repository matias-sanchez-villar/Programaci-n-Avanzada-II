package com.example.donapp.Interfaces;

import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Enums.StatusResponse;

/**
 * Interfaz para que las clases puedan heredar y definir metodos de acceso a BD
 * Solamente las entidades que son listadas, estas no pueden ser creadas o modificadas.
 */

public interface IReadRepository<T> {
    StatusResponse selectAllForSpinner(Spinner spn);
    StatusResponse selectAllForListView(ListView lv);
}
