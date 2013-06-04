package com.shop.valueobjects;

public class MassenArtikel extends Artikel {

	public int anzahl;
	
	public MassenArtikel(int nr, String title,int stock, double price, int anzahl) {
		super(nr, title, stock, price);
		this.anzahl = anzahl; 
	}

}
