package com.example.coke.fooding;

import android.app.Fragment;
import android.content.ClipData;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coke.fooding.data.Usuario;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by coke on 15/10/2015.
 */

public class LoginFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    View view;

    public static Usuario u;

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    public static boolean pruebaTest = false;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.login_fragment, container, false);

        Button confirmButton = (Button) view.findViewById(R.id.email_sign_in_button);
        confirmButton.setOnClickListener(this);

        mEmailView = (AutoCompleteTextView) view.findViewById(R.id.email);
        mPasswordView = (EditText) view.findViewById(R.id.password);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.email_sign_in_button:
                //switchFragment(HelpFragment.TAG);

               // Toast.makeText(getActivity(), "Email: " + mEmailView.getText() + " Pass: " + mPasswordView.getText(), Toast.LENGTH_SHORT).show();

                boolean logueado = ClientInterface.login_usuario(mEmailView.getText().toString(), mPasswordView.getText().toString(), pruebaTest);

                if(logueado){

                    try {
                        //File f = new File("ficheroUsuarios");
                        File root = new File(MainActivity.mPath, "ficheroUsuarios.txt");
                        FileWriter writer = new FileWriter(root);
                        writer.append(mEmailView.getText().toString());
                        writer.flush();
                        writer.close();
                        MainActivity.registrado = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    u = new Usuario();
                    u.setEmail(mEmailView.getText().toString());

                    //Activamos booleano de registrado
                    MainActivity.registrado = true;
                    MainActivity.navigationView.getMenu().findItem(R.id.menu_logueados).setVisible(true);


                    Toast.makeText(getActivity(), "Logeado correctamente", Toast.LENGTH_SHORT).show();
                    // meter el usuario en fichero
                }
                else{
                    Toast.makeText(getActivity(), "Email o passwd fallido", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }





}
