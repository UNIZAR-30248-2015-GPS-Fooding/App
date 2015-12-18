/*
 * Access.java v1.0 29/10/2015
 */
package com.example.coke.fooding;

import com.example.coke.fooding.data.Data;
import com.example.coke.fooding.data.Ingrediente;
import com.example.coke.fooding.data.Receta;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.LinkedList;
import java.util.List;

public class Access {

    /**
     * Clase para la formacion de peticiones para el servidor
     * @version 1.0
     *  - getIngredientes() para obtener la lista de ingredientes
     *  - getRecetas(nombre, tipo, ingredientes) para obtener la lista de
     *      recetas.
     *  ++ Referirse a la documentacion de cada metodo para saber mas.
     * @date 29/10/2015
     */

    /**
     * Obtiene los ingredientes que hay en la BD del servidor.
     *
     * @return una lista de ingredientes si ha funcionado correctamente, o
     * null si no puede conectarse al servidor
     */
    public static List<String> getIngredientes() {
        // hacer la peticion al servidor
        String xml = "<request id =\"" + Data.ING_CODE + "\"></request>";
        Document doc = Client.sendRequest(xml);

        if (doc != null) {
            // se han recuperado ingredientes
            doc.getDocumentElement().normalize();

            // recorrer la lista de ingredientes
            List<String> ings = new LinkedList<String>();
            NodeList nl = doc.getElementsByTagName("ingrediente");

            if(nl != null && nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {
                    // agregar cada ingrediente
                    Node n = nl.item(i);
                    ings.add(n.getTextContent());
                }
            }
            return ings;
        } else {
            return null;
        }
    }

    /**
     * Obtiene los tipos que hay en la base de datos del servidor
     *
     * @return una lista de tipos si ha funcionado correctamente o
     * null si no puede conectarse con el servidor
     */
    public static List<String> getTipos(){
        // hacer la peticion al servidor
        String xml = "<request id =\"" + Data.TIPO_CODE + "\"></request>";
        Document doc = Client.sendRequest(xml);

        if(doc != null){
            // se han recuperado tipos
            doc.getDocumentElement().normalize();

            // recorrer la lista de tipos
            List<String> tipos = new LinkedList<String>();
            NodeList nl = doc.getElementsByTagName("tipo");

            if(nl != null && nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {
                    // agregar cada tipo
                    Node n = nl.item(i);
                    tipos.add(n.getTextContent());
                }
            }
            return tipos;
        }
        else{
            return null;
        }
    }

    /**
     * Obtiene las recetas de la BD del servidor cuyo nombre coincide
     * con @param nombre, su tipo con @param tipo y todos los ingredientes
     * de @param ingredientes son ingredientes de la receta.
     *
     * Si @param nombre es null, no se buscara por nombre.
     * Si @param tipo es null, no se buscara por tipo.
     * Si @param ingredientes es null, no se buscara por ingredientes.
     *
     * @return una lista de las recetas que coinciden con los parametros de
     * busqueda
     */
    public static List<Receta> getRecetas(String nombre, String tipo, List<String> ingredientes) {
        /* hacer la peticion al servidor */

        // crear la peticion
        String xml = "<request id=\"" + Data.RECETA_CODE + "\">";
        if (nombre != null)  // comprobar si hay que poner nombre
            xml = xml + "<nombre>" + nombre + "</nombre>";
        if (tipo != null)    // comprobar si hay que poner tipo
            xml = xml + "<tipo>" + tipo + "</tipo>";
        if (ingredientes != null) {   // comprobar si hay que poner ingredientes
            for (String ing : ingredientes) {
                xml = xml + "<ingrediente>" + ing + "</ingrediente>";
            }
        }
        xml = xml + "</request>";

        // mandar la peticion
        Document doc = Client.sendRequest(xml);

        if (doc != null) {
            // se ha recibido respuesta correcta

            doc.getDocumentElement().normalize();

            // recorrer lista de recetas
            NodeList nl = doc.getElementsByTagName("receta");
            List<Receta> recetas = new LinkedList<Receta>();

            if(nl != null && nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {
                    Element n = (Element) nl.item(i);

                    // crear nueva receta
                    Receta r = new Receta();

                    // id
                    if(n.getElementsByTagName("id") != null
                            && n.getElementsByTagName("id").getLength() > 0) {
                        r.setId(Integer.parseInt(n.getElementsByTagName("id").item(0).getTextContent()));
                    }

                    // nombre
                    if(n.getElementsByTagName("nombre") != null
                            && n.getElementsByTagName("nombre").getLength() > 0) {
                        r.setNombre(n.getElementsByTagName("nombre").item(0).getTextContent());
                    }

                    // tipo
                    if(n.getElementsByTagName("tipo") != null
                            && n.getElementsByTagName("tipo").getLength() > 0) {
                        r.setTipo(n.getElementsByTagName("tipo").item(0).getTextContent());
                    }

                    // instrucciones
                    if(n.getElementsByTagName("instrucciones") != null
                            && n.getElementsByTagName("instrucciones").getLength() > 0) {
                        r.setInstrucciones(n.getElementsByTagName("instrucciones").item(0).getTextContent());
                    }

                    // me_gusta
                    if(n.getElementsByTagName("me_gusta") != null
                            && n.getElementsByTagName("me_gusta").getLength() > 0) {
                        r.setMe_gusta(Integer.parseInt(n.getElementsByTagName("me_gusta").item(0).getTextContent()));
                    }

                    // no_me_gusta
                    if(n.getElementsByTagName("no_me_gusta") != null
                            && n.getElementsByTagName("no_me_gusta").getLength() > 0) {
                        r.setNo_me_gusta(Integer.parseInt(n.getElementsByTagName("no_me_gusta").item(0).getTextContent()));
                    }

                    // ingredientes
                    List<Ingrediente> ings = new LinkedList<Ingrediente>();
                    NodeList nll = n.getElementsByTagName("ingrediente");
                    if(nll != null && nll.getLength() > 0) {
                        for (int j = 0; j < nll.getLength(); j++) {
                            Element nn = (Element) nll.item(j);

                            // crear ingrediente
                            Ingrediente ing = new Ingrediente();
                            ing.setNombre(nn.getTextContent());
                            ing.setCantidad(Integer.parseInt(nn.getAttribute("cantidad")));
                            ing.setUds(nn.getAttribute("uds"));

                            // agregar ingrediente
                            ings.add(ing);
                        }
                        r.setIngredientes(ings);
                    }

                    // agregar receta
                    recetas.add(r);
                }
            }
            return recetas;
        }
        return null;
    }

