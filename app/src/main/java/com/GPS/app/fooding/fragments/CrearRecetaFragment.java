package com.GPS.app.fooding.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.GPS.app.fooding.connection.ClientInterface;
import com.GPS.app.fooding.MainActivity;
import com.GPS.app.fooding.R;
import com.GPS.app.fooding.data.Ingrediente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Fooding on 15/10/2015.
 */
public class CrearRecetaFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    View view;
    private EditText nombreReceta;
    private EditText elaboracionReceta;

    //tipos de una receta
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

                List<Ingrediente> listIngr = new LinkedList<>();
                Ingrediente i = new Ingrediente();
                if (!tipoInputSpinnerIngredientes.getSelectedItem().toString().equalsIgnoreCase("Seleccione ingrediente")){
                    i.setCantidad(Integer.parseInt(cant1.getText().toString()));
                    i.setNombre(tipoInputSpinnerIngredientes.getSelectedItem().toString());
                    i.setUds(uds1.getText().toString());
                    listIngr.add(i);
                }
                i = new Ingrediente();
                if(!tipoInputSpinnerIngredientes2.getSelectedItem().toString().equalsIgnoreCase("Seleccione ingrediente")){
                    i.setCantidad(Integer.parseInt(cant2.getText().toString()));
                    i.setNombre(tipoInputSpinnerIngredientes2.getSelectedItem().toString());
                    i.setUds(uds2.getText().toString());
                    listIngr.add(i);
                }
                i = new Ingrediente();
                if(!tipoInputSpinnerIngredientes3.getSelectedItem().toString().equalsIgnoreCase("Seleccione ingrediente")){
                    i.setCantidad(Integer.parseInt(cant3.getText().toString()));
                    i.setNombre(tipoInputSpinnerIngredientes3.getSelectedItem().toString());
                    i.setUds(uds3.getText().toString());
                    listIngr.add(i);
                }

                String tipo = MainActivity.saberTipo(tipoInputSpinner.getSelectedItemPosition());
                String nombre = nombreReceta.getText().toString();
                String elaboracion = elaboracionReceta.getText().toString();

                String correo = "";

                boolean creado = false;

                File myFile = new File(MainActivity.mPath + "/" + "ficheroUsuarios.txt");

                try {
                    FileReader f = new FileReader(myFile);
                    BufferedReader b = new BufferedReader(f);
                    correo = b.readLine();
                    b.close();
                    System.out.println(correo);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                if ((tipo!=null) && (!nombre.equalsIgnoreCase("")) && (!elaboracion.equalsIgnoreCase("")) && (listIngr!=null)){
                    creado = ClientInterface.crear_receta(correo,nombre,tipo,elaboracion ,listIngr, test);
                }
                else{
                    Toast.makeText(getActivity(), "ERROR: rellene todos los campos",Toast.LENGTH_SHORT ).show();
                }

                if(creado){
                    Toast.makeText(getActivity(), "Receta creada correctamente",Toast.LENGTH_SHORT ).show();
                    //ir a la lista de recetas
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction trans = getFragmentManager().beginTransaction();
                    android.support.v4.app.Fragment fragment = new ListaRecetasFragment("");
                    trans.replace(R.id.mainFrame, fragment);
                    trans.remove(this);
                    trans.commit();
                    manager.popBackStack();

                }else{
                    Toast.makeText(getActivity(), "Receta no creada. Hubo un error al procesar la petición",Toast.LENGTH_SHORT).show();
                    //ir a la lista de recetas
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction trans = getFragmentManager().beginTransaction();
                    android.support.v4.app.Fragment fragment = new ListaRecetasFragment("");
                    trans.replace(R.id.mainFrame, fragment);
                    trans.remove(this);
                    trans.commit();
                    manager.popBackStack();
                }

                break;
        }
    }
}
