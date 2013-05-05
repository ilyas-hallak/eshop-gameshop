package com.shop.valueobjects;

import java.io.Serializable;

public class Artikel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3756287705724091604L;
	private String title;
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

	private int nr;
	private int stock;

	public Artikel() {
	}
	
	public Artikel(int nr, String title,int stock ) {
		this.nr = nr;
		this.title = title;
		this.stock = stock;
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
}
