package com.GPS.app.fooding;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.SpannableString;

import com.GPS.app.fooding.connection.ClientInterface;
import com.GPS.app.fooding.data.Ingrediente;
import com.GPS.app.fooding.data.Receta;
import com.GPS.app.fooding.connection.Access;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by coke on 28/10/2015.
 */
public class RecetaDialog extends DialogFragment {

    private ImageView imageView;
    private Receta recetaSeleccionada;

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
        TextView titulo = (TextView) view.findViewById(R.id.titulo);
        TextView elaboracion = (TextView) view.findViewById(R.id.descripcion2);
        TextView autor = (TextView) view.findViewById(R.id.texto_autor);
        final TextView valoracion = (TextView) view.findViewById(R.id.texto_valoracion);

        //Mostramos nombre
        SpannableString spanString = new SpannableString(recetaSeleccionada.getNombre());
        spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), 0);
        spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
        titulo.setText(spanString);

        //Mostramos icono
        if (recetaSeleccionada.getTipo().equalsIgnoreCase("pasta")) {
            imageView.setImageResource(R.mipmap.pasta_logo);
        } else if (recetaSeleccionada.getTipo().equalsIgnoreCase("postre")) {
            imageView.setImageResource(R.mipmap.dessert_logo);
        } else if (recetaSeleccionada.getTipo().equalsIgnoreCase("pescado")) {
            imageView.setImageResource(R.mipmap.fish_logo);
        } else if (recetaSeleccionada.getTipo().equalsIgnoreCase("carne")) {
            imageView.setImageResource(R.mipmap.meat_logo);
        } else if (recetaSeleccionada.getTipo().equalsIgnoreCase("verdura")) {
            imageView.setImageResource(R.mipmap.vegetable_logo);
        } else {
            imageView.setImageResource(R.mipmap.random_logo);
        }

        final Receta dataObtained = Access.getReceta(recetaSeleccionada.getId());

        //Mostramos los ingredientes
        String listaIngredientes = ingredientesReceta(dataObtained.getIngredientes(), cuentaComensales);
        descripcion.setText(listaIngredientes);

        //Autor de la receta
        autor.setText(dataObtained.getAutor().getNombre());


        //Mostramos valoracion de la receta
        double media = (double) dataObtained.getMe_gusta() / (double) (dataObtained.getNo_me_gusta() + dataObtained.getMe_gusta());
        if (dataObtained.getMe_gusta() == 0 || (dataObtained.getNo_me_gusta() + dataObtained.getMe_gusta()) == 0) {
            valoracion.setText(" 0.0% de votos positivos");
        } else {
            valoracion.setText(media * 100 + "% de votos positivos");
        }

        //Mostramos los comensales
        comensales = (TextView) view.findViewById(R.id.numComensales);

        //Incrementa el numero de comensales con el boton "+"
        final Button incrementarComensales = (Button) view.findViewById(R.id.button);
        incrementarComensales.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cuentaComensales++;
                String contador = String.valueOf(cuentaComensales);
                comensales.setText(contador);

                descripcion.setText(ingredientesReceta(dataObtained.getIngredientes(), cuentaComensales));
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

                    descripcion.setText(ingredientesReceta(dataObtained.getIngredientes(), cuentaComensales));
                }
            }
        });

        //Mostramos la elaboracion
        elaboracion.setText(dataObtained.getInstrucciones());

        //Boton de me gusta
        final Button meGusta = (Button) view.findViewById(R.id.buttonMeGusta);
        meGusta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File myFile = new File(MainActivity.mPath + "/" + "ficheroUsuarios.txt");

                try {
                    FileReader f = new FileReader(myFile);
                    BufferedReader b = new BufferedReader(f);
                    String correo = b.readLine();
                    System.out.println(correo);
                    b.close();

                    boolean exito = ClientInterface.valorar_receta(dataObtained.getId(), 1, correo, false);
                    if (exito) {
                        Toast.makeText(getActivity(), "Me gusta", Toast.LENGTH_SHORT).show();

                        final Receta dataObtained = Access.getReceta(recetaSeleccionada.getId());

                        double media = (double) dataObtained.getMe_gusta() + 1 / (double) (dataObtained.getNo_me_gusta() + dataObtained.getMe_gusta() + 1);
                        if (dataObtained.getMe_gusta() == 0 || (dataObtained.getNo_me_gusta() + dataObtained.getMe_gusta()) + 1 == 0) {
                            valoracion.setText(" 0.0% de votos positivos");
                        } else {
                            valoracion.setText(media * 100 + "% de votos positivos");
                        }
                    } else {
                        Toast.makeText(getActivity(), "Error al valorar", Toast.LENGTH_SHORT).show();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Por favor, haz login para poder valorar", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Por favor, haz login para poder valorar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Boton de no me gusta
        final Button noMeGusta = (Button) view.findViewById(R.id.buttonNoMeGusta);
        noMeGusta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File myFile = new File(MainActivity.mPath + "/" + "ficheroUsuarios.txt");

                try {
                    FileReader f = new FileReader(myFile);
                    BufferedReader b = new BufferedReader(f);
                    String correo = b.readLine();
                    b.close();

                    boolean exito = ClientInterface.valorar_receta(dataObtained.getId(), -1, correo, false);

                    if (exito) {
                        Toast.makeText(getActivity(), "No me gusta", Toast.LENGTH_SHORT).show();

                        final Receta dataObtained = Access.getReceta(recetaSeleccionada.getId());

                        double media = (double) dataObtained.getMe_gusta() / (double) (dataObtained.getNo_me_gusta() + 1 + dataObtained.getMe_gusta());
                        if (dataObtained.getMe_gusta() == 0 || (dataObtained.getNo_me_gusta() + 1 + dataObtained.getMe_gusta()) == 0) {
                            valoracion.setText(" 0.0% de votos positivos");
                        } else {
                            valoracion.setText(media * 100 + "% de votos positivos");
                        }
                    } else {
                        Toast.makeText(getActivity(), "Error al valorar", Toast.LENGTH_SHORT).show();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Por favor, haz login para poder valorar", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Por favor, haz login para poder valorar", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    public static RecetaDialog newInstance(Receta recetaSeleccionada) {
        RecetaDialog f = new RecetaDialog(recetaSeleccionada);
        return f;
    }

    public String ingredientesReceta(List<Ingrediente> listaIngredientes, int cantidad) {
        String ingredientes = "";

        for (int i = 0; i < listaIngredientes.size(); i++) {

            ingredientes = ingredientes +
                    listaIngredientes.get(i).getCantidad() * cantidad + " " +
                    listaIngredientes.get(i).getUds() + " " +
                    listaIngredientes.get(i).getNombre() + "\n";
        }
        return ingredientes;

    }
}