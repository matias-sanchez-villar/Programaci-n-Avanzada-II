package com.example.donapp.Activity.ui.Estadisticas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EstadisticasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public EstadisticasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is estadisticas fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
