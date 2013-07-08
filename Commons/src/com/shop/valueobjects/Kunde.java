package com.shop.valueobjects;

/**
 * @description Klasse Kunde - enthaelt die Werte eines Kunden mit getter und setter
 * @description erbt von der Klasse Person
 */
public class Kunde extends Person {

	// ATTRIBUTE der Klasse
	private static final long serialVersionUID = 2345625329911573006L; // Serialisierte Nummer (generiert)
	private String address;
	private Warenkorb cart;
	
	/**
	 * @return the cart
	 */
	public Warenkorb getCart() {
		return cart;
	}

	/**
	 * @param cart the cart to set
	 */
	public void setCart(Warenkorb cart) {
		this.cart = cart;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	// serialize default constructor fuer Persistenz
	public Kunde() {
	}
		
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}
	
	/**
	 * @description Konstruktor der Klasse Kunde um diesen zu erstellen
	 * @param name - Name des Kunden
	 * @param eMail - E-Mail des Kunden
	 * @param address - Adresse des Kunden
	 * @param pw - Passwort des Kunden
	 */
	public Kunde(String name, String eMail, String address, String pw) {
		super(eMail, pw);
		this.name = name;
		this.address = address;
		this.password = pw;
		
		this.cart = new Warenkorb();
	}



}
