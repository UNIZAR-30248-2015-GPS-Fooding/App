package com.example.coke.fooding;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog.Builder;

import com.example.coke.fooding.data.Receta;
import com.google.android.gms.maps.MapsInitializer;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Atributos para guardar filtros
    public static String filtroNombre = "";
    public static String filtroIngredientes = "";
    public static int ordenacionSpinner = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Permitir conexion con servidor
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        imageView.setImageResource(R.mipmap.watermark);


        //Iniciamos la Actividad con el fragment ListaRecetas
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        android.support.v4.app.Fragment fragment = new ListaRecetasFragment();
        fragmentTransaction.replace(R.id.mainFrame, fragment);
        fragmentTransaction.commit();

    }


    /*
     * Volver a atras a la anterior Entrada de la pila de entradas
     */
    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_item_buscar) {
            showDialog(1);
            //Toast.makeText(MainActivity.this, "Funcion no disponible", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (id == R.id.menu_inicio) {
            Toast.makeText(MainActivity.this, "Funcion no disponible", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.menu_registrarse) {
            android.support.v4.app.Fragment fragment = new RegistrarseFragment();
            fragmentTransaction.replace(R.id.mainFrame, fragment);
            fragmentTransaction.addToBackStack(null);
            //Toast.makeText(MainActivity.this, "Funcion no disponible", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.menu_iniciar_sesion) {
            android.support.v4.app.Fragment fragment = new LoginFragment();
          fragmentTransaction.replace(R.id.mainFrame, fragment);
            fragmentTransaction.addToBackStack(null);
      }
        else if (id == R.id.menu_lista_recetas) {
            android.support.v4.app.Fragment fragment = new ListaRecetasFragment();
            fragmentTransaction.replace(R.id.mainFrame, fragment);
            fragmentTransaction.addToBackStack(null);


        }
        else if (id == R.id.menu_lista_usuarios) {
            android.support.v4.app.Fragment fragment = new ListaUsuariosFragment();
            fragmentTransaction.replace(R.id.mainFrame, fragment);
            fragmentTransaction.addToBackStack(null);
            //Toast.makeText(MainActivity.this, "Funcion no disponible", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.menu_supermercados) {
            Intent Intent = new Intent(this, MapsActivity.class);
            startActivity(Intent);

            //Fragment fragment = new MapsActivity();
            //fragmentTransaction.replace(R.id.mainFrame, fragment);

        }
        else if (id == R.id.menu_favoritos) {
            Toast.makeText(MainActivity.this, "Funcion no disponible", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.menu_supermercados){
            Toast.makeText(MainActivity.this, "Funcion no disponible", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.menu_ingredientes) {
            Toast.makeText(MainActivity.this, "Funcion no disponible", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.menu_cerrar_sesion) {
            Toast.makeText(MainActivity.this, "Funcion no disponible", Toast.LENGTH_SHORT).show();
        }


        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (id) {
            case 1:
                return dialogBuscar();
        }
        dialog = builder.show();
        return dialog;
    }

    /*
     * ShowDialog correspondiente a la opcion BUSCAR del ActionBar
     */
    private Dialog dialogBuscar() {

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.filtros_busqueda, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        //Ponemos layour filtros_busqueda.xml a alertdialog builder
        alertDialogBuilder.setView(promptsView);

        //Obtenemos elementos de layout
        final EditText userInputNom = (EditText) promptsView
                .findViewById(R.id.textoNom);

        List<Receta> recetasNombre = ClientInterface.getRecetasFiltros(userInputNom.getText().toString(), null, null);



        final EditText userInputIng = (EditText) promptsView
                .findViewById(R.id.TextoIng);
        final Spinner userInputSpinner = (Spinner) promptsView
                .findViewById(R.id.dialog_spinner);
        String[] tiposOrdenación = {"Por Nombre", "Por Tipo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item, tiposOrdenación);
        userInputSpinner.setAdapter(adapter);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Filtrar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                String valueNombre = userInputNom.getText().toString();
                                filtroNombre = valueNombre;
                                String valueIngrediente = userInputIng.getText().toString();
                                filtroIngredientes = valueIngrediente;
                                ordenacionSpinner = userInputSpinner.getSelectedItemPosition();
                                //fillData();
                                return;
                            }
                        })
                .setNegativeButton("Borrar Filtros",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                filtroNombre = "";
                                userInputNom.setText("");
                                filtroIngredientes = "";
                                userInputIng.setText("");
                                ordenacionSpinner = 0;
                                userInputSpinner.setSelection(0);
                                return;
                            }
                        });

        return alertDialogBuilder.create();

    }




}
