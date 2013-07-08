package com.shop.exceptions;

import com.shop.valueobjects.Artikel;

/**
 * @description Klasse ArtikelexistsException - zum auffangen eines Fehlers, wenn Artikel schon vorhanden
 *
 */
public class ArtikelexistsException extends Exception {

/**
 * @description Konstruktor der Klasse ArtikelexistsException
 * @param a - Variable des Artikels bei dem ein Fehler aufgetreten ist um diesen in der Fehlermeldung zu verwenden
 */
	public ArtikelexistsException(Artikel a) {
		super("Der Artikel " + a.getTitle() + " mit der Nummer " + a.getNr() + "ist bereits vorhanden!");
	}
}
