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

import com.GPS.app.fooding.connection.ClientInterface;
import com.GPS.app.fooding.data.Ingrediente;
import com.GPS.app.fooding.data.Receta;

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
        TextView autor = (TextView) view.findViewById(R.id.texto_autor);
        TextView valoracion = (TextView) view.findViewById(R.id.texto_valoracion);

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

        //TODO Mostrar autor de la receta

        autor.setText("Autor");


        //Mostramos valoracion
        double media = (double) recetaSeleccionada.getMe_gusta() / (double) (recetaSeleccionada.getNo_me_gusta() + recetaSeleccionada.getMe_gusta()) ;
        if(recetaSeleccionada.getMe_gusta() == 0 || (recetaSeleccionada.getNo_me_gusta() + recetaSeleccionada.getMe_gusta()) == 0){
            valoracion.setText(" 0.0% de votos positivos");
        }
        else{
            valoracion.setText( media + "% de votos positivos");
        }


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
        meGusta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientInterface.valorar_receta(recetaSeleccionada.getId(), 1, true);
                Toast.makeText(getActivity(), "Me gusta", Toast.LENGTH_SHORT).show();
            }
        });

        final Button noMeGusta = (Button) view.findViewById(R.id.buttonNoMeGusta);
        noMeGusta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientInterface.valorar_receta(recetaSeleccionada.getId(),-1, true);
                Toast.makeText(getActivity(), "No me gusta", Toast.LENGTH_SHORT).show();
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