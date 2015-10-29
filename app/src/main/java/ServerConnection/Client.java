/*
 * Client.java v1.0 29/10/2015
 */
package ServerConnection;

import org.w3c.dom.Document;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Client {

    /**
     * Clase para la conexion con el servidor
     * @version 1.0
     * @date 29/10/2015
     */

   /* URL a la que mandar las peticiones */
    private static String url = "https://fooding-gpsfooding.rhcloud.com/servlet/Listener";

    /**
     * @return un docuumento con el XML de respuesta del servidor al enviarle @param xml
     */
    public static Document sendRequest(String xml){
        Document doc = null;
        try {
            // crear la conexion
            URL u = new URL(url);
            URLConnection uc = u.openConnection();
            HttpURLConnection conn = (HttpURLConnection) uc;

            // parametros de la conexion
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "text/xml");
            conn.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
            conn.connect();

            // escribir la request
            PrintWriter pw = new PrintWriter(conn.getOutputStream());
            pw.write(xml);
            pw.close();

            // recibir la response en el documento
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(conn.getInputStream());
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return doc;
    }
}
