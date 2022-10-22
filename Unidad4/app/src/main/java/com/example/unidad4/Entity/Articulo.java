package com.example.unidad4.Entity;

public class Articulo {

    private int id;
    private String nombre;
    private int stock;
    private int idCategoria;

    public Articulo()
    {

    }

    public Articulo(int id, String nombre, int stock, int idCategoria) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.idCategoria = idCategoria;
    }

    public int getId(){
        return this.id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getStock(){
        return this.stock;
    }

    public int getIdCategoria(){
        return this.idCategoria;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public void setIdCategoria(int idCategoria){
        this.idCategoria = idCategoria;
    }
}
