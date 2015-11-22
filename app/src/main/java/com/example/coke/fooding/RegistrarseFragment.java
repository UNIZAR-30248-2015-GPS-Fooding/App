package com.example.coke.fooding;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by coke on 15/10/2015.
 */
public class RegistrarseFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    View view;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mNombreView;
    public static boolean test = false;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {


        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.registrarse_fragment, container, false);

        Button confirmButton = (Button) view.findViewById(R.id.RegistrarseButton);
        confirmButton.setOnClickListener(this);

        mEmailView = (AutoCompleteTextView) view.findViewById(R.id.email);
        mPasswordView = (EditText) view.findViewById(R.id.password);
        mNombreView = (EditText) view.findViewById(R.id.nombre);

        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.RegistrarseButton:
                boolean creado = ClientInterface.crear_usuario(mEmailView.getText().toString(),mNombreView.getText().toString(), mPasswordView.getText().toString(),test);
              //  Toast.makeText(getActivity(), "Email: " + mEmailView.getText() + " Pass: " + mPasswordView.getText() + " Nombre: " + mNombreView.getText(), Toast.LENGTH_SHORT).show();
                if(creado){
                    Toast.makeText(getActivity(), "Usuario creado correctamente",Toast.LENGTH_SHORT ).show();
                }else{
                    Toast.makeText(getActivity(), "Usuario no registrado",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }



}
