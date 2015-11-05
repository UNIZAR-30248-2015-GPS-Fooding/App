package com.example.coke.fooding;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by coke on 28/10/2015.
 */
public class RecetaDialog extends DialogFragment {

    private ImageView imageView;

    public RecetaDialog() {
        // Constructor vacio para pasarle la receta para coger datos
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.receta_dialog, container);
        ImageView imageView = (ImageView) view.findViewById(R.id.icono);
        imageView.setImageResource(R.mipmap.fish_logo);
        getDialog().setTitle("Titulo");

        return view;
    }

    public static RecetaDialog newInstance() {
        RecetaDialog f = new RecetaDialog();
        return f;
    }
}