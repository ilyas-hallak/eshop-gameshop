package com.shop.valueobjects;

public class Artikel {

	private String title;
	private int nr;
	private int stock;

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
		this.stock += stock;
	}

	public int getStock() {
		return this.stock;

	}
}
