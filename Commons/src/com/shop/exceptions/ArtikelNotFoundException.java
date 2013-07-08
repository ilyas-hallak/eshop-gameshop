package com.shop.exceptions;

import com.shop.valueobjects.Artikel;

/**
 * @description Klasse ArtikelNotFoundException - 
 * @description zum auffangen eines Fehlers, wenn Artikel nicht in der Artikellise existiert
 */
public class ArtikelNotFoundException extends Exception {
	/**
	 * @description Konstruktor der Klasse ArtikelNotFoundException
	 * @param a - Variable des Artikels bei dem ein Fehler aufgetreten ist um diesen in der Fehlermeldung zu verwenden
	 */
	public ArtikelNotFoundException(Artikel a) {
		super("Der Artikel mit den folgenden Daten wurde nicht gefunden: Nr:" + a.getNr() + " Titel: " + a.getTitle());
	}

}
