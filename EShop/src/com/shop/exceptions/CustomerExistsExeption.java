package com.shop.exceptions;

public class CustomerExistsExeption extends Exception {
	
	public CustomerExistsExeption(String msg) {
		super("Der Kunde " + msg + " ist schon vorhanden!");
	}

}
