package com.shop.exceptions;

import com.shop.valueobjects.Artikel;

/**
 * @description Klasse BestandZuKleinException - zum auffangen eines Fehlers, wenn die Anzahl der hinterlegten Artikel nicht genug ist
 *
 */
public class BestandZuKleinException extends Exception {
	/**
	 * @description Konstruktor der Klasse BestandZuKleinException
	 * @param a - Variable des Artikels bei dem ein Fehler aufgetreten ist um diesen in der Fehlermeldung zu verwenden
	 */
	public BestandZuKleinException(Artikel a) {
		super("Der Bestand vom Artikel " + a.getTitle() + " ist zu klein. Er hat nur einen Bestand von " + a.getStock());
	}
}
