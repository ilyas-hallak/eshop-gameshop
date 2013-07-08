package com.shop.valueobjects;

import java.io.Serializable;

/**
 * @description Klasse Person - enthaelt die Werte einer Person allgemein (Oberklasse), mit getter und setter
 */
public class Person implements Serializable {

	// ATTRIBUTE der Klasse
	private static final long serialVersionUID = 936335457255744308L; // SerialNummer generiert
	protected int nr;
	protected String name;
	protected String password;
	protected String eMail;
	
	// serialize default constructor
	public Person() {
		
	}
	
	/**
	 * @description Konstruktor fuer Person
	 * @param eMail
	 * @param password
	 */
	public Person(String eMail, String password) {
		this.eMail = eMail;
		this.password = password;
	}

	/**
	 * @return the nr
	 */
	public int getNr() {
		return nr;
	}

	/**
	 * @param nr the nr to set
	 */
	public void setNr(int nr) {
		this.nr = nr;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the eMail
	 */
	public String geteMail() {
		return eMail;
	}

	/**
	 * @param eMail the eMail to set
	 */
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
}
