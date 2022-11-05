package com.example.donapp.Interfaces;

import android.content.Context;
import android.os.AsyncTask;

import com.example.donapp.Enums.StatusResponse;

public interface IBaseRepository<T> {
    StatusResponse createAsync(T object, AsyncTask<String, Void, StatusResponse> thread);
    StatusResponse updateAsync(T object, AsyncTask<String, Void, StatusResponse> thread);
    StatusResponse deleteAsync(int id, AsyncTask<String, Void, StatusResponse> thread);
    StatusResponse selectAllAsync(AsyncTask<String, Void, StatusResponse> thread);
}
