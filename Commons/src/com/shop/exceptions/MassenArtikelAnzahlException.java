package com.shop.exceptions;

import com.shop.valueobjects.MassenArtikel;

/**
 * @description Klasse MassengutArtikelAnzahlException - zum auffangen eines Fehlers, wenn die gekaufte Anzahl nicht mit der des Massengutartikels uebereinstimmt
 *
 */
public class MassenArtikelAnzahlException extends Exception {
	
	/**
	 * @description Konstruktor der Klasse MassengutArtikelAnzahlException
	 * @param mA - Variable des Massengutartikels bei dem ein Fehler aufgetreten ist um diesen in der Fehlermeldung zu verwenden
	 */
	public MassenArtikelAnzahlException(MassenArtikel mA) {
		super("Der Massengutartikel " + mA.getTitle() + " kann man nur in festen groessen von " + mA.getAnzahl() + " gekauft werden!");
	}
}
