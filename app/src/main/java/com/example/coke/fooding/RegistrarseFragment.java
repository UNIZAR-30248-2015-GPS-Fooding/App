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
public class RegistrarseFragment extends Fragment implements View.OnClickListener {

    View view;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mNombreView;

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
        //do what you want to do when button is clicked
        switch (v.getId()) {
            case R.id.RegistrarseButton:
                //switchFragment(HelpFragment.TAG);
                Toast.makeText(getActivity(), "Email: " + mEmailView.getText() + " Pass: " + mPasswordView.getText() + " Nombre: " + mNombreView.getText(), Toast.LENGTH_SHORT).show();
                break;
        }
    }



}
