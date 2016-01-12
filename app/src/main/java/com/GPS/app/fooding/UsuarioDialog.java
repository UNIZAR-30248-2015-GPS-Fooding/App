package com.GPS.app.fooding;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.GPS.app.fooding.connection.Access;
import com.GPS.app.fooding.connection.ClientInterface;
import com.GPS.app.fooding.data.Receta;
import com.GPS.app.fooding.data.Usuario;

import java.util.LinkedList;
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
        Usuario u = ClientInterface.info_usuario(usuarioSeleccionada.getEmail(), false);
 //       Toast.makeText(getActivity(), u.getRecetas().get(1).toString(),Toast.LENGTH_SHORT).show();
        List<Receta> listaRecetas = u.getRecetas();
        String listatext = "";
        for(int i=0; i< listaRecetas.size(); i++){
           listatext = listatext + listaRecetas.get(i).getNombre() + "\n";
        }
        recetas.setText(listatext);

        //Mostramos puntos
        puntos.setText(Integer.toString(u.getScore()));

        return view;
    }

    public static UsuarioDialog newInstance(Usuario u) {
        UsuarioDialog f = new UsuarioDialog(u);
        return f;
    }

}