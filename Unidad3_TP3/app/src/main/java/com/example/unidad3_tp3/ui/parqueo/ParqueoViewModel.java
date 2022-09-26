package com.example.unidad3_tp3.ui.parqueo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ParqueoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ParqueoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}