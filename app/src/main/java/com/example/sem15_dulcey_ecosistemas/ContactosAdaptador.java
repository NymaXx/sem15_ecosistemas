package com.example.sem15_dulcey_ecosistemas;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ContactosAdaptador extends BaseAdapter {

    private ArrayList<Contacto> contactos;

    public ContactosAdaptador(){

        contactos = new ArrayList<>();
    }

    public void addContacto (Contacto contactosAnadir){
        contactos.add(contactosAnadir);
        notifyDataSetChanged();
    }

    public void clear() {
        contactos.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return contactos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View renglon, ViewGroup lista) {

        LayoutInflater inflater = LayoutInflater.from(lista.getContext());
        View renglonView = inflater.inflate(R.layout.row, null);

        Contacto contactosAnadir = contactos.get(position);


        TextView nombreContactosLista = renglonView.findViewById(R.id.nombreContactosLista);
        TextView numeroContactosLista = renglonView.findViewById(R.id.numeroContactosLista);
        Button botonEliminarContacto = renglonView.findViewById(R.id.botonEliminarContacto);

        nombreContactosLista.setText(contactosAnadir.getNombreContacto());
        numeroContactosLista.setText(contactosAnadir.getNumeroContacto());

        botonEliminarContacto.setOnClickListener(
                (v)->{

                    String id = contactos.get(position).getId();
                    Log.e("TAG", contactos.get(position).getId());
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("contactos").child(contactos.get(position).getIdUsuario()).child(id);

                    reference.setValue(null);
                }
        );

        return renglonView;
    }
}
