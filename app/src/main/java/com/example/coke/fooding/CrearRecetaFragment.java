package com.example.coke.fooding;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by coke on 15/10/2015.
 */
public class CrearRecetaFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    View view;
    private EditText ingredientesReceta1;
    private EditText ingredientesReceta2;
    private EditText ingredientesReceta3;
    private EditText nombreReceta;
    private EditText elaboracionReceta;

    private Spinner tipoReceta;
    private ArrayAdapter<String> adaptadorType;
    public static List<String> tipos = new LinkedList<String>();
    final Spinner tipoInputSpinner=null;

    public static boolean test = false;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {


        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.crear_receta, container, false);

        Button confirmButton = (Button) view.findViewById(R.id.CrearRecetaButton);
        confirmButton.setOnClickListener(this);

        ingredientesReceta1 = (EditText) view.findViewById(R.id.ingredientes);
        elaboracionReceta = (EditText) view.findViewById(R.id.elaboracion);
        nombreReceta = (EditText) view.findViewById(R.id.nombreReceta);
        //tipoReceta = (EditText) view.findViewById(R.id.tipoReceta);

        tipos.add("Seleccione tipo");
        tipos.addAll(ClientInterface.getTipos());

        final Spinner tipoInputSpinner = (Spinner) view.findViewById(R.id.spinnerTipoReceta);
        String[] tiposRecetas = tipos.toArray(new String[tipos.size()]);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String> (this.getActivity(), android.R.layout.simple_spinner_dropdown_item, tiposRecetas);
        tipoInputSpinner.setAdapter(adapter2);




        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.CrearRecetaButton:

                String valueTipo = MainActivity.saberTipo(tipoInputSpinner.getSelectedItemPosition());
                List<Ingrediente> listaIngredientes = new LinkedList<Ingrediente>();
              /**  if(ingredientesReceta1.getVisibility() == View.VISIBLE){

                    //listaIngredientes.add(ingredientesReceta1.getText());
                }*/
               // boolean creado = ClientInterface.crear_receta(nombreReceta, valueTipo, elaboracionReceta, )
               // boolean creado = ClientInterface.crear_usuario(mEmailView.getText().toString(), mNombreView.getText().toString(), mPasswordView.getText().toString(), test);
              //  Toast.makeText(getActivity(), "Email: " + mEmailView.getText() + " Pass: " + mPasswordView.getText() + " Nombre: " + mNombreView.getText(), Toast.LENGTH_SHORT).show();
              /**  if(creado){
                    Toast.makeText(getActivity(), "Se ha enviado un e-mail de verificación al correo introducido",Toast.LENGTH_SHORT ).show();
                }else{
                    Toast.makeText(getActivity(), "Usuario no registrado",Toast.LENGTH_SHORT).show();
                }*/
                break;
            case R.id.angry_btn:
                final Button botonAnadeIngrediente = (Button) view.findViewById(R.id.angry_btn);
                if (ingredientesReceta1.getVisibility() == View.GONE){
                    ingredientesReceta1.setVisibility(View.VISIBLE);
                }
                else if (ingredientesReceta2.getVisibility() == View.GONE){
                    ingredientesReceta2.setVisibility(View.VISIBLE);
                }
                else{
                    ingredientesReceta3.setVisibility(View.VISIBLE);
                    botonAnadeIngrediente.setText("Maximo numero de Ingredientes");
                }


        }
    }






}
