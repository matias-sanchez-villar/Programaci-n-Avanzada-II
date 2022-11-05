package com.example.donapp.Entity;

public abstract class EntidadBase {
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
