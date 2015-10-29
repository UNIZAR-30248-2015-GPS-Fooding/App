/*
 * Access.java v1.0 29/10/2015
 */
package ServerConnection;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.LinkedList;
import java.util.List;

import ServerConnection.data.Data;

public class Access {

    /**
     * Clase para la formacion de peticiones para el servidor
     * @version 1.0
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
}