    /**
     * Intenta crear un nuevo usuario en la BD (BD de test o de produccion,
     * dependiendo del @param test). Este usuario tendra @param mail como
     * e-mail, @param nick como nickname y @param pw como password
     *
     * @return true si se ha creado el usuario y false en caso contrario
     */
    public static boolean crear_usuario(String mail, String nick, String pw, boolean test){
        String t = null;

        if(test){
            t = "yes";
        }
        else{
            t = "no";
        }

        // crear xml
        String xml = "<request id=\"" + Data.CREAR_USER_CODE + "\">";
        xml += "<mail>" + mail + "</mail>";
        xml += "<nick>" + nick + "</nick>";
        xml += "<pw>" + pw + "</pw>";
        xml += "<test>" + t + "</test>";
        xml += "</request>";

        // enviar xml y recibir respuesta
        Document doc = Client.sendRequest(xml);

        // comprobar respuesta
        if(doc != null){
            doc.getDocumentElement().normalize();

            if(doc.getElementsByTagName("hecho") != null &&
                    doc.getElementsByTagName("hecho").getLength() > 0){
                t = doc.getElementsByTagName("hecho").item(0).getTextContent();
               return t.equalsIgnoreCase("yes");
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    /**
     * Intenta loguear a un usuario en la BD. Este usuario tendra
     * @param mail como e-mail y @param pw como password
     *
     * @return true si se ha logueado el usuario y false en caso contrario
     */
    public static boolean login_usuario(String mail, String pw, boolean test){
        String t = null;

        if(test){
            t = "yes";
        }else{
            t = "no;";
        }
        // crear xml
        String xml = "<request id=\"" + Data.LOGIN_CODE + "\">";
        xml += "<mail>" + mail + "</mail>";
        xml += "<pw>" + pw + "</pw>";
        xml += "<test>" + t + "</test>";
        xml += "</request>";

        // enviar xml y recibir respuesta
        Document doc = Client.sendRequest(xml);

        // comprobar respuesta
        if(doc != null){
            doc.getDocumentElement().normalize();

            if(doc.getElementsByTagName("hecho") != null &&
                    doc.getElementsByTagName("hecho").getLength() > 0){
                t = doc.getElementsByTagName("hecho").item(0).getTextContent();
                return t.equalsIgnoreCase("yes");
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    /**
     * @param nombre : nombre de la nueva receta
     * @param tipo : tipo de la nueva receta
     * @param instrucciones : instrucciones de la nueva receta
     * @param ings : ingredientes de la nueva receta
     * @return <true> si se ha podido crear la receta, <false> en
     * caso contrario
     */
    public static boolean crear_receta(String nombre, String tipo, String instrucciones, List<Ingrediente> ings, boolean test){
        String t = null;

        if(test) t= "yes";
        else t= "no";

        String xml = "<request id=\"" + Data.CREAR_REC_CODE + "\">";
        xml = xml + "<nombre>" + nombre + "</nombre>";
        xml = xml + "<tipo>" + tipo + "</tipo>";
        xml = xml + "<instrucciones>" + instrucciones + "</instrucciones>";
        xml = xml + "<test>" + t + "</test>";

        for(Ingrediente i : ings){
            xml = xml + "<ingrediente cantidad=\"" + i.getCantidad()
                    + "\" uds=\"" + i.getUds() + "\">"
                    + i.getNombre() + "</ingrediente>";
        }

        xml = xml + "</request>";
        // enviar xml y recibir respuesta
        Document doc = Client.sendRequest(xml);

        // comprobar respuesta
        if(doc != null){
            doc.getDocumentElement().normalize();

            if(doc.getElementsByTagName("hecho") != null &&
                    doc.getElementsByTagName("hecho").getLength() > 0){
                t = doc.getElementsByTagName("hecho").item(0).getTextContent();
                return t.equalsIgnoreCase("yes");
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
}
