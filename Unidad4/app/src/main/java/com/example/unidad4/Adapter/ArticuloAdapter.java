package com.example.unidad4.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.unidad4.Entity.Articulo;
import com.example.unidad4.R;

import java.util.List;

public class ArticuloAdapter extends ArrayAdapter<Articulo> {

    public ArticuloAdapter(Context context, List<Articulo> objetos) {
        super(context, R.layout.fragment_listar, objetos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.fragment_listar, null);
        return item;
    }
}
