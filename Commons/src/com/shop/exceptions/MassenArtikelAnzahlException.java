package com.shop.exceptions;

import com.shop.valueobjects.MassenArtikel;

public class MassenArtikelAnzahlException extends Exception {
	public MassenArtikelAnzahlException(MassenArtikel mA) {
		super("Der Massengutartikel " + mA.getTitle() + " kann man nur in festen größen von " + mA.getAnzahl() + " gekauft werden!");
	}
}
