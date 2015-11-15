/*
 * Security.java v1.0 14/11/2015
 */
package com.example.coke.fooding;

public class Security {

	/**
	 * Clase para encriptar passwords
	 * 
	 * @version 1.0 - stub method
	 * @date 14/11/2015
	 */

	/**
	 * @param pw:
	 *            password a encriptar
	 * @return una cadena de caracteres que representa la password (encriptada)
	 */
	public static String encrypt_password(String pw) {

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(pw.getBytes());

		byte byteData[] = md.digest();

		// Convertir los bytes a hexadecimal
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}
}
