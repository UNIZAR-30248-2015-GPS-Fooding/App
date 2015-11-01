package com.example.coke.fooding;

import android.app.Activity;
import android.app.Dialog;

import android.app.ListFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by coke on 15/10/2015.
 */
public class ListaRecetasFragment extends android.support.v4.app.Fragment {

    static Activity actividadPadre;

    static ListView lv;
    String[] itemname ={
            "Receta",
            "Receta",
            "Receta",
            "Receta",
            "Receta",
            "Receta",
            "Receta",
            "Receta"
    };

    Integer[] imgid={
            R.mipmap.fish_logo,
            R.mipmap.meat_logo,
            R.mipmap.vegetable_logo,
            R.mipmap.pasta_logo,
            R.mipmap.meat_logo,
            R.mipmap.fish_logo,
            R.mipmap.pasta_logo,
            R.mipmap.dessert_logo,
    };

    static ArrayList<String> nombresReceta = new ArrayList<>();
    static ArrayList<Integer> logosReceta = new ArrayList<>();

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
                actualizarLista();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = itemname[+position];
                Toast.makeText(actividadPadre.getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                showEditDialog();

            }
        });

        //ADAPTADOR PARA LAS RECETAS
        RecetaAdapter adapter=new RecetaAdapter(actividadPadre, itemname, imgid);
        lv.setAdapter(adapter);
        return view;
    }

    private void showEditDialog() {
        FragmentManager fm = getFragmentManager();
        RecetaDialog editNameDialog = new RecetaDialog();
        editNameDialog.show(fm, "fragment_edit_name");
    }

    public static void actualizarLista(){
        RecetaAdapter adapter=new RecetaAdapter(actividadPadre, nombresReceta.toArray(new String[nombresReceta.size()]) , logosReceta.toArray(new Integer[logosReceta.size()]));
        lv.setAdapter(adapter);
    }

}