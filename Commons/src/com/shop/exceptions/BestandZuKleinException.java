package com.shop.exceptions;

public class BestandZuKleinException extends Exception {
	public BestandZuKleinException() {
		super(" Es sind nicht genuegend Artikel im Lager!");
	}
}
