/*
 * Client.java v1.0 29/10/2015
 */
package com.example.coke.fooding;

import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class Client {

    /**
     * Clase para la conexion con el servidor
     *
     * @version 1.0
     * @date 29/10/2015
     */

	/* URL a la que mandar las peticiones */
    private static String url = "http://fooding-gpsfooding.rhcloud.com/servlet/Listener";

    /**
     * @return un docuumento con el XML de respuesta del servidor al
     *         enviarle @param xml
     */
    public static Document sendRequest(String xml) {
        Document doc = null;
        try {
            // crear la conexion
            URL u = new URL(url);
            URLConnection uc = u.openConnection();
            HttpsURLConnection conn = (HttpsURLConnection) uc;

            // parametros de la conexion
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "text/xml");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 ( compatible ) ");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length",
                    Integer.toString(xml.getBytes().length));
            conn.setUseCaches(false);

            // escribir la request
            DataOutputStream wr = new DataOutputStream(
                    conn.getOutputStream());
            wr.writeBytes(xml);
            wr.close();

            if (conn.getResponseCode() >= 200 && conn.getResponseCode() < 300) {
                // codigo de exito

                // recibir la response en el documento
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                String s = get(conn.getInputStream());
                doc = builder.parse(new ByteArrayInputStream(s.getBytes(Charset.forName("UTF-8"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc;
    }

    /**
     * Parsea la respuesta del servidor
     * @param is InputStream del servidor
     */
    private static String get(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
