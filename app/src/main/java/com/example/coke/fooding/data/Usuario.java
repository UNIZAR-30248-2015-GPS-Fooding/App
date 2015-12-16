/*
 * Ingrediente.java v1.0 26/10/2015
 */

package com.example.coke.fooding.data;

public class Usuario {

	/**
	 * Clase para representar los ingredientes en las recetas
	 *
	 * @version 1.0
	 * @date 26/10/2015
	 */

	/* atributos de los ingredientes */
	private String nombre = null;
	private String email = null;

	/**
	 * Metodo de creacion de ingredientes
	 */
	public Usuario(){
	}
	
	/**
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            el nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return las uds
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param uds
	 *            las nuevas uds
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
