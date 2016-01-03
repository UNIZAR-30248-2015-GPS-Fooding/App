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

import com.GPS.app.fooding.connection.ClientInterface;
import com.GPS.app.fooding.R;
import com.GPS.app.fooding.RecetaAdapter;
import com.GPS.app.fooding.RecetaDialog;
import com.GPS.app.fooding.data.Receta;

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

    public ListaRecetasFragment (){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        actividadPadre = this.getActivity();
        View view = inflater.inflate(R.layout.fragment_lista_recetas, container, false);
        lv = (ListView) view.findViewById(R.id.listRec);

        FloatingActionButton floatingButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(actividadPadre.getApplicationContext(), "FAB CLICKED: agnadida Receta", Toast.LENGTH_LONG).show();
                nombresReceta.add("Nueva Receta");
                logosReceta.add(R.mipmap.fish_logo);
                actualizarLista(null);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                //String Slecteditem = itemname[+position];
                Receta recetaSeleccionada = listaRecetas.get(position);
                //Toast.makeText(actividadPadre.getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                showEditDialog(recetaSeleccionada, position);

            }
        });

        //ACTUALIZAMOS LA LISTA
        actualizarLista(null);
        return view;
    }

    private void showEditDialog(Receta recetaSeleccionada, int position) {
        FragmentManager fm = getFragmentManager();
        RecetaDialog editNameDialog = new RecetaDialog(recetaSeleccionada);
        editNameDialog.show(fm, "fragment_edit_name");
    }

    public static void actualizarLista(List<Receta> listaReceta){
        if(listaReceta== null){
            listaRecetas = ClientInterface.getRecetas();
        }else{
            listaRecetas = listaReceta;
        }
        String[] listaNombres = new String[listaRecetas.size()];
        String[] listaTipo = new String[listaRecetas.size()];
        Integer[] listaIconos = new Integer[listaRecetas.size()];
        Double[] puntos = new Double[listaRecetas.size()];

        //creamos la lista de nombres y de logos
        for(int i=0;i<listaRecetas.size();i++){
            listaNombres[i] = listaRecetas.get(i).getNombre();
            listaTipo[i] = listaRecetas.get(i).getTipo();

            double media = (double) listaRecetas.get(i).getMe_gusta() / (double) (listaRecetas.get(i).getNo_me_gusta() + listaRecetas.get(i).getMe_gusta()) ;
            if(listaRecetas.get(i).getMe_gusta() == 0 || (listaRecetas.get(i).getNo_me_gusta() + listaRecetas.get(i).getMe_gusta()) == 0){
                puntos[i] = 0.0;
            }
            else{
                puntos[i] = media;
            }

            //Transformamos los logos a iconos
            if (listaTipo[i].equalsIgnoreCase("pasta")){
                listaIconos[i] = R.mipmap.pasta_logo;
            }
            else if (listaTipo[i].equalsIgnoreCase("postre")){
                listaIconos[i] = R.mipmap.dessert_logo;
            }
            else if (listaTipo[i].equalsIgnoreCase("pescado")){
                listaIconos[i] = R.mipmap.fish_logo;
            }
            else if (listaTipo[i].equalsIgnoreCase("carne")){
                listaIconos[i] = R.mipmap.meat_logo;
            }
            else if (listaTipo[i].equalsIgnoreCase("verdura")){
                listaIconos[i] = R.mipmap.vegetable_logo;
            }
            else{
                listaIconos[i] = R.mipmap.random_logo;
            }

        }

        RecetaAdapter adapter=new RecetaAdapter(actividadPadre, listaNombres , listaIconos,puntos);
        lv.setAdapter(adapter);
    }

}
