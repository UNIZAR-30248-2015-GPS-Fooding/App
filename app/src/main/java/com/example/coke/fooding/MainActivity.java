package com.example.coke.fooding;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.MapsInitializer;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new ListaRecetasFragment();
        fragmentTransaction.replace(R.id.mainFrame, fragment);
        fragmentTransaction.commit();




    }



    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
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
            Toast.makeText(MainActivity.this, "Funcion no disponible", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (id == R.id.menu_inicio) {
            Toast.makeText(MainActivity.this, "Funcion no disponible", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.menu_registrarse) {
            Fragment fragment = new RegistrarseFragment();
            fragmentTransaction.replace(R.id.mainFrame, fragment);
            fragmentTransaction.addToBackStack(null);
            //Toast.makeText(MainActivity.this, "Funcion no disponible", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.menu_iniciar_sesion) {
          Fragment fragment = new LoginFragment();
          fragmentTransaction.replace(R.id.mainFrame, fragment);
            fragmentTransaction.addToBackStack(null);
      }
        else if (id == R.id.menu_lista_recetas) {
            Fragment fragment = new ListaRecetasFragment();
            fragmentTransaction.replace(R.id.mainFrame, fragment);
            fragmentTransaction.addToBackStack(null);


        }
        else if (id == R.id.menu_lista_usuarios) {
            Fragment fragment = new ListaUsuariosFragment();
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
}
