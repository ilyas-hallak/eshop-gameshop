package com.shop.exceptions;

import com.shop.valueobjects.Artikel;

public class ArtikelexistsException extends Exception {
	public ArtikelexistsException(Artikel a) {
		super("Der Artikel " + a.getTitle() + " mit der Nummer " + a.getNr() + "ist bereits vorhanden!");
	}
}
