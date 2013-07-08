package com.shop.exceptions;

import com.shop.valueobjects.Artikel;

public class BestandZuKleinException extends Exception {
	public BestandZuKleinException(Artikel a) {
		super("Der Bestand vom Artikel " + a.getTitle() + " ist zu klein. Er hat nur einen Bestand von " + a.getStock());
	}
}
