package com.shop.exceptions;

import com.shop.valueobjects.Person;

/**
 * 
 * @description Klasse PersonNotFoundException - zum auffangen eines Fehlers, wenn der Kunde schon registriert ist die Person nicht auf dem server existiert
 *
 */
public class PersonNotFoundException extends Exception {
	
	/**
	 * @description Konstruktor der Klasse PersonNotFoundException
	 * @param p - Variable der Person bei dem ein Fehler aufgetreten ist um diesen in der Fehlermeldung zu verwenden
	 */
	public PersonNotFoundException(Person p) {
		super("Die Person " + p.getName() + " wurde nicht gefunden!");
	}
}
