package com.example.coke.fooding;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.coke.fooding.data.Ingrediente;
import com.example.coke.fooding.data.Receta;
import com.example.coke.fooding.data.Usuario;

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
        TextView correo = (TextView) view.findViewById(R.id.correo);


        //Mostramos nombre del user como titulo
        getDialog().setTitle("     " + usuarioSeleccionada.getNombre() + "     ");
        int width = ViewGroup.LayoutParams.FILL_PARENT;
        int height = ViewGroup.LayoutParams.FILL_PARENT;
        getDialog().getWindow().setLayout(width, height);

        //Mostramos el correo
        correo.setText(usuarioSeleccionada.getEmail());

        //Incrementa el numero de likes con el boton "+"
        final Button incrementarLikes = (Button) view.findViewById(R.id.likeButton);
        incrementarLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Incrementamos likes
            }
        });

        //Incrementa el numero de dislikes con el boton "-"
        final Button incrementarDislikes = (Button) view.findViewById(R.id.dislikeButton);
        incrementarDislikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Incrementamos dislikes
            }
        });

        return view;
    }

    public static UsuarioDialog newInstance(Usuario u) {
        UsuarioDialog f = new UsuarioDialog(u);
        return f;
    }

}