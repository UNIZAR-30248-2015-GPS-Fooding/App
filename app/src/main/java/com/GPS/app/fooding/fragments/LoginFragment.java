package com.GPS.app.fooding.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.GPS.app.fooding.connection.ClientInterface;
import com.GPS.app.fooding.MainActivity;
import com.GPS.app.fooding.R;
import com.GPS.app.fooding.data.Usuario;

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
                             ViewGroup container, Bundle savedInstanceState) {

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

                doLogin(mEmailView.getText().toString(), mPasswordView.getText().toString());
                break;
        }
    }

    public void doLogin(String email, String passw) {

        boolean logueado = ClientInterface.login_usuario(email, passw, pruebaTest);

        if (logueado) {

            MainActivity.mail = email;
            // meter el usuario en fichero
            try {
                //File f = new File("ficheroUsuarios");
                File root = new File(MainActivity.mPath, "ficheroUsuarios.txt");
                FileWriter writer = new FileWriter(root);
                writer.append(email);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            u = new Usuario();
            u.setEmail(email);

            //Activamos booleano de registrado
            MainActivity.registrado = true;
            MainActivity.navigationView.getMenu().findItem(R.id.menu_logueados).setVisible(true);
            MainActivity.navigationView.getMenu().findItem(R.id.menu_iniciar_sesion).setVisible(false);
            MainActivity.navigationView.getMenu().findItem(R.id.menu_registrarse).setVisible(false);

            Toast.makeText(getActivity(), "Logeado correctamente", Toast.LENGTH_SHORT).show();
            android.support.v4.app.FragmentTransaction trans = getFragmentManager().beginTransaction();
            android.support.v4.app.Fragment fragment = new ListaRecetasFragment("");
            trans.replace(R.id.mainFrame, fragment);
            trans.addToBackStack(null);
            trans.commit();


        } else {

            Toast.makeText(getActivity(), "Email o passwd fallido", Toast.LENGTH_SHORT).show();
        }
    }

    public static void doLoginStatic(String email, String passw) {

        boolean logueado = ClientInterface.login_usuario(email, passw, false);

        if (logueado) {

            // meter el usuario en fichero
            try {
                //File f = new File("ficheroUsuarios");
                File root = new File(MainActivity.mPath, "ficheroUsuarios.txt");
                FileWriter writer = new FileWriter(root);
                writer.append(email);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            u = new Usuario();
            u.setEmail(email);

            //Activamos booleano de registrado
            MainActivity.registrado = true;
            MainActivity.mail = email;


        } else {
        }
    }

}
