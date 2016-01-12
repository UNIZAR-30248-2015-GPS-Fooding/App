package com.GPS.app.fooding;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.GPS.app.fooding.connection.ClientInterface;
import com.GPS.app.fooding.data.Receta;
import com.GPS.app.fooding.data.Usuario;
import com.GPS.app.fooding.fragments.CrearRecetaFragment;
import com.GPS.app.fooding.fragments.CuentaFragment;
import com.GPS.app.fooding.fragments.ErrorFragment;
import com.GPS.app.fooding.fragments.ListaRecetasFragment;
import com.GPS.app.fooding.fragments.ListaUsuariosFragment;
import com.GPS.app.fooding.fragments.LoginFragment;
import com.GPS.app.fooding.fragments.RegistrarseFragment;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Atributos para guardar tipos
    public static List<String> tipos = new LinkedList<String>();
    public static List<String> ingredientes = new LinkedList<String>();

    //Atributo del dialogo de busqueda
    private LinearLayout mLayout;

    public static File mPath = new File(Environment.getExternalStorageDirectory() + "/Fooding");
    public static boolean registrado;

    //Menu lateral
    public static NavigationView navigationView;

    //Usuario registrado
    public static String mail;

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    protected void onResume(){
        super.onResume();
        //leer el fichero si hay algo escrito ese nombre de usuario

        //Si estamos logeados, msotramos menu oculto
        navigationView.getMenu().findItem(R.id.menu_logueados).setVisible(registrado);
        navigationView.getMenu().findItem(R.id.menu_iniciar_sesion).setVisible(!registrado);
        navigationView.getMenu().findItem(R.id.menu_registrarse).setVisible(!registrado);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registrado = false;
        if (!mPath.exists()) {
            mPath.mkdirs();
        }

        //leer el fichero de usuarios
        //mira si hay usuarios
        //y crea una variable para el usuario

        //Permitir conexion con servidor
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        imageView.setImageResource(R.mipmap.watermark);

        //Si estamos logeados, msotramos menu oculto
        navigationView.getMenu().findItem(R.id.menu_logueados).setVisible(registrado);
        navigationView.getMenu().findItem(R.id.menu_iniciar_sesion).setVisible(!registrado);
        navigationView.getMenu().findItem(R.id.menu_registrarse).setVisible(!registrado);

        tipos.add("Ninguno");
        List<String> lista = ClientInterface.getTipos();
        if (lista ==null){

            //Mostramos pantalla de error
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            android.support.v4.app.Fragment fragment = new ErrorFragment();
            fragmentTransaction.replace(R.id.mainFrame, fragment);
            fragmentTransaction.commit();
            //System.exit(0);
        }
        else {
            tipos.addAll(lista);

            ingredientes.add("Ninguno");
            ingredientes.addAll(ClientInterface.getIngredientes());

            //Iniciamos la Actividad con el fragment ListaRecetas
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            android.support.v4.app.Fragment fragment = new ListaRecetasFragment("");
            fragmentTransaction.replace(R.id.mainFrame, fragment);
            fragmentTransaction.commit();

        }

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
        if (id == R.id.menu_item_buscar_usuario) {
            showDialog(2);
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

        File root = new File(mPath, "ficheroUsuarios.txt");

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
            if (root.exists() && root.isFile() && registrado){
                Toast.makeText(MainActivity.this, "ERROR. Primero debe cerrar sesion.", Toast.LENGTH_SHORT).show();
            }
            else{
                android.support.v4.app.Fragment fragment = new LoginFragment();
                fragmentTransaction.replace(R.id.mainFrame, fragment);
                fragmentTransaction.addToBackStack(null);
            }

      }
        else if (id == R.id.menu_lista_recetas) {
            android.support.v4.app.Fragment fragment = new ListaRecetasFragment("");
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
            Toast.makeText(MainActivity.this, "Funcion no disponible", Toast.LENGTH_SHORT).show();

        }
        else if(id == R.id.crear_receta){
            android.support.v4.app.Fragment fragment = new CrearRecetaFragment();
            fragmentTransaction.replace(R.id.mainFrame, fragment);
            fragmentTransaction.addToBackStack(null);
        }
        else if (id == R.id.menu_favoritos) {
            android.support.v4.app.Fragment fragment = new ListaRecetasFragment("modoFavoritos");
            fragmentTransaction.replace(R.id.mainFrame, fragment);
            fragmentTransaction.addToBackStack(null);
        }
        else if (id == R.id.menu_supermercados){
            Toast.makeText(MainActivity.this, "Funcion no disponible", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.menu_ingredientes) {
            Toast.makeText(MainActivity.this, "Funcion no disponible", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.menu_cerrar_sesion) {
            if (root.exists() && root.isFile() && registrado){
                root.delete();
                registrado = false;
                navigationView.getMenu().findItem(R.id.menu_logueados).setVisible(false);
                navigationView.getMenu().findItem(R.id.menu_iniciar_sesion).setVisible(true);
                navigationView.getMenu().findItem(R.id.menu_registrarse).setVisible(true);
                Toast.makeText(MainActivity.this, "Sesion cerrada correctamente", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this, "ERROR. Primero debe hacer login", Toast.LENGTH_SHORT).show();
            }

        }
        else if (id == R.id.menu_usuario){
            if (root.exists() && root.isFile() && registrado){

                //TODO Cargar el usuario del fichero y usarlo para llamar a CuentaFragment()
                //Ahora se crea un usuario de prueba
                Usuario u = new Usuario();
                u.setEmail("prueba@mail");
                u.setNombre("nombre");
                u.setScore(99);

                android.support.v4.app.Fragment fragment = new CuentaFragment(u);
                fragmentTransaction.replace(R.id.mainFrame, fragment);
                fragmentTransaction.addToBackStack(null);
            }
            else{
                Toast.makeText(MainActivity.this, "ERROR. Primero debe cerrar sesion.", Toast.LENGTH_SHORT).show();
            }
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
            case 2:
                return dialogBuscarUsuario();
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

        final Spinner userInputIng = (Spinner) promptsView
                .findViewById(R.id.TextoIng);
        final Spinner userInputIng2 = (Spinner) promptsView
                .findViewById(R.id.TextoIng2);
        final Spinner userInputIng3 = (Spinner) promptsView
                .findViewById(R.id.TextoIng3);
        String[] ingrecientesRecetas = ingredientes.toArray(new String[ingredientes.size()]);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item, ingrecientesRecetas);
        userInputIng.setAdapter(adapter3);
        userInputIng2.setAdapter(adapter3);
        userInputIng3.setAdapter(adapter3);

        final Spinner tipoInputSpinner = (Spinner) promptsView.findViewById(R.id.dialog_spinner2);
        String[] tiposRecetas = tipos.toArray(new String[tipos.size()]);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item, tiposRecetas);
        tipoInputSpinner.setAdapter(adapter2);

        final Spinner userInputSpinner = (Spinner) promptsView
                .findViewById(R.id.dialog_spinner);
        String[] tiposOrdenación = {"Ninguno","Por Nombre", "Por Tipo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item, tiposOrdenación);
        userInputSpinner.setAdapter(adapter);

        final Button botonAnadeIngrediente = (Button) promptsView.findViewById(R.id.angry_btn);
        botonAnadeIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userInputIng.getVisibility() == View.GONE){
                    userInputIng.setVisibility(View.VISIBLE);
                }
                else if (userInputIng2.getVisibility() == View.GONE){
                    userInputIng2.setVisibility(View.VISIBLE);
                }
                else{
                    userInputIng3.setVisibility(View.VISIBLE);
                    botonAnadeIngrediente.setText("Maximo numero de Ingredientes");
                }
            }
        });

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Filtrar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                String valueNombre = userInputNom.getText().toString();
                                List<String> listaIngredientes = new LinkedList<String>();
                                if (!userInputIng.getSelectedItem().toString().equalsIgnoreCase("Ninguno")){
                                    listaIngredientes.add(userInputIng.getSelectedItem().toString());
                                }
                                if (!userInputIng2.getSelectedItem().toString().equalsIgnoreCase("Ninguno")){
                                    listaIngredientes.add(userInputIng2.getSelectedItem().toString());
                                }
                                if (!userInputIng3.getSelectedItem().toString().equalsIgnoreCase("Ninguno")){
                                    listaIngredientes.add(userInputIng3.getSelectedItem().toString());
                                }

                                String valueTipo = saberTipo(tipoInputSpinner.getSelectedItemPosition());
                                List<Receta> recetasNombre = ClientInterface.getRecetasFiltros(valueNombre, valueTipo,listaIngredientes);
                                recetasNombre = ordenaRecetas(recetasNombre,userInputSpinner.getSelectedItemPosition());
                                ListaRecetasFragment.actualizarLista(recetasNombre,"");
                                return;
                            }
                        })
                .setNegativeButton("Borrar Filtros",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                userInputNom.setText("");
                                userInputIng.setSelection(0);
                                userInputSpinner.setSelection(0);
                                tipoInputSpinner.setSelection(0);
                                userInputIng.setVisibility(View.GONE);
                                userInputIng.setSelection(0);
                                userInputIng2.setVisibility(View.GONE);
                                userInputIng2.setSelection(0);
                                userInputIng3.setVisibility(View.GONE);
                                userInputIng3.setSelection(0);
                                botonAnadeIngrediente.setText("Añadir Ingrediente");
                                ListaRecetasFragment.actualizarLista(null,"");
                                return;
                            }
                        })
                .setNeutralButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                return;
                            }
                        });

        return alertDialogBuilder.create();

    }

    public static String saberTipo(int i){
        String tipo = "";
        switch(i){
            case 0:
                tipo = null;
                break;
            case 1:
                tipo = "Pasta";
                break;
            case 2:
                tipo = "Carne";
                break;
            case 3:
                tipo = "Pescado";
                break;
            case 4:
                tipo = "Verdura";
                break;
            default:
                tipo = "Postre";
                break;
        }
        return tipo;
    }


    /*
 * ShowDialog correspondiente a la opcion BUSCAR del ActionBar
 */
    private Dialog dialogBuscarUsuario() {

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.filtros_busqueda_usuario, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptsView);

        //Obtenemos elementos de layout
        final EditText userInputNom = (EditText) promptsView
                .findViewById(R.id.textoNom_user);
        final Spinner userInputSpinner = (Spinner) promptsView
                .findViewById(R.id.dialog_spinner_user);
        String[] tiposOrdenación = {"Ninguno","Por Nombre", "Por Puntos"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item, tiposOrdenación);
        userInputSpinner.setAdapter(adapter);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Filtrar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                String valueNombre = userInputNom.getText().toString();
                                List<Usuario> listaUsuarios = ClientInterface.get_usuarios(valueNombre, false);
                                listaUsuarios = ordenaUsuarios(listaUsuarios,userInputSpinner.getSelectedItemPosition());
                                ListaUsuariosFragment.actualizarLista(listaUsuarios);
                                return;
                            }
                        })
                .setNegativeButton("Borrar Filtros",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                userInputNom.setText("");
                                userInputSpinner.setSelection(0);
                                ListaUsuariosFragment.actualizarLista(null);
                                return;
                            }
                        })
                .setNeutralButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                return;
                            }
                        });

        return alertDialogBuilder.create();

    }


    public List<Usuario> ordenaUsuarios(List<Usuario> lista, int tipo){

        //Ordenar por nombre
        if(tipo == 1){
            int i, j;
            Usuario aux;
            for(i=0;i<lista.size()-1;i++){
                for(j=0;j<lista.size()-i-1;j++) {
                    if (lista.get(j + 1).getNombre().toLowerCase().compareTo(lista.get(j).getNombre().toLowerCase()) < 0) {
                        aux = lista.get(j + 1);
                        lista.set(j+1,lista.get(j));
                        lista.set(j,aux);
                    }
                }
            }
        }

        //Ordenar por puntos
        if(tipo == 2){
            int i, j;
            Usuario aux;
            for(i=0;i<lista.size()-1;i++){
                for(j=0;j<lista.size()-i-1;j++) {
                    if (lista.get(j + 1).getScore() >= lista.get(j).getScore()) {
                        aux = lista.get(j + 1);
                        lista.set(j+1,lista.get(j));
                        lista.set(j,aux);
                    }
                }
            }
        }

        return lista;

    }

    public List<Receta> ordenaRecetas(List<Receta> lista, int tipo){

        //Ordenar por nombre
        if(tipo == 1){
            int i, j;
            Receta aux;
            for(i=0;i<lista.size()-1;i++){
                for(j=0;j<lista.size()-i-1;j++) {
                    if (lista.get(j + 1).getNombre().toLowerCase().compareTo(lista.get(j).getNombre().toLowerCase()) < 0) {
                        aux = lista.get(j + 1);
                        lista.set(j+1,lista.get(j));
                        lista.set(j,aux);
                    }
                }
            }
        }

        //Ordenar por tipo
        if(tipo == 2){
            int i, j;
            Receta aux;
            for(i=0;i<lista.size()-1;i++){
                for(j=0;j<lista.size()-i-1;j++) {
                    if (lista.get(j + 1).getTipo().toLowerCase().compareTo(lista.get(j).getTipo().toLowerCase()) < 0) {
                        aux = lista.get(j + 1);
                        lista.set(j+1,lista.get(j));
                        lista.set(j,aux);
                    }
                }
            }
        }

        return lista;

    }



}
