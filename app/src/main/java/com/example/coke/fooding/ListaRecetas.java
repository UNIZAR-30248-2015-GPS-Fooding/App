package com.example.coke.fooding;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ListaRecetas extends AppCompatActivity {

    ListView list;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_recetas);

        //ADAPTADOR PARA LAS RECETAS
        RecetaAdapter adapter=new RecetaAdapter(this, itemname, imgid);
        list=(ListView)findViewById(R.id.listRec);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = itemname[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
