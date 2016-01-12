package com.GPS.app.fooding;

/**
 * Created by coke on 07/10/2015.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.GPS.app.fooding.connection.ClientInterface;

public class RecetaAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final int[] ids;
    private final String[] itemname;
    private final Integer[] imgid;
    private final Double[] puntos;

    public RecetaAdapter(Activity context, String[] itemname, int[] ids, Integer[] imgid, Double[] puntos) {
        super(context, R.layout.receta, itemname);
        this.ids = ids;
        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
        this.puntos=puntos;
    }

    @Override
    public View getView(final int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.receta, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        final ImageView favView = (ImageView)rowView.findViewById(R.id.iconStar);
        if (ClientInterface.esFavorita(MainActivity.mail, ids[position], false)) {
            favView.setImageResource(R.mipmap.fav_logo);
        }
        else{
            favView.setImageResource(R.mipmap.no_fav_logo);
        }


        favView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Si está logueado
                if (MainActivity.registrado){
                    //TODO ESTO FUNCIONARÁ CUANDO ESTÉ IMPLEMENTADO EL METODO
                    if (ClientInterface.esFavorita(MainActivity.mail, ids[position],false)) {
                        ClientInterface.quitarFavorita(MainActivity.mail, ids[position]);
                        favView.setImageResource(R.mipmap.no_fav_logo);
                        Toast.makeText(getContext(), "Favorito eliminado correctamente", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        ClientInterface.hacerFavorita(MainActivity.mail, itemname[position]);
                        favView.setImageResource(R.mipmap.fav_logo);
                        Toast.makeText(getContext(), "Favorito añadido correctamente", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getContext(), "Debes estar registrado para usar la funcion Favorito", Toast.LENGTH_SHORT).show();
                }



            }
        });

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        extratxt.setText(MainActivity.DF.format(puntos[position]) + "% de votos positivos");
        return rowView;

    };
}