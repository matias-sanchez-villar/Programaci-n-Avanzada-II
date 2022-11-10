package com.example.donapp.Interfaces;

import android.widget.ListView;
import android.widget.Spinner;

import com.example.donapp.Enums.StatusResponse;

/**
 * Interfaz para que las clases puedan heredar y definir metodos de acceso a BD
 *
 */

public interface IQueryRepository<T> {
    Integer create(T entity);
    StatusResponse update(T entity);
    StatusResponse delete(int id);
    StatusResponse selectAll();
    StatusResponse selectAllForSpinner(Spinner spn);
    StatusResponse selectAllForListView(ListView lv);
    T selectEntity(T entity);
}
