package com.shop.valueobjects;

public class Mitarbeiter extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = -830660481589983375L;

	// serialize default constructor
	public Mitarbeiter() {
	}
	
	public Mitarbeiter(String eMail, String password) {
		super(eMail, password);
	}
	
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
