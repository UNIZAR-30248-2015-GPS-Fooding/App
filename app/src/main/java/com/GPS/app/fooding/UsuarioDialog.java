package com.GPS.app.fooding;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.GPS.app.fooding.data.Receta;
import com.GPS.app.fooding.data.Usuario;

import java.util.List;

/**
 * Created by coke on 28/10/2015.
 */
public class UsuarioDialog extends DialogFragment {

    private ImageView imageView;
    private Usuario usuarioSeleccionada;


    public UsuarioDialog(Usuario usuarioSeleccionada) {
        // Constructor para pasarle datos de la receta
        this.usuarioSeleccionada = usuarioSeleccionada;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Cargamos elementos del layout
        View view = inflater.inflate(R.layout.usuario_dialog, container);
        TextView recetas = (TextView) view.findViewById(R.id.recetas);
        final TextView puntos = (TextView) view.findViewById(R.id.puntos);


        //Mostramos nombre del user como titulo
        getDialog().setTitle(usuarioSeleccionada.getNombre());
        int width = ViewGroup.LayoutParams.FILL_PARENT;
        int height = ViewGroup.LayoutParams.FILL_PARENT;
        getDialog().getWindow().setLayout(width, height);

        //Mostramos el correo
        List<Receta> listaRecetas = usuarioSeleccionada.getRecetas();
        String listatext = "";
        for(Receta r : listaRecetas){
            listatext=listatext+r.getNombre()+"\n";
        }
        recetas.setText(listatext);

        //Mostramos puntos
        puntos.setText(Integer.toString(usuarioSeleccionada.getScore()));

        return view;
    }

    public static UsuarioDialog newInstance(Usuario u) {
        UsuarioDialog f = new UsuarioDialog(u);
        return f;
    }

}