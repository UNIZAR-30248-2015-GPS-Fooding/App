package com.GPS.app.fooding.fragments;

import android.app.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.GPS.app.fooding.MainActivity;
import com.GPS.app.fooding.connection.ClientInterface;
import com.GPS.app.fooding.R;
import com.GPS.app.fooding.RecetaAdapter;
import com.GPS.app.fooding.RecetaDialog;
import com.GPS.app.fooding.data.Receta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by coke on 15/10/2015.
 */
public class ListaRecetasFragment extends android.support.v4.app.Fragment {

    static Activity actividadPadre;
    static ListView lv;

    static ArrayList<String> nombresReceta = new ArrayList<>();
    static ArrayList<Integer> logosReceta = new ArrayList<>();
    static List<Receta> listaRecetas = null;

    String modo = "";

    public ListaRecetasFragment(String modo) {
        this.modo = modo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        actividadPadre = this.getActivity();
        View view = inflater.inflate(R.layout.fragment_lista_recetas, container, false);
        lv = (ListView) view.findViewById(R.id.listRec);

        String correo = "";
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

        FloatingActionButton floatingButton = (FloatingActionButton) view.findViewById(R.id.fab);
        if(correo!=null){
            floatingButton.setVisibility(View.VISIBLE);
        }else{
            floatingButton.setVisibility(View.GONE);
        }
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navegacion a crear receta
                android.support.v4.app.FragmentTransaction trans = getFragmentManager().beginTransaction();
                android.support.v4.app.Fragment fragment = new CrearRecetaFragment();
                trans.replace(R.id.mainFrame, fragment);
                trans.addToBackStack(null);
                trans.commit();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //String Slecteditem = itemname[+position];
                Receta recetaSeleccionada = listaRecetas.get(position);
                //Toast.makeText(actividadPadre.getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                showEditDialog(recetaSeleccionada, position);

            }
        });

        //ACTUALIZAMOS LA LISTA
        actualizarLista(null, modo);
        return view;
    }

    private void showEditDialog(Receta recetaSeleccionada, int position) {
        FragmentManager fm = getFragmentManager();
        RecetaDialog editNameDialog = new RecetaDialog(recetaSeleccionada);
        editNameDialog.show(fm, "fragment_edit_name");
    }

    public static void actualizarLista(List<Receta> listaReceta, String modo) {

        if (modo.equalsIgnoreCase("modoFavoritos")) {
            File myFile = new File(MainActivity.mPath + "/" + "ficheroUsuarios.txt");
            String correo = "";
            try {
                FileReader f = new FileReader(myFile);
                BufferedReader b = new BufferedReader(f);
                correo = b.readLine();
                b.close();
                System.out.println(correo);

            } catch (IOException e) {
                e.printStackTrace();
            }
            //TODO ESTO FUNCIONARÁ CUANDO ESTÉ IMPLEMENTADO EL METODO
            listaRecetas = ClientInterface.get_favoritos(correo);
        } else {
            if (listaReceta == null) {
                listaRecetas = ClientInterface.getRecetas();
            } else {
                listaRecetas = listaReceta;
            }
        }

        String[] listaNombres = new String[listaRecetas.size()];
        String[] listaTipo = new String[listaRecetas.size()];
        Integer[] listaIconos = new Integer[listaRecetas.size()];
        int[] listaIds = new int[listaRecetas.size()];
        Double[] puntos = new Double[listaRecetas.size()];

        //creamos la lista de nombres y de logos
        for (int i = 0; i < listaRecetas.size(); i++) {
            listaNombres[i] = listaRecetas.get(i).getNombre();
            listaTipo[i] = listaRecetas.get(i).getTipo();
            listaIds[i] = listaRecetas.get(i).getId();

            double media = (double) listaRecetas.get(i).getMe_gusta() / (double) (listaRecetas.get(i).getNo_me_gusta() + listaRecetas.get(i).getMe_gusta());
            if (listaRecetas.get(i).getMe_gusta() == 0 || (listaRecetas.get(i).getNo_me_gusta() + listaRecetas.get(i).getMe_gusta()) == 0) {
                puntos[i] = 0.0;
            } else {
                puntos[i] = media * 100;
            }

            //Transformamos los logos a iconos
            if (listaTipo[i].equalsIgnoreCase("pasta")) {
                listaIconos[i] = R.mipmap.pasta_logo;
            } else if (listaTipo[i].equalsIgnoreCase("postre")) {
                listaIconos[i] = R.mipmap.dessert_logo;
            } else if (listaTipo[i].equalsIgnoreCase("pescado")) {
                listaIconos[i] = R.mipmap.fish_logo;
            } else if (listaTipo[i].equalsIgnoreCase("carne")) {
                listaIconos[i] = R.mipmap.meat_logo;
            } else if (listaTipo[i].equalsIgnoreCase("verdura")) {
                listaIconos[i] = R.mipmap.vegetable_logo;
            } else {
                listaIconos[i] = R.mipmap.random_logo;
            }

        }

        RecetaAdapter adapter = new RecetaAdapter(actividadPadre, listaNombres, listaIds, listaIconos, puntos);
        lv.setAdapter(adapter);
    }

}
