package com.shop.valueobjects;

/**
 * 
 * @description Klasse MassenArtikel - enthaelt die Werte eines Massen-Artikels mit getter und setter
 */
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

	/**
	 * @description Konstruktor um Massengutartikel zu erstellen
	 * @param nr - Artikelnummer
	 * @param title - Titel vom Artikel
	 * @param stock - Bestand des Artikels
	 * @param price - Preis des Artikels
	 * @param anzahl - Stueckzahl des Massengutes welche mindestens gekauft werden muss
	 */
	public MassenArtikel(int nr, String title,int stock, double price, int anzahl) {
		super(nr, title, stock, price);
		this.anzahl = anzahl; 
	}
	public MassenArtikel() {}
}
