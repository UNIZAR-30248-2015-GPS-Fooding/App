/*
 * Access.java v1.0 29/10/2015
 */
package com.GPS.app.fooding.connection;

import com.GPS.app.fooding.data.Data;
import com.GPS.app.fooding.data.Ingrediente;
import com.GPS.app.fooding.data.Receta;
import com.GPS.app.fooding.data.Usuario;

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
            List<String> ings = new LinkedList<>();
            NodeList nl = doc.getElementsByTagName("ingrediente");

            if (nl != null && nl.getLength() > 0) {
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
    public static List<String> getTipos() {
        // hacer la peticion al servidor
        String xml = "<request id =\"" + Data.TIPO_CODE + "\"></request>";
        Document doc = Client.sendRequest(xml);

        if (doc != null) {
            // se han recuperado tipos
            doc.getDocumentElement().normalize();

            // recorrer la lista de tipos
            List<String> tipos = new LinkedList<>();
            NodeList nl = doc.getElementsByTagName("tipo");

            if (nl != null && nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {
                    // agregar cada tipo
                    Node n = nl.item(i);
                    tipos.add(n.getTextContent());
                }
            }
            return tipos;
        } else {
            return null;
        }
    }

    /**
     * Obtiene las recetas de la BD del servidor cuyo nombre coincide
     * con @param nombre, su tipo con @param tipo y todos los ingredientes
     * de @param ingredientes son ingredientes de la receta.
     * <p/>
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
        String xml = "<request id=\"" + Data.LIST_RECETA_CODE + "\">";
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
            List<Receta> recetas = new LinkedList<>();

            if (nl != null && nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {
                    Element n = (Element) nl.item(i);

                    // crear nueva receta
                    Receta r = new Receta();

                    // id
                    if (n.getElementsByTagName("id") != null
                            && n.getElementsByTagName("id").getLength() > 0) {
                        r.setId(Integer.parseInt(n.getElementsByTagName("id").item(0).getTextContent()));
                    }

                    // nombre
                    if (n.getElementsByTagName("nombre") != null
                            && n.getElementsByTagName("nombre").getLength() > 0) {
                        r.setNombre(n.getElementsByTagName("nombre").item(0).getTextContent());
                    }

                    // tipo
                    if (n.getElementsByTagName("tipo") != null
                            && n.getElementsByTagName("tipo").getLength() > 0) {
                        r.setTipo(n.getElementsByTagName("tipo").item(0).getTextContent());
                    }

                    // me_gusta
                    if (n.getElementsByTagName("me_gusta") != null
                            && n.getElementsByTagName("me_gusta").getLength() > 0) {
                        r.setMe_gusta(Integer.parseInt(n.getElementsByTagName("me_gusta").item(0).getTextContent()));
                    }

                    // no_me_gusta
                    if (n.getElementsByTagName("no_me_gusta") != null
                            && n.getElementsByTagName("no_me_gusta").getLength() > 0) {
                        r.setNo_me_gusta(Integer.parseInt(n.getElementsByTagName("no_me_gusta").item(0).getTextContent()));
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
     * Obtiene las recetas de la BD del servidor cuyo id coincide con
     *
     * @param id
     * @return una receta que coincide con el parametro de
     * busqueda
     */
    public static Receta getReceta(int id) {
        /* hacer la peticion al servidor */

        // crear la peticion
        String xml = "<request id=\"" + Data.RECETA_CODE + "\">";
        if (id >= 0)  // comprobar si el id esta bien
            xml = xml + "<id>" + id + "</id>";
        xml = xml + "</request>";
        System.out.println(xml);
        // mandar la peticion
        Document doc = Client.sendRequest(xml);

        if (doc != null) {
            // se ha recibido respuesta correcta

            doc.getDocumentElement().normalize();

            // crear nueva receta
            Receta r = new Receta();

            // id
            if (doc.getElementsByTagName("id") != null
                    && doc.getElementsByTagName("id").getLength() > 0) {
                r.setId(Integer.parseInt(doc.getElementsByTagName("id").item(0).getTextContent()));
            }

            // nombre
            if (doc.getElementsByTagName("nombre") != null
                    && doc.getElementsByTagName("nombre").getLength() > 0) {
                r.setNombre(doc.getElementsByTagName("nombre").item(0).getTextContent());
            }

            // tipo
            if (doc.getElementsByTagName("tipo") != null
                    && doc.getElementsByTagName("tipo").getLength() > 0) {
                r.setTipo(doc.getElementsByTagName("tipo").item(0).getTextContent());
            }

            // instrucciones
            if (doc.getElementsByTagName("instrucciones") != null
                    && doc.getElementsByTagName("instrucciones").getLength() > 0) {
                r.setInstrucciones(doc.getElementsByTagName("instrucciones").item(0).getTextContent());
            }

            // me_gusta
            if (doc.getElementsByTagName("me_gusta") != null
                    && doc.getElementsByTagName("me_gusta").getLength() > 0) {
                r.setMe_gusta(Integer.parseInt(doc.getElementsByTagName("me_gusta").item(0).getTextContent()));
                System.out.println(r.getMe_gusta());
            }

            // no_me_gusta
            if (doc.getElementsByTagName("no_me_gusta") != null
                    && doc.getElementsByTagName("no_me_gusta").getLength() > 0) {
                r.setNo_me_gusta(Integer.parseInt(doc.getElementsByTagName("no_me_gusta").item(0).getTextContent()));
                System.out.println(r.getNo_me_gusta());
            }

            // Usuario
            Usuario us = new Usuario();

            // mail usuario
            if (doc.getElementsByTagName("mail_autor") != null
                    && doc.getElementsByTagName("mail_autor").getLength() > 0) {
                us.setEmail(doc.getElementsByTagName("mail_autor").item(0).getTextContent());
            }

            // nick usuario
            if (doc.getElementsByTagName("nick_autor") != null
                    && doc.getElementsByTagName("nick_autor").getLength() > 0) {
                us.setNombre(doc.getElementsByTagName("nick_autor").item(0).getTextContent());
            }

            r.setAutor(us);

            // ingredientes
            List<Ingrediente> ings = new LinkedList<>();
            NodeList nll = doc.getElementsByTagName("ingrediente");
            if (nll != null && nll.getLength() > 0) {
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


            return r;
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
    public static boolean crear_usuario(String mail, String nick, String pw, boolean test) {
        String t;

        if (test) {
            t = "yes";
        } else {
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
        if (doc != null) {
            doc.getDocumentElement().normalize();

            if (doc.getElementsByTagName("hecho") != null &&
                    doc.getElementsByTagName("hecho").getLength() > 0) {
                t = doc.getElementsByTagName("hecho").item(0).getTextContent();
                return t.equalsIgnoreCase("yes");
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Intenta loguear a un usuario en la BD. Este usuario tendra
     *
     * @param mail como e-mail y @param pw como password
     * @return true si se ha logueado el usuario y false en caso contrario
     */
    public static boolean login_usuario(String mail, String pw, boolean test) {
        String t;

        if (test) {
            t = "yes";
        } else {
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
        if (doc != null) {
            doc.getDocumentElement().normalize();

            if (doc.getElementsByTagName("hecho") != null &&
                    doc.getElementsByTagName("hecho").getLength() > 0) {
                t = doc.getElementsByTagName("hecho").item(0).getTextContent();
                return t.equalsIgnoreCase("yes");
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * @param mail          : mail del usuario
     * @param nombre        : nombre de la nueva receta
     * @param tipo          : tipo de la nueva receta
     * @param instrucciones : instrucciones de la nueva receta
     * @param ings          : ingredientes de la nueva receta
     * @return <true> si se ha podido crear la receta, <false> en
     * caso contrario
     */
    public static boolean crear_receta(String mail, String nombre, String tipo, String instrucciones, List<Ingrediente> ings, boolean test) {
        String t;

        if (test) t = "yes";
        else t = "no";

        String xml = "<request id=\"" + Data.CREAR_REC_CODE + "\">";
        xml = xml + "<mail>" + mail + "</mail>";
        xml = xml + "<nombre>" + nombre + "</nombre>";
        xml = xml + "<tipo>" + tipo + "</tipo>";
        xml = xml + "<instrucciones>" + instrucciones + "</instrucciones>";
        xml = xml + "<test>" + t + "</test>";

        for (Ingrediente i : ings) {
            xml = xml + "<ingrediente cantidad=\"" + i.getCantidad()
                    + "\" uds=\"" + i.getUds() + "\">"
                    + i.getNombre() + "</ingrediente>";
        }

        xml = xml + "</request>";
        // enviar xml y recibir respuesta
        Document doc = Client.sendRequest(xml);

        // comprobar respuesta
        if (doc != null) {
            doc.getDocumentElement().normalize();

            if (doc.getElementsByTagName("hecho") != null &&
                    doc.getElementsByTagName("hecho").getLength() > 0) {
                t = doc.getElementsByTagName("hecho").item(0).getTextContent();
                return t.equalsIgnoreCase("yes");
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * @param id         identificador de la receta
     * @param valoracion -1 o 1
     * @param test       <true> si es test, <false> en caso contrario
     * @param mail       String con el mail
     * @return <true> si se ha podido valorar, <false> en caso contrario
     */
    public static boolean valorar_receta(int id, int valoracion, String mail, boolean test) {
        String t;

        if (test) t = "yes";
        else t = "no";

        String val = "?";
        if (valoracion > 0) {
            val = "mg";
        } else if (valoracion < 0) {
            val = "nmg";
        }

        String xml = "<request id=\"" + Data.VOTAR_CODE + "\">";
        xml = xml + "<id>" + id + "</id>";
        xml = xml + "<mail>" + mail + "</mail>";
        xml = xml + "<voto>" + val + "</voto>";
        xml = xml + "<test>" + t + "</test>";
        xml = xml + "</request>";

        System.out.println(xml);
        // enviar xml y recibir respuesta
        Document doc = Client.sendRequest(xml);

        // comprobar respuesta
        if (doc != null) {
            doc.getDocumentElement().normalize();

            if (doc.getElementsByTagName("hecho") != null &&
                    doc.getElementsByTagName("hecho").getLength() > 0) {
                t = doc.getElementsByTagName("hecho").item(0).getTextContent();
                return t.equalsIgnoreCase("yes");
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * @param id   identificador de la receta
     * @param test <true> si es test, <false> en caso contrario
     * @return la valoracion media de la receta
     */
    public static double valoracion_media_receta(int id, boolean test) {
        String t;

        if (test) t = "yes";
        else t = "no";

        String xml = "<request id=\"" + Data.VOTAR_CODE + "\">";
        xml = xml + "<id>" + id + "</id>";
        xml = xml + "<test>" + t + "</test>";

        xml = xml + "</request>";
        // enviar xml y recibir respuesta
        Document doc = Client.sendRequest(xml);

        // comprobar respuesta
        if (doc != null) {
            doc.getDocumentElement().normalize();

            if (doc.getElementsByTagName("valoracion") != null &&
                    doc.getElementsByTagName("valoracion").getLength() > 0) {
                t = doc.getElementsByTagName("valoracion").item(0).getTextContent();
                return Double.valueOf(t);
            } else {
                return -2;
            }
        } else {
            return -2;
        }
    }

    /**
     * @param test <true> si es test, <false> en caso contrario
     * @param nick nick del usuario a buscar (null si se quieren buscar todos los usuarios
     * @return una lista de los usuarios de la BD
     */
    public static List<Usuario> get_usuarios(String nick, boolean test) {
        String t;

        if (test) t = "yes";
        else t = "no";

        String xml = "<request id=\"" + Data.LIST_USER_CODE + "\">";
        if (nick != null)
            xml = xml + "<nick>" + nick + "</nick>";
        xml = xml + "<test>" + t + "</test>";

        xml = xml + "</request>";
        // enviar xml y recibir respuesta
        Document doc = Client.sendRequest(xml);

        // comprobar respuesta
        if (doc != null) {
            List<Usuario> users = new LinkedList<>();
            if (doc.getElementsByTagName("usuario") != null &&
                    doc.getElementsByTagName("usuario").getLength() > 0) {
                for (int i = 0; i < doc.getElementsByTagName("usuario").getLength(); i++) {
                    Element e = (Element) doc.getElementsByTagName("usuario").item(i);

                    String nombre = e.getElementsByTagName("nick").item(0).getTextContent();
                    int score = Integer.parseInt(e.getElementsByTagName("score").item(0).getTextContent());
                    String email = e.getElementsByTagName("mail").item(0).getTextContent();

                    Usuario u = new Usuario(nombre, score, null);
                    u.setEmail(email);
                    users.add(u);
                }
            }
            return users;
        } else {
            return null;
        }
    }

    /**
     * @param mail email del usuario
     * @param test <true> si es test, <false> en caso contrario
     * @return usuario de la BD
     */
    public static Usuario info_usuario(String mail, boolean test) {
        String t;

        if (test) t = "yes";
        else t = "no";

        String xml = "<request id=\"" + Data.USER_CODE + "\">";
        if (mail != null)
            xml = xml + "<mail>" + mail + "</mail>";
        xml = xml + "<test>" + t + "</test>";

        xml = xml + "</request>";
        // enviar xml y recibir respuesta
        Document doc = Client.sendRequest(xml);

        // comprobar respuesta
        if (doc != null) {
            String email = null;
            String nick = null;
            int score = -1;
            List<Receta> recetas = new LinkedList<>();

            if (doc.getElementsByTagName("mail") != null &&
                    doc.getElementsByTagName("mail").getLength() > 0) {
                email = doc.getElementsByTagName("mail").item(0).getTextContent();
            }
            if (doc.getElementsByTagName("nick") != null &&
                    doc.getElementsByTagName("nick").getLength() > 0) {
                nick = doc.getElementsByTagName("nick").item(0).getTextContent();
            }
            if (doc.getElementsByTagName("score") != null &&
                    doc.getElementsByTagName("score").getLength() > 0) {
                score = Integer.parseInt(doc.getElementsByTagName("score").item(0).getTextContent());
            }
            if (doc.getElementsByTagName("receta") != null &&
                    doc.getElementsByTagName("receta").getLength() > 0) {
                for (int j = 0; j < doc.getElementsByTagName("receta").getLength(); j++) {
                    Element ee = (Element) doc.getElementsByTagName("receta").item(j);
                    int id = Integer.parseInt(ee.getAttribute("id"));
                    String rec = ee.getTextContent();

                    Receta r = new Receta();
                    r.setId(id);
                    r.setNombre(rec);
                    recetas.add(r);
                }
            }

            Usuario u = new Usuario(nick, score, recetas);
            u.setEmail(email);
            return u;
        } else {
            return null;
        }
    }


    /**
     * Obtiene las recetas de la BD del servidor cuyo id coincide con
     *
     * @param id
     * @return una receta que coincide con el parametro de
     * busqueda
     */
    public static boolean esFavorita(String mail, int id, boolean test) {
        /* hacer la peticion al servidor */

        String testS = "";
        if (test) {
            testS = "yes";
        } else {
            testS = "no";
        }
        // crear la peticion
        String xml = "<request id=\"" + Data.CHECK_FAV_CODE + "\">";
        xml = xml + "<mail>" + mail + "</mail>";
        if (id >= 0)  // comprobar si el id esta bien
            xml = xml + "<id>" + id + "</id>";
        xml = xml + "<test>" + testS + "</test>";
        xml = xml + "</request>";

        // mandar la peticion
        Document doc = Client.sendRequest(xml);

        if (doc != null) {
            // se ha recibido respuesta correcta

            doc.getDocumentElement().normalize();

            boolean returned = false;

            // id
            if (doc.getElementsByTagName("favorita") != null
                    && doc.getElementsByTagName("favorita").getLength() > 0) {
                returned = doc.getElementsByTagName("favorita").item(0).getTextContent().equals("yes");
            }

            return returned;
        }
        return false;
    }

    /**
     * Obtiene las recetas de la BD del servidor cuyo id coincide con
     *
     * @param id
     * @return una receta que coincide con el parametro de
     * busqueda
     */
    public static boolean hacerFavorita(String mail, int id, boolean test) {
        /* hacer la peticion al servidor */
        String testS = "";
        if (test) {
            testS = "yes";
        } else {
            testS = "no";
        }
        // crear la peticion
        String xml = "<request id=\"" + Data.FAV_CODE + "\">";
        xml = xml + "<mail>" + mail + "</mail>";
        if (id >= 0)  // comprobar si el id esta bien
            xml = xml + "<id>" + id + "</id>";
        xml = xml + "<test>" + testS + "</test>";
        xml = xml + "</request>";

        // mandar la peticion
        Document doc = Client.sendRequest(xml);

        if (doc != null) {
            // se ha recibido respuesta correcta

            doc.getDocumentElement().normalize();

            boolean returned = false;

            // id
            if (doc.getElementsByTagName("hecho") != null
                    && doc.getElementsByTagName("hecho").getLength() > 0) {
                returned = doc.getElementsByTagName("hecho").item(0).getTextContent().equals("yes");
            }

            return returned;
        }
        return false;
    }

    /**
     * Obtiene las recetas de la BD del servidor cuyo id coincide con
     *
     * @param id
     * @return una receta que coincide con el parametro de
     * busqueda
     */
    public static boolean quitarFavorita(String mail, int id, boolean test) {
        /* El servidor deshace el favorito si al hacer favorito la receta ya es favorita */
        return hacerFavorita(mail, id, test);
    }

    /**
     * Obtiene las recetas de la BD del servidor cuyo nombre coincide
     * con @param nombre, su tipo con @param tipo y todos los ingredientes
     * de @param ingredientes son ingredientes de la receta.
     * <p/>
     * Si @param nombre es null, no se buscara por nombre.
     * Si @param tipo es null, no se buscara por tipo.
     * Si @param ingredientes es null, no se buscara por ingredientes.
     *
     * @return una lista de las recetas que coinciden con los parametros de
     * busqueda
     */
    public static List<Receta> get_favoritos(String mail, boolean test) {
        /* hacer la peticion al servidor */
        String testS = "no";
        if(test)
            testS = "yes";
        // crear la peticion
        String xml = "<request id=\"" + Data.LIST_FAV_CODE + "\">";
        xml = xml + "<mail>" + mail + "</mail>";
        xml = xml + "<test>" + testS + "</test>";
        xml = xml + "</request>";

        // mandar la peticion
        Document doc = Client.sendRequest(xml);

        if (doc != null) {
            // se ha recibido respuesta correcta

            doc.getDocumentElement().normalize();

            // recorrer lista de recetas
            NodeList nl = doc.getElementsByTagName("receta");
            List<Receta> recetas = new LinkedList<>();

            if (nl != null && nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {
                    Element n = (Element) nl.item(i);

                    // crear nueva receta
                    Receta r = new Receta();

                    // id
                    if (n.getElementsByTagName("id") != null
                            && n.getElementsByTagName("id").getLength() > 0) {
                        r.setId(Integer.parseInt(n.getElementsByTagName("id").item(0).getTextContent()));
                    }

                    // nombre
                    if (n.getElementsByTagName("nombre") != null
                            && n.getElementsByTagName("nombre").getLength() > 0) {
                        r.setNombre(n.getElementsByTagName("nombre").item(0).getTextContent());
                    }

                    // tipo
                    if (n.getElementsByTagName("tipo") != null
                            && n.getElementsByTagName("tipo").getLength() > 0) {
                        r.setTipo(n.getElementsByTagName("tipo").item(0).getTextContent());
                    }

                    // me_gusta
                    if (n.getElementsByTagName("me_gusta") != null
                            && n.getElementsByTagName("me_gusta").getLength() > 0) {
                        r.setMe_gusta(Integer.parseInt(n.getElementsByTagName("me_gusta").item(0).getTextContent()));
                    }

                    // no_me_gusta
                    if (n.getElementsByTagName("no_me_gusta") != null
                            && n.getElementsByTagName("no_me_gusta").getLength() > 0) {
                        r.setNo_me_gusta(Integer.parseInt(n.getElementsByTagName("no_me_gusta").item(0).getTextContent()));
                    }

                    // agregar receta
                    recetas.add(r);
                }
            }
            return recetas;
        }
        return null;
    }
}
