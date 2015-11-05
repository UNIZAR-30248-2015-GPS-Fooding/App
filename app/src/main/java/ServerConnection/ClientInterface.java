/*
 * ClientInterface.java v1.0 05/11/2015
 */
package ServerConnection;

import java.util.List;

import ServerConnection.Access;
import ServerConnection.data.Receta;

public class ClientInterface {

    /**
     * Clase para facilitar la conexion entre cliente y servidor
     * @version 1.0
     *  - getIngredientes() obtiene todos los ingredientes disponibles
     *  - getRecetas() obtiene todas las recetas disponibles
     *  - getRecetasFiltros(nombre, tipo, ingredientes) obtiene todas las
     *      recetas cuyo nombre coincida con el especificado, su tipo sea
     *      el mismo que el parametro y entre cuyos ingredientes esten los
     *      especificados en la lista de ingredientes especificada.
     *          Si no se quiere buscar por todos los campos, poner los campos
     *          que no se usen a <null>.
     *
     * @date 05/11/2015
     */

    /**
     * @return la lista de ingredientes de la BD
     */
    public static List<String> getIngredientes(){
        return Access.getIngredientes();
    }

    /**
     * @return el listado con todas las recetas de la BD
     */
    public static List<Receta> getRecetas(){
        return Access.getRecetas(null, null, null);
    }

    /**
     * @param nombre: <null> o el nombre (o parte del mismo) por el que filtrar
     * @param tipo: <null> o el tipo por el que filtrar
     * @param ingredientes: <null> o la lista de ingredientes que debe contener
     *                    las recetas devueltas
     * @return una lista con todas las recetas cuyo nombre coincida total o
     * parcialmente con @param nombre, cuyo tipo sea el especificado
     * en @param tipo y entre cuyos ingredientes se encuentren aquellos
     * contenidos en @param ingredientes
     */
    public static List<Receta> getRecetasFiltros(String nombre, String tipo, List<String> ingredientes){
        return Access.getRecetas(nombre, tipo, ingredientes);
    }
}
