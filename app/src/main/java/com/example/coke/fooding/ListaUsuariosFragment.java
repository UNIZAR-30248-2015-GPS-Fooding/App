package com.example.coke.fooding;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.coke.fooding.data.Receta;
import com.example.coke.fooding.data.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coke on 15/10/2015.
 */
public class ListaUsuariosFragment extends android.support.v4.app.Fragment {

    static Activity actividadPadre;
    static ListView lv;
    static List<Usuario> listaUsuarios = null;
    static boolean test = false;

    public ListaUsuariosFragment (){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        actividadPadre = this.getActivity();
        View view = inflater.inflate(R.layout.fragment_lista_usuarios, container, false);
        lv = (ListView) view.findViewById(R.id.listUsu);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                //String Slecteditem = itemname[+position];
                Usuario usuarioSeleccionado = listaUsuarios.get(position);
                //Toast.makeText(actividadPadre.getApplicationContext(), usuarioSeleccionado.getNombre(), Toast.LENGTH_SHORT).show();
                showEditDialog(usuarioSeleccionado);

            }
        });

        //ACTUALIZAMOS LA LISTA
        actualizarLista(null);
        return view;

    }

    private void showEditDialog(Usuario usuarioSeleccionado) {
        FragmentManager fm = getFragmentManager();
        UsuarioDialog editNameDialog = new UsuarioDialog(usuarioSeleccionado);
        editNameDialog.show(fm, "fragment_edit_name");
    }

    public static void actualizarLista(List<Usuario> lista){
        if(lista== null){
            listaUsuarios = ClientInterface.get_usuarios(test);
        }else{
            listaUsuarios = lista;
        }
        String[] listaNombres = new String[listaUsuarios.size()];
        Integer[] listaIconos = new Integer[listaUsuarios.size()];

        //creamos la lista de nombres y de logos
        for(int i=0;i<listaUsuarios.size();i++){
            listaNombres[i] = listaUsuarios.get(i).getNombre();
            listaIconos[i] = R.mipmap.user_logo;
        }

        UsuarioAdapter adapter=new UsuarioAdapter(actividadPadre, listaNombres , listaIconos);
        lv.setAdapter(adapter);
    }

}
