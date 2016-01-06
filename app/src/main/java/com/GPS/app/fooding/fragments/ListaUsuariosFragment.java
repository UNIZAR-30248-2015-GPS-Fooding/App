package com.GPS.app.fooding.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.GPS.app.fooding.connection.ClientInterface;
import com.GPS.app.fooding.R;
import com.GPS.app.fooding.UsuarioAdapter;
import com.GPS.app.fooding.UsuarioDialog;
import com.GPS.app.fooding.data.Usuario;

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
                //String Slecteditem = itemname[+position];
                Usuario usuarioSeleccionado = listaUsuarios.get(position);
                //Toast.makeText(actividadPadre.getApplicationContext(), usuarioSeleccionado.getNombre(), Toast.LENGTH_SHORT).show();
                showEditDialog(usuarioSeleccionado);

            }
        });

        //Permitimos modificar la action bar
        setHasOptionsMenu(true);


        //ACTUALIZAMOS LA LISTA
        actualizarLista(null);
        return view;

    }

    /**
     * Modificamos el action bar con la nueva opcion de buscar usuarios
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_usuario, menu);
    }

    private void showEditDialog(Usuario usuarioSeleccionado) {
        FragmentManager fm = getFragmentManager();
        UsuarioDialog editNameDialog = new UsuarioDialog(usuarioSeleccionado);
        editNameDialog.show(fm, "fragment_edit_name");
    }

    public static void actualizarLista(List<Usuario> lista){
        if(lista== null){
            // TODO pongo null pero ni idea de que va ahi (el nombre de usuario?)
            listaUsuarios = ClientInterface.get_usuarios(null, test);
        }else{
            listaUsuarios = lista;
        }
        String[] listaNombres = new String[listaUsuarios.size()];
        Integer[] listaIconos = new Integer[listaUsuarios.size()];
        String[] listaPuntos = new String[listaUsuarios.size()];

        //creamos la lista de nombres y de logos
        for(int i=0;i<listaUsuarios.size();i++){
            listaNombres[i] = listaUsuarios.get(i).getNombre();
            listaIconos[i] = R.mipmap.user_logo;
            listaPuntos[i] = Integer.toString(listaUsuarios.get(i).getScore());
        }

        UsuarioAdapter adapter=new UsuarioAdapter(actividadPadre, listaNombres , listaIconos, listaPuntos);
        lv.setAdapter(adapter);
    }

}
