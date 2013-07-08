package com.shop.exceptions;

import com.shop.valueobjects.Kunde;

/**
 * @description Klasse CustomerExistsException - zum auffangen eines Fehlers, wenn der Kunde schon registriert ist
 *
 */
public class CustomerExistsExeption extends Exception {
	
	/**
	 * @description Konstruktor der Klasse CustomerExistsException
	 * @param k - Variable des Kunden bei dem ein Fehler aufgetreten ist um diesen in der Fehlermeldung zu verwenden
	 */
	public CustomerExistsExeption(Kunde k) {
		super("Der Kunde mit dem Namen " + k.getName() + " und der Mailadresse " +  k.geteMail()+ " ist schon vorhanden!");
	}

}
