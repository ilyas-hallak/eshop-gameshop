package com.shop.valueobjects;

public class Kunde extends Person {

	private String address;

	public Kunde(String name, String eMail, String address, String pw) {
		super(eMail, pw);
		this.name = name;
		this.address = address;
		this.password = pw;
	}



}
