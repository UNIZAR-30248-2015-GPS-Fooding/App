/*
 * Ingrediente.java v1.0 26/10/2015
 */

package com.GPS.app.fooding.data;

import java.util.List;

public class Usuario {

	/**
	 * Clase para representar los ingredientes en las recetas
	 *
	 * @version 1.0
	 * @date 26/10/2015
	 */

	/* atributos de los ingredientes */
	private String nombre = null;
	private int score = -1;
	private List<Receta> recetas = null;
	private String email = null;

	/**
	 * Metodo de creacion de usuarios
	 */
	public Usuario(){
	}

	/**
	 * @return mail del usuario
	 */
	public String getEmail(){
		return email;
	}

	/**
	 * @param mail e-mail del usuario
	 */
	public void setEmail(String mail){
		this.email = mail;
	}

	/**
	 * Metodo de creacion de usuarios
	 */
	public Usuario(String nombre, int score, List<Receta> recetas){
		this.nombre = nombre;
		this.score = score;
		this.recetas = recetas;
	}
	
	/**
	 * @return el score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 *            el nuevo score
	 */
	public void setScore(int score) {
		this.score = score;
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
	 * @return las recetas
	 */
	public List<Receta> getRecetas() {
		return recetas;
	}

	/**
	 * @param recs
	 *            las nuevas recetas
	 */
	public void setRecetas(List<Receta> recs) {
		this.recetas = recs;
	}
}
