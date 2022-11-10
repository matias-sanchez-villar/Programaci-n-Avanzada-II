package com.example.donapp.Activity.ui.Postulaciones;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PostulacionesViewModel extends ViewModel{

    private final MutableLiveData<String> mText;

    public PostulacionesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is postulaciones fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
