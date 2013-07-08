package com.shop.valueobjects;

public class MassenArtikel extends Artikel {

	public int anzahl;
	
	/**
	 * @return the anzahl
	 */
	public int getAnzahl() {
		return anzahl;
	}

	/**
	 * @param anzahl the anzahl to set
	 */
	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public MassenArtikel(int nr, String title,int stock, double price, int anzahl) {
		super(nr, title, stock, price);
		this.anzahl = anzahl; 
	}

}
