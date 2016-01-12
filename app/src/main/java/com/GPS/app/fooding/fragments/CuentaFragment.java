package com.GPS.app.fooding.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.GPS.app.fooding.MainActivity;
import com.GPS.app.fooding.R;
import com.GPS.app.fooding.connection.ClientInterface;
import com.GPS.app.fooding.data.Usuario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

        String email = "";

        File myFile = new File(MainActivity.mPath + "/" + "ficheroUsuarios.txt");

        try {
            FileReader f = new FileReader(myFile);
            BufferedReader b = new BufferedReader(f);
            email = b.readLine();
            b.close();
            System.out.println(correo);

        } catch (IOException e) {
            e.printStackTrace();
        }

        u = ClientInterface.info_usuario(email, false);

        //Rellenamos datos
        //correo.setText(u.getEmail());
        correo.setText(email);
        nombre.setText(u.getNombre());
        puntos.setText((Integer.toString(u.getScore())));

        return view;
    }



}
