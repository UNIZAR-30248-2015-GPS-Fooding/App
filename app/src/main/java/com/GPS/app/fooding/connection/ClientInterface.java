/*
 * ClientInterface.java v1.0 05/11/2015
 */
package com.GPS.app.fooding.connection;

import com.GPS.app.fooding.data.Ingrediente;
import com.GPS.app.fooding.data.Receta;
import com.GPS.app.fooding.data.Usuario;

import java.security.NoSuchAlgorithmException;
import java.util.List;

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
     * **** IMPORTANTE: getRecetasFiltros() funciona con (nombre, null, null)
     *      No se garantiza que funcione con tipo o ingredientes (TODAVIA)
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
     * @return la lista de tipos de la BD
     */
    public static List<String> getTipos() { return Access.getTipos(); }

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

    /**
     * @param mail: e-mail del usuario
     * @param nick: nickname del usuario
     * @param pw: password del usuario (se encriptara aqui)
     * @param test: <true> si se quiere realizar la operacion sobre la BD de test,
     *            <false> en caso contrario
     * @return <true> si se ha podido crear el usuario, <false> en caso contrario
     */
    public static boolean crear_usuario(String mail, String nick, String pw, boolean test){
        try {
            String encrypted_pw = Security.encrypt_password(pw);
            return Access.crear_usuario(mail, nick, encrypted_pw, test);
        }
        catch(NoSuchAlgorithmException e){
            return false;
        }
    }

    /**
     * @param mail: e-mail del usuario
     * @param pw: password del usuario (se encriptara aqui)
     * @param test: <true> si es test, <false> en caso contrario
     * @return <true> si se ha podido loguear al usuario, <false> en caso contrario
     */
    public static boolean login_usuario(String mail, String pw, boolean test){
        try{
            String encrypted_pw = Security.encrypt_password(pw);
            return Access.login_usuario(mail, encrypted_pw, test);
        }
        catch(NoSuchAlgorithmException e){
            return false;
        }
    }

    /**
     * @param mail : mail del
     * @param nombre : nombre de la nueva receta
     * @param tipo : tipo de la nueva receta
     * @param instrucciones : instrucciones de la nueva receta
     * @param ings : ingredientes de la nueva receta
     * @param test : <true> si es test, <false> en caso contrario
     * @return <true> si se ha podido crear la receta, <false> en caso contrario
     */
    public static boolean crear_receta(String mail, String nombre, String tipo, String instrucciones, List<Ingrediente> ings, boolean test){
        return Access.crear_receta(mail, nombre, tipo, instrucciones, ings, test);
    }

    /**
     * @param id : identificador de la receta
     * @param valoracion : -1 o 1
     * @param test : <true> si es test, <false> en caso contrario
     * @return <true> si se ha podido valorar la receta, <false> en caso contrario
     */
    public static boolean valorar_receta(int id, int valoracion, String mail, boolean test){
        return Access.valorar_receta(id, valoracion, mail, test);
    }

    /**
     * @param id : identificador de la receta
     * @param test : <true> si es test, <false> en caso contrario
     * @return valoracion media de la receta, -2 si no se ha podido valorar
     */
    public static double valoracion_media_receta(int id, boolean test){
        return Access.valoracion_media_receta(id, test);
    }

    /**
     * @param test : <true> si es test, <false> en caso contrario
     * @param nick nick del usuario a buscar (null si se quieren buscar todos los usuarios
     * @return lista de usuarios del sistema
     */
    public static List<Usuario> get_usuarios(String nick, boolean test){
        return Access.get_usuarios(nick, test);
    }

    /**
     * @param mail email del usuario
     * @param test <true> si es test, <false> en caso contrario
     * @return usuario de la BD
     */
    public static Usuario info_usuario(String mail, boolean test){
        return Access.info_usuario(mail, test);
    }
}
