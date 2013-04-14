package com.shop.valueobjects;

public class Artikel {

	private String title;
	private int nr;
	

	public Artikel(int nr, String title) {
		this.nr = nr;
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}
	
	public int getNr() {
		return this.nr;
	}

}
