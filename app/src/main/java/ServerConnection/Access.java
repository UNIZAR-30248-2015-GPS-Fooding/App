/*
 * Access.java v1.0 29/10/2015
 */
package ServerConnection;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.LinkedList;
import java.util.List;

import ServerConnection.data.Data;
import ServerConnection.data.Ingrediente;
import ServerConnection.data.Receta;

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

            for (int i = 0; i < nl.getLength(); i++) {
                // agregar cada ingrediente
                Node n = nl.item(i);
                ings.add(n.getTextContent());
            }
            return ings;
        } else {
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

        System.out.println(xml);

        // mandar la peticion
        Document doc = Client.sendRequest(xml);

        if (doc != null) {
            System.out.println("HOLA");
            // se ha recibido respuesta correcta

            doc.getDocumentElement().normalize();

            // recorrer lista de recetas
            NodeList nl = doc.getElementsByTagName("receta");
            List<Receta> recetas = new LinkedList<Receta>();
            for (int i = 0; i < nl.getLength(); i++) {
                Element n = (Element) nl.item(i);

                // crear nueva receta
                Receta r = new Receta();

                // nombre
                r.setNombre(n.getElementsByTagName("nombre").item(0).getTextContent());

                // tipo
                r.setTipo(n.getElementsByTagName("tipo").item(0).getTextContent());

                // instrucciones
                r.setInstrucciones(n.getElementsByTagName("instrucciones").item(0).getTextContent());

                // me_gusta
                r.setMe_gusta(Integer.parseInt(n.getElementsByTagName("me_gusta").item(0).getTextContent()));

                // no_me_gusta
                r.setNo_me_gusta(Integer.parseInt(n.getElementsByTagName("no_me_gusta").item(0).getTextContent()));

                // ingredientes
                List<Ingrediente> ings = new LinkedList<Ingrediente>();
                NodeList nll = n.getElementsByTagName("ingrediente");
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

                // agregar receta
                recetas.add(r);
            }
            return recetas;
        }
        return null;
    }
}
