package com.example.donapp.Entity;

import java.io.Serializable;

public abstract class EntidadBase implements Serializable {
    int id;

    public EntidadBase(){
    }

    public EntidadBase(int id){
        this.id  = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
