package com.shop.exceptions;

import com.shop.valueobjects.Artikel;

/**
 * 
 * @author Helen
 *
 */
public class ArtikelNotFoundException extends Exception {
	/**
	 * 
	 * @param a
	 */
	public ArtikelNotFoundException(Artikel a) {
		super("Der Artikel mit den folgenden Daten wurde nicht gefunden: Nr:" + a.getNr() + " Titel: " + a.getTitle());
	}

}
