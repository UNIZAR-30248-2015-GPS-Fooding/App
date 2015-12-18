package com.example.coke.fooding;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.coke.fooding.data.Ingrediente;
import com.example.coke.fooding.data.Receta;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by coke on 15/10/2015.
 */
public class CrearRecetaFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    View view;
    private EditText nombreReceta;
    private EditText elaboracionReceta;

    private Spinner tipoReceta;
    public static List<String> tipos = new LinkedList<String>();
    private Spinner tipoInputSpinner;

    public static List<String> tiposIngredientes = new LinkedList<String>();

    private LinearLayout layout1;
    private LinearLayout layout2;
    private LinearLayout layout3;

    private EditText cant1;
    private EditText cant2;
    private EditText cant3;

    private EditText uds1;
    private EditText uds2;
    private EditText uds3;

    private Spinner tipoInputSpinnerIngredientes;
  //  private EditText nombreIngrediente1;
    private Spinner tipoInputSpinnerIngredientes2;
    private Spinner tipoInputSpinnerIngredientes3;





    public static boolean test = false;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.crear_receta, container, false);

        Button confirmButton = (Button) view.findViewById(R.id.CrearRecetaButton);
        confirmButton.setOnClickListener(this);

        //Añadir nombre de la receta
        nombreReceta = (EditText) view.findViewById(R.id.nombreReceta);


        // Añadir tipo de la receta
        tipos.add("Seleccione tipo");
        tipos.addAll(ClientInterface.getTipos());

        tipoInputSpinner = (Spinner) view.findViewById(R.id.spinnerTipoReceta);
        String[] tiposRecetas = tipos.toArray(new String[tipos.size()]);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String> (this.getActivity(), android.R.layout.simple_spinner_dropdown_item, tiposRecetas);
        tipoInputSpinner.setAdapter(adapter2);

        //Añadir ingredientes
        tiposIngredientes.add("Seleccione ingrediente");
        tiposIngredientes.addAll(ClientInterface.getIngredientes());

        layout1 = (LinearLayout) view.findViewById(R.id.layout);
        layout2 = (LinearLayout) view.findViewById(R.id.layout2);
        layout3 = (LinearLayout) view.findViewById(R.id.layout3);

        //elegir un ingrediente de la bd
        tipoInputSpinnerIngredientes = (Spinner) view.findViewById(R.id.TextoIng);
        tipoInputSpinnerIngredientes2 = (Spinner) view.findViewById(R.id.TextoIng2);
        tipoInputSpinnerIngredientes3 = (Spinner) view.findViewById(R.id.TextoIng3);

        //cantidad de ingrediente
        cant1 = (EditText) view.findViewById(R.id.cant);
        cant2 = (EditText) view.findViewById(R.id.cant2);
        cant3 = (EditText) view.findViewById(R.id.cant3);

        //unidades de ingrediente
        uds1 = (EditText) view.findViewById(R.id.uds);
        uds2 = (EditText) view.findViewById(R.id.uds2);
        uds3 = (EditText) view.findViewById(R.id.uds3);

        String[] tiposIngredientes2 = tiposIngredientes.toArray(new String[tipos.size()]);
        ArrayAdapter<String> adapterIngre = new ArrayAdapter<String> (this.getActivity(), android.R.layout.simple_spinner_dropdown_item, tiposIngredientes2);
        tipoInputSpinnerIngredientes.setAdapter(adapterIngre);
        tipoInputSpinnerIngredientes2.setAdapter(adapterIngre);
        tipoInputSpinnerIngredientes3.setAdapter(adapterIngre);


        final Button botonAnadeIngrediente = (Button) view.findViewById(R.id.mas_ingredientes);
        botonAnadeIngrediente.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (layout1.getVisibility() == View.GONE) {
                    layout1.setVisibility(View.VISIBLE);
                } else if (layout2.getVisibility() == View.GONE) {
                    layout2.setVisibility(View.VISIBLE);
                } else {
                    layout3.setVisibility(View.VISIBLE);
                    botonAnadeIngrediente.setText("Maximo numero de Ingredientes");
                }
            }
        });

        //Elaboracion de la receta
        elaboracionReceta = (EditText) view.findViewById(R.id.elaboracion);

        return view;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.CrearRecetaButton:
                String tipo = MainActivity.saberTipo(tipoInputSpinner.getSelectedItemPosition());
                List<Ingrediente> listIngr = new LinkedList<>();
                Ingrediente i = new Ingrediente();
                i.setCantidad(Integer.parseInt(cant1.getText().toString()));
                i.setNombre(tipoInputSpinnerIngredientes.getSelectedItem().toString());
                i.setUds(uds1.getText().toString());
                listIngr.add(i);
                String nombre = nombreReceta.getText().toString();
                String elaboracion = elaboracionReceta.getText().toString();

                boolean creado = ClientInterface.crear_receta(nombre,tipo,elaboracion ,listIngr, test);
                if(creado){
                    Toast.makeText(getActivity(), "Receta creada correctamente",Toast.LENGTH_SHORT ).show();
                }else{
                    Toast.makeText(getActivity(), "Receta no creada",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }






}
