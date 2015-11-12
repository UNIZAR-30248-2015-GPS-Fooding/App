package com.example.coke.fooding;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.coke.fooding.data.Receta;

/**
 * Created by coke on 28/10/2015.
 */
public class RecetaDialog extends DialogFragment {

    private ImageView imageView;
    private Receta recetaSeleccionada;

    public RecetaDialog(Receta recetaSeleccionada) {
        // Constructor para pasarle datos de la receta
        this.recetaSeleccionada = recetaSeleccionada;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Cargamos elementos del layout
        View view = inflater.inflate(R.layout.receta_dialog, container);
        ImageView imageView = (ImageView) view.findViewById(R.id.icono);

        //Mostramos nombre
        getDialog().setTitle(recetaSeleccionada.getNombre());
        //Mostramos icono
        if (recetaSeleccionada.getTipo().equalsIgnoreCase("pasta")){
            imageView.setImageResource(R.mipmap.pasta_logo);
        }
        else if (recetaSeleccionada.getTipo().equalsIgnoreCase("postre")){
            imageView.setImageResource(R.mipmap.dessert_logo);
        }
        else if (recetaSeleccionada.getTipo().equalsIgnoreCase("pescado")){
            imageView.setImageResource(R.mipmap.fish_logo);
        }
        else if (recetaSeleccionada.getTipo().equalsIgnoreCase("carne")){
            imageView.setImageResource(R.mipmap.meat_logo);
        }
        else if (recetaSeleccionada.getTipo().equalsIgnoreCase("verdura")){
            imageView.setImageResource(R.mipmap.vegetable_logo);
        }
        else {
            imageView.setImageResource(R.mipmap.random_logo);
        }

        return view;
    }

    public static RecetaDialog newInstance(Receta recetaSeleccionada) {
        RecetaDialog f = new RecetaDialog(recetaSeleccionada);
        return f;
    }
}