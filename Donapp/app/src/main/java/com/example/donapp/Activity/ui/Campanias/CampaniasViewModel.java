package com.example.donapp.Activity.ui.Campanias;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CampaniasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CampaniasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}