package com.example.donapp.Data;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Interfaces.IBaseRepository;

import java.util.concurrent.ExecutionException;

/**
 * Clase generica que hereda de IBaseRepository
 * Se definen metodos que se van a usar en toda la aplicación
 * Las clases que hereden de este metodo en realidad actúan como Service, validar usos
 * Aún asi, dejo el nombre Repository para ver mas adelante si se le debe agregar otro tipo de metodos
 * que directamente peguen en la base de datos.
 * @param <T>
 */

// TODO: Validar si las clases que heredan de esta clase actuan como repo o como service.

public abstract class BaseRepository<T> implements IBaseRepository<T> {

    protected Context context;

    @Override
    public StatusResponse createAsync(T object, AsyncTask<String, Void, StatusResponse> thread) {
        try{
            return thread.execute().get();
        } catch (ExecutionException e){
            return StatusResponse.FAIL;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return StatusResponse.FAIL;
        }
    }

    @Override
    public StatusResponse updateAsync(T object, AsyncTask<String, Void, StatusResponse> thread) {
        try{
            return thread.execute().get();
        } catch (ExecutionException e){
            return StatusResponse.FAIL;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return StatusResponse.FAIL;
        }
    }

    @Override
    public StatusResponse deleteAsync(int id, AsyncTask<String, Void, StatusResponse> thread) {
        try{
            return thread.execute().get();
        } catch (ExecutionException e){
            return StatusResponse.FAIL;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return StatusResponse.FAIL;
        }
    }

    @Override
    public StatusResponse selectAllAsync(AsyncTask<String, Void, StatusResponse> thread) {
        try{
            thread.execute();
            return StatusResponse.SUCCESS;
        } catch (IllegalStateException ex){
            return StatusResponse.FAIL;
        }
    }
}
