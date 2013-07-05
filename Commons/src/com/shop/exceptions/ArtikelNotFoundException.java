package com.shop.exceptions;

import com.shop.valueobjects.Artikel;

public class ArtikelNotFoundException extends Exception {
	public ArtikelNotFoundException(Artikel a) {
		super("Der Artikel mit den folgenden Daten wurde nicht gefunden: Nr:" + a.getNr() + " Titel: " + a.getTitle());
	}

}
