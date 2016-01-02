package com.example.coke.fooding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coke.fooding.data.Usuario;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by coke on 15/10/2015.
 */

public class CuentaFragment extends android.support.v4.app.Fragment {

    View view;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    public static boolean pruebaTest = false;
    Usuario u;

    public CuentaFragment (Usuario u){
        this.u = u;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.cuenta_fragment, container, false);

        //Obtenemos elementos del xml
        TextView correo = (TextView) view.findViewById(R.id.correo);
        TextView nombre = (TextView) view.findViewById(R.id.nombre);
        TextView puntos = (TextView) view.findViewById(R.id.likes);
        //TextView dislikes = (TextView) view.findViewById(R.id.dislikes);

        //Rellenamos datos
        correo.setText(u.getEmail());
        nombre.setText(u.getNombre());
        puntos.setText((Integer.toString(u.getScore())));
        return view;
    }



}
