package com.example.coke.fooding;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by coke on 15/10/2015.
 */
public class ListaRecetasFragment extends Fragment {

    Activity actividadPadre;

    ListView lv;
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
            R.mipmap.fish_logo,
            R.mipmap.vegetable_logo,
            R.mipmap.fish_logo,
            R.mipmap.meat_logo,
            R.mipmap.meat_logo,
    };

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        actividadPadre = this.getActivity();
        View view = inflater.inflate(R.layout.activity_lista_recetas, container, false);
        ListView lv = (ListView) view.findViewById(R.id.listRec);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = itemname[+position];
                Toast.makeText(actividadPadre.getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });

        //ADAPTADOR PARA LAS RECETAS
        RecetaAdapter adapter=new RecetaAdapter(actividadPadre, itemname, imgid);
        lv.setAdapter(adapter);
        return view;
    }
}
