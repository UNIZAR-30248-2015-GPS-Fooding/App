package com.example.coke.fooding;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.coke.fooding.data.Ingrediente;
import com.example.coke.fooding.data.Receta;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by coke on 28/10/2015.
 */
public class RecetaDialog extends DialogFragment {

    private ImageView imageView;
    private Receta recetaSeleccionada;

   // private LinearLayout btnIncrementar;

    private TextView comensales;
    private int cuentaComensales = 1;



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
       final TextView descripcion = (TextView) view.findViewById(R.id.descripcion);
        TextView elaboracion = (TextView) view.findViewById(R.id.descripcion2);



        //Mostramos nombre
        getDialog().setTitle("     " + recetaSeleccionada.getNombre() + "     ");
        int width = ViewGroup.LayoutParams.FILL_PARENT;
        int height = ViewGroup.LayoutParams.FILL_PARENT;
        getDialog().getWindow().setLayout(width, height);

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

        //Mostramos los ingredientes
        String listaIngredientes = ingredientesReceta(recetaSeleccionada.getIngredientes(),cuentaComensales );

        descripcion.setText(listaIngredientes);


        //Mostramos los comensales
        comensales = (TextView) view.findViewById(R.id.numComensales);

        //Incrementa el numero de comensales con el boton "+"
        final Button incrementarComensales = (Button) view.findViewById(R.id.button);
        incrementarComensales.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cuentaComensales++;
                String contador=String.valueOf(cuentaComensales);
                comensales.setText(contador);

                descripcion.setText(ingredientesReceta(recetaSeleccionada.getIngredientes(),cuentaComensales));





            }
        });

        //Decrementa el numero de comensales con el boton "-"
        final Button decrementarComensales = (Button) view.findViewById(R.id.button2);
        decrementarComensales.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cuentaComensales > 1) {
                    cuentaComensales--;
                    String contador = String.valueOf(cuentaComensales);
                    comensales.setText(contador);

                    descripcion.setText(ingredientesReceta(recetaSeleccionada.getIngredientes(),cuentaComensales));

                }
            }
        });

        //Mostramos la elaboracion

        elaboracion.setText(recetaSeleccionada.getInstrucciones());

        //Decrementa el numero de comensales con el boton "-"
        final Button meGusta = (Button) view.findViewById(R.id.buttonMeGusta);
        decrementarComensales.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ClientInterface.valorar_receta(recetaSeleccionada.getId(),1, false);
            }
        });


        final Button noMeGusta = (Button) view.findViewById(R.id.buttonNoMeGusta);
        decrementarComensales.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ClientInterface.valorar_receta(recetaSeleccionada.getId(),-1, false);
            }
        });





        return view;
    }

    public static RecetaDialog newInstance(Receta recetaSeleccionada) {
        RecetaDialog f = new RecetaDialog(recetaSeleccionada);
        return f;
    }

    public String ingredientesReceta(List<Ingrediente> listaIngredientes, int cantidad){
        String ingredientes = "";

        for(int i = 0; i< listaIngredientes.size(); i++){
            
            ingredientes = ingredientes +
                    listaIngredientes.get(i).getCantidad() * cantidad +" " +
                    listaIngredientes.get(i).getUds() + " " +
                    listaIngredientes.get(i).getNombre() + "\n";
        }
        return ingredientes;

    }
}