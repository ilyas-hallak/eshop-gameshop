package com.shop.valueobjects;

import java.io.Serializable;

/**
 *@description Klasse Artikel - enthaelt die Werte eines Artikels um diese oder Massengutartikel zu erzeugen mit getter und setter.
 */
public class Artikel implements Serializable {

	public static int maxNumber = 0;
	
	// Attribute fuer die Klasse
	private static final long serialVersionUID = -3756287705724091604L;
	private String title;
	private double price;
	private int nr;
	private int stock;
	
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param nr the nr to set
	 */
	public void setNr(int nr) {
		this.nr = nr;
	}

	public Artikel() {
	}
	
	// KONSTRUKTOR ARTIKEL
	public Artikel(int nr, String title,int stock, double price2) {
		this.nr = nr;
		if (nr > maxNumber)
			maxNumber = nr;
		this.title = title;
		this.stock = stock;
		this.price = price2;
	}

	// KONSTRUKTOR MASSENGUTS
	public Artikel(String title, int stock, double price) {
		this(++maxNumber, title, stock, price);
	}

	public String getTitle() {
		return this.title;
	}
	
	public int getNr() {
		return this.nr;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStock() {
		return this.stock;

	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
