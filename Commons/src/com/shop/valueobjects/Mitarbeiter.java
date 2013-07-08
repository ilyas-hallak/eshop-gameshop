package com.shop.valueobjects;

/**
 * @description Klasse Mitarbeiter - enthaelt die Werte eines Mitarbeiters mit getter und setter
 * @description erbt von Klasse Person
 */
public class Mitarbeiter extends Person {

	private static final long serialVersionUID = -830660481589983375L;

	// serialize default constructor
	public Mitarbeiter() {
	}
	
	/**
	 * @description Konstruktor fuer Mitarbeiter
	 * @param eMail
	 * @param password
	 */
	public Mitarbeiter(String eMail, String password) {
		super(eMail, password);
	}
	
	/**
	 * @description Zweiter Konstruktor fuer Mitarbeiter
	 * @param eMail
	 * @param name - zusaetzlich mit Name
	 * @param password
	 */
	public Mitarbeiter(String eMail, String name, String password) {
		super(eMail, password);
		this.setName(name);
	}
	
	public String setEMail(String mail) {
		return this.eMail = mail;
	}
	
	public String getEMail() {
		return this.eMail;
	}

}
